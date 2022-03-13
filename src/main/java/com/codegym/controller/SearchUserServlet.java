package com.codegym.controller;

import com.codegym.dao.user.UserDAO;
import com.codegym.model.SearchResult;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchUserServlet", value = "/searchUser")
public class SearchUserServlet extends HttpServlet {
    private IUserService userService = new UserService(new UserDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            response.sendRedirect("/");
        } else {
            String q = request.getParameter("q");
            if (q.length() == 0) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/list.jsp");
                requestDispatcher.forward(request, response);
                return;
            }
            List<User> users = (List<User>) userService.findAllUserByUserName(q);
            request.setAttribute("users", users);
            request.setAttribute("tittle", "Ket qua tim kiem: " + q);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/user/list.jsp");
            requestDispatcher.forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
