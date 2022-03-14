package com.codegym.controller;

import com.codegym.dao.user.UserDAO;
import com.codegym.model.User;
import com.codegym.service.user.IUserService;
import com.codegym.service.user.UserService;

import javax.jws.soap.SOAPBinding;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet", value = "/users")
//@WebServlet(name = "SearchUserServlet", value = "/users/search")
public class UserServlet extends HttpServlet {
    public static final int ROLE_ID_ADMIN = 1;
    public static final int ROLE_ID_BLOGGER = 2;
    private IUserService userService = new UserService(new UserDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null || loginUser.isBlocked()) {
            response.sendRedirect("/");
        } else {
            boolean isAdmin = loginUser.getRole_id() == ROLE_ID_ADMIN;
            if (isAdmin) {
                doGetAdmin(request, response);
            } else {
                response.sendRedirect("/blogs");
            }

        }
    }

    private void doGetAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "/";
        }



        switch (action) {
            case "create": {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/users/create.jsp");
                dispatcher.forward(request, response);
            }
            case "block": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findByID(id);
                user.setStatus(false);
                userService.updateById(id, user);
                response.sendRedirect("/users");
                break;
            }

            case "unblock": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findByID(id);
                user.setStatus(true);
                userService.updateById(id, user);
                response.sendRedirect("/users");
                break;
            }
            case "setAdmin": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findByID(id);
                user.setRole_id(ROLE_ID_ADMIN); //ROLE_ID_
                userService.updateById(id, user);
                response.sendRedirect("/users");
                break;
            }
            case "unsetAdmin": {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.findByID(id);
                user.setRole_id(ROLE_ID_BLOGGER);
                userService.updateById(id, user);
                response.sendRedirect("/users");
                break;
            }

            default: {
                List<User> users = userService.findAll();
                request.setAttribute("users", users);
                List<Integer> blogCounts = new ArrayList<>();
                for (User user : users) {
                    int count = ((UserService) userService).countBlog(user);
                    blogCounts.add(count);
                }
                request.setAttribute("blogCounts", blogCounts);
                Map<Integer, String> map_roleId_roleName = userService.map_roleId_roleName();
                request.setAttribute("map_roleId_roleName", map_roleId_roleName);
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
