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
import java.io.IOException;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    UserService userService = new UserService(new UserDAO());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String USER_NOT_EXIST = "User khong ton tai!";
        String WRONG_PASSWORD = "Mat khau khong dung!";

        boolean loginSuccess;
        String email = request.getParameter("email");
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
        loginSuccess = password.equals(user.getPassword());
        if (loginSuccess) {
            System.out.println("login success");
        } else {
            // Wrong password
            request.setAttribute("message", WRONG_PASSWORD);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
