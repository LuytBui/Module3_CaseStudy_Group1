package com.codegym.controller;

import com.codegym.dao.user.UserDAO;
import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import com.codegym.service.user.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet(name = "UserServlet", value = "/users")
public class UserServlet extends HttpServlet {
    public static final int ROLE_ID_ADMIN = 1;
    private IUserService userService = new UserService(new UserDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            response.sendRedirect("");
        } else {
            boolean isAdmin = loginUser.getRole_id() == ROLE_ID_ADMIN;
            if (isAdmin) {
                doGetAdmin(request, response);
            } else {
                response.sendRedirect("https://google.com.vn");
            }

        }
    }

    private void doGetAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                List<User> users = userService.findAll();
                request.setAttribute("users", users);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/users/create.jsp");
                dispatcher.forward(request, response);
            }
            default: {
                List<User> users = userService.findAll();

                request.setAttribute("users", users);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String dateOfBirth = request.getParameter("dateOfBirth");
                boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
                String address = request.getParameter("address");
                boolean status = Boolean.parseBoolean(request.getParameter("status"));
                int roleId = Integer.parseInt("role_id");
                User user = new User(username, password, phone, email, dateOfBirth, gender, address, status);
                user.setRole_id(roleId);
                userService.create(user);
                response.sendRedirect("/users");
                break;
            }

        }
    }
}
