package com.codegym.controller;

import com.codegym.model.User;
import com.codegym.service.user.IUserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action){
            case "create":{
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                String phone = request.getParameter("phone");
//                String email = request.getString("email");
//                Date dateOfBirth = request.getDate("dateOfBirth");
//                Boolean gender = request.getBoolean("gender");
//                String address = request.getString("address");
//                boolean status = request.getBoolean("status");
//                int roleId = request.getInt("role_id");
//                User user = new User(id, username, password, phone, email, dateOfBirth, gender, address, status);
                break;
            }
        }
    }
}
