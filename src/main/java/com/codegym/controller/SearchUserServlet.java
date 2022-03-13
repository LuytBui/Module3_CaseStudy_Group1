package com.codegym.controller;

import com.codegym.dao.category.CategoryDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.Category;
import com.codegym.model.SearchResult;
import com.codegym.model.User;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;
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
import java.util.Map;

import static com.codegym.controller.BlogServlet.ROLE_ID_ADMIN;

@WebServlet(name = "SearchUserServlet", value = "/searchUser")
public class SearchUserServlet extends HttpServlet {
    private IUserService userService = new UserService(new UserDAO());
    private ICategoryService categoryService = new CategoryService(new CategoryDAO());

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
            List<User> users = userService.findAllUserByUserName(q);
            request.setAttribute("users", users);
            List<Integer> blogCounts = new ArrayList<>();
            for (User user : users) {
                int count = ((UserService) userService).countBlog(user);
                blogCounts.add(count);
            }
            request.setAttribute("blogCounts", blogCounts);
            Map<Integer, String> map_roleId_roleName = userService.map_roleId_roleName();
            request.setAttribute("map_roleId_roleName", map_roleId_roleName);

            // them thuoc tinh ben sidebar
            List<Category> categories = categoryService.findAll();
            request.setAttribute("categories", categories);
            boolean isAdmin = loginUser.getRole_id() == ROLE_ID_ADMIN;
            request.setAttribute("isAdmin", isAdmin);
            String username = loginUser.getUsername();
            request.setAttribute("username", username);
            int loginUserId = loginUser.getId();
            request.setAttribute("loginUserId", loginUserId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/user/list.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
