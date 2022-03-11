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
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    public static final String MSG_USERNAME_EXIST = "Ten nguoi dung da ton tai";
    public static final String MSG_EMAIL_EXIST = "Email nay da duoc su dung";
    public static final String MSG_PHONE_EXIST = "So dien thoai nay da duoc su dung";
    public static final String MSG_PASSWORDS_NOT_MATCH = "Kiem tra lai mat khau";
    public static final int MIN_PASSWORD_LENGTH = 6;
    public static final int MAX_PASSWORD_LENGTH = 100;
    public static final String SYSTEM_ERROR = "Loi he thong!!";
    IUserService userService = new UserService(new UserDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("register/register-form.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password-repeat");

        String message = "";

        boolean usernameAvailable = validateRegister_username(username);
        boolean emailAvailable = validateRegister_email(email);
        boolean phoneAvailable = validateRegister_phone(phone);
        boolean passwordMatch = password.equals(password1);
        boolean passwordValid = validateRegister_password(password);


        boolean validate = usernameAvailable && emailAvailable
                && phoneAvailable && passwordMatch && passwordValid;

        if (!usernameAvailable) message = MSG_USERNAME_EXIST;
        if (!emailAvailable) message = MSG_EMAIL_EXIST;
        if (!phoneAvailable) message = MSG_PHONE_EXIST;
        if (!passwordMatch) message = MSG_PASSWORDS_NOT_MATCH;
        if (!passwordValid) message = "Mat khau phai co do dai "
                + MIN_PASSWORD_LENGTH + " - " + MAX_PASSWORD_LENGTH + " ky tu.";

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
                message = SYSTEM_ERROR;
                request.setAttribute("message", message);
                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("register/register-form.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("register/register-form.jsp");
            requestDispatcher.forward(request, response);
        }


    }

    // username da ton tai trong database hay chua
    private boolean validateRegister_username(String username){
        User user =  userService.findByUsername(username);
        return user == null;
    }

    // email da ton tai trong database hay chua
    private boolean validateRegister_email(String email){
        User user =  userService.findByEmail(email);
        return user == null;
    }

    // email da ton tai trong database hay chua
    private boolean validateRegister_phone(String phone){
        User user =  userService.findByPhone(phone);
        return user == null;
    }

    private boolean validateRegister_password(String password){
        boolean isValid = true;
        int passwordLength = password.length();
        if (passwordLength < MIN_PASSWORD_LENGTH || passwordLength > MAX_PASSWORD_LENGTH) {
            isValid = false;
        }
        return isValid;
    }

}
