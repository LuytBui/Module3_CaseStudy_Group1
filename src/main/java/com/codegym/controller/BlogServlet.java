package com.codegym.controller;

import com.codegym.dao.blog.BlogDAO;
import com.codegym.model.Blog;
import com.codegym.service.blog.BlogService;
import com.codegym.service.blog.IBlogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BlogServlet", value = "/blogs")
public class BlogServlet extends HttpServlet {
    IBlogService blogService = new BlogService(new BlogDAO());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {

                break;
            }
            default: {
                List<Blog> blogs = blogService.findAll();
                request.setAttribute("blogs", blogs);
                Map<Integer, String> map_userId_userName = blogService.getMap_userId_userName();
                request.setAttribute("map_userId_userName", map_userId_userName);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/list.jsp");
                dispatcher.forward(request, response);
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
