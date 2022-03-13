package com.codegym.controller;


import com.codegym.dao.user.UserDAO;
import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import com.codegym.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {

    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 100;
    public static final String MSG_PASSWORD_INVALID = "Mat khau phai co do dai "
            + MIN_PASSWORD_LENGTH + " - " + MAX_PASSWORD_LENGTH + " ky tu.";

    public static final int MIN_USERNAME_LENGTH = 5;
    public static final int MAX_USERNAME_LENGTH = 25;
    //    String USERNAME_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){4,23}[a-zA-Z0-9]$";
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){" + (MIN_USERNAME_LENGTH - 2) + "," + (MAX_USERNAME_LENGTH - 2) + "}[a-zA-Z0-9]$";
    public static final String EMAIL_REGEX = "^[A-Za-z]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]{1,})$";
    public static final String PHONE_REGEX = "^[0]+[0-9]{9}";

    public static final String MSG_USERNAME_EXIST = "Ten nguoi dung da ton tai";
    public static final String MSG_EMAIL_EXIST = "Email nay da duoc su dung";
    public static final String MSG_PHONE_EXIST = "So dien thoai nay da duoc su dung";
    public static final String MSG_PASSWORDS_NOT_MATCH = "Kiem tra lai mat khau";
    public static final String MSG_SYSTEM_ERROR = "Loi he thong!!";
    public static final String MSG_USERNAME_INVALID = "Ten nguoi dung khong hop le. Phai co " +
            MIN_USERNAME_LENGTH + "-" + MAX_USERNAME_LENGTH + " ky tu, khong chua ky tu dac biet.";
    public static final String MSG_EMAIL_INVALID = "Email khong hop le";
    public static final String MSG_PHONE_INVALID = "So dien thoai khong hop le";
    public static final String MSG_AGREE_TERMS_OF_SERVICE = "Vui long xac nhan dong y voi dieu khoan dich vu.";

    IUserService userService = new UserService(new UserDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register/register-form.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Boolean agreeToS = request.getParameter("agree-ToS") != null;
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password-repeat");

        String message = "";

        boolean usernameAvailable = usernameExisted(username);
        boolean validUsername = validUsername(username);
        boolean emailAvailable = emailExisted(email);
        boolean validEmail = validEmail(email);
        boolean phoneAvailable = phoneExisted(phone);
        boolean validPhone = validPhone(phone);
        boolean passwordMatch = password.equals(password1);
        boolean validPassword = validPassword(password);

        boolean validate = agreeToS
                && usernameAvailable && validUsername
                && emailAvailable && validEmail
                && phoneAvailable && validPhone
                && passwordMatch && validPassword;

        if (!passwordMatch) message = MSG_PASSWORDS_NOT_MATCH;
        if (!validPassword) message = MSG_PASSWORD_INVALID;
        if (!phoneAvailable) message = MSG_PHONE_EXIST;
        if (!validPhone) message = MSG_PHONE_INVALID;
        if (!emailAvailable) message = MSG_EMAIL_EXIST;
        if (!validEmail) message = MSG_EMAIL_INVALID;
        if (!usernameAvailable) message = MSG_USERNAME_EXIST;
        if (!validUsername) message = MSG_USERNAME_INVALID;
        if (!agreeToS) message = MSG_AGREE_TERMS_OF_SERVICE;

        request.setAttribute("message", message);

        if (validate) {
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            boolean success = userService.create(user);
            if (success) {
                response.sendRedirect("register/success.html");
            } else {
                message = MSG_SYSTEM_ERROR;
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("register/register-form.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("phone", phone);
            request.setAttribute("agreed", agreeToS);

            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("register/register-form.jsp");
            requestDispatcher.forward(request, response);
        }


    }

    // username da ton tai trong database hay chua
    private boolean usernameExisted(String username) {
        User user = userService.findByUsername(username);
        return user == null;
    }

    // email da ton tai trong database hay chua
    private boolean emailExisted(String email) {
        User user = userService.findByEmail(email);
        return user == null;
    }

    // email da ton tai trong database hay chua
    private boolean phoneExisted(String phone) {
        User user = userService.findByPhone(phone);
        return user == null;
    }

    private boolean validPassword(String password) {
        boolean isValid = true;
        int passwordLength = password.length();
        if (passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH) {
            isValid = false;
        }
        return isValid;
    }

    public static boolean validPhone(String phone) {
        Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean validEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean validUsername(String username) {
        Pattern pattern = Pattern.compile(USERNAME_REGEX);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

}
