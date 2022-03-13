package com.codegym.controller;

import com.codegym.dao.blog.BlogDAO;
import com.codegym.dao.category.CategoryDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.SearchResult;
import com.codegym.model.User;
import com.codegym.service.blog.BlogService;
import com.codegym.service.blog.IBlogService;
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


@WebServlet(name = "BlogSearchServlet", value = "/search")
public class BlogSearchServlet extends HttpServlet {
    IUserService userService = new UserService(new UserDAO());
    IBlogService blogService = new BlogService(new BlogDAO());
    ICategoryService categoryService = new CategoryService(new CategoryDAO());
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } else {
            String q = request.getParameter("q");
            if (q.length() == 0) {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("blog/search.jsp");
                requestDispatcher.forward(request, response);
                return;
            }
            List<SearchResult> searchResults = new ArrayList<>();
            searchResults.addAll(userService.searchKeyword(q));
            searchResults.addAll(categoryService.searchKeyword(q));
            searchResults.addAll(blogService.searchKeyword(q));
            request.setAttribute("searchResults", searchResults);
            request.setAttribute("tittle", "Ket qua tim kiem: "+q);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("blog/search.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}