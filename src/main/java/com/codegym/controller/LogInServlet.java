package com.codegym.controller;


import com.codegym.dao.user.UserDAO;
import com.codegym.model.User;
import com.codegym.service.user.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", value = "")
public class LogInServlet extends HttpServlet {

    public static final String MSG_USER_BLOCKED = "Tai khoan da bi khoa, vui long lien he Admin de giai quyet.";
    public static final String USER_NOT_EXIST = "User khong ton tai!";
    public static final String WRONG_PASSWORD = "Mat khau khong dung!";

    UserService userService = new UserService(new UserDAO());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("/blogs");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String email = request.getParameter("email").toLowerCase();
        String password = request.getParameter("password");

        User user = userService.findByEmail(email);

        if (user == null) {
            request.setAttribute("message", USER_NOT_EXIST);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        // user exist
        // check for password
        boolean passwordMatch = password.equals(user.getPassword());

        if (passwordMatch) {
            boolean userBlocked = !user.isStatus();
            if (userBlocked) {
                request.setAttribute("message", MSG_USER_BLOCKED);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("/blogs");

        } else {
            // Wrong password
            request.setAttribute("message", WRONG_PASSWORD);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
