package com.codegym.controller;

import com.codegym.dao.blog.BlogDAO;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.model.User;
import com.codegym.service.blog.BlogService;
import com.codegym.service.blog.IBlogService;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BlogServlet", value = "/blogs")
public class BlogServlet extends HttpServlet {
    public static final int ROLE_ID_ADMIN = 1;
    IBlogService blogService = new BlogService(new BlogDAO());
    ICategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            response.sendRedirect("/");
        } else {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            List<Category> categories = categoryService.findAll();
            request.setAttribute("categories", categories);
            boolean isAdmin = loginUser.getRole_id() == ROLE_ID_ADMIN;
            request.setAttribute("isAdmin", isAdmin);
            String username = loginUser.getUsername();
            request.setAttribute("username", username);
            int loginUserId = loginUser.getId();
            request.setAttribute("loginUserId", loginUserId);
            Map<Integer, String> map_userId_userName = blogService.getMap_userId_userName();
            request.setAttribute("map_userId_userName", map_userId_userName);
            Map<Integer, String> map_categoryId_categoryName = blogService.getMap_categoryId_categoryName();
            request.setAttribute("map_categoryId_categoryName", map_categoryId_categoryName);


            switch (action) {
                case "create": {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/blog/create.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "edit": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Blog blog = blogService.findByID(id);
                    request.setAttribute("blog", blog);
                    Category blogCategory = blogService.findCategoryByBlogId(id);
                    request.setAttribute("blogCategory", blogCategory);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/blog/edit.jsp");
                    requestDispatcher.forward(request, response);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Blog blog = blogService.findByID(id);
                    request.setAttribute("blog", blog);
                    Category category = blogService.findCategoryByBlogId(id);
                    request.setAttribute("category", category);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/delete.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "view": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Blog blog = blogService.findByID(id);
                    request.setAttribute("blog", blog);
                    Category category = blogService.findCategoryByBlogId(id);
                    request.setAttribute("category", category);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/view.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "viewMyBlog": {
                    int id = loginUser.getId();
                    List<Blog> blogs = blogService.findAllBlogByUserId(id);
                    request.setAttribute("blogs", blogs);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/list.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "viewCategoryBlog": {
                    List<Blog> blogs= new ArrayList<>();
                    try {
                        int id = Integer.parseInt(request.getParameter("id"));
                        blogs = blogService.findAllBlogByCategoryID(id);
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                    }

                    request.setAttribute("blogs", blogs);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/list.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "viewUserBlog": {
                    int user_id = Integer.parseInt(request.getParameter("user_id"));
                    List<Blog> blogs = blogService.findAllBlogByUserId(user_id);
                    request.setAttribute("blogs", blogs);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/list.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "search": {
                    break;
                }
                default: {
                    List<Blog> blogs = blogService.findAll();
                    request.setAttribute("blogs", blogs);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/blog/list.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            response.sendRedirect("../");
        } else {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create": {
                    int category_id = Integer.parseInt(request.getParameter("category_id"));
                    int user_id = loginUser.getId();
                    String tittle = request.getParameter("tittle");
                    String content = request.getParameter("content");
                    String dateModified = blogService.getCurrentTime();
                    Blog blog = new Blog(category_id, user_id, tittle, content, dateModified);
                    blogService.create(blog);
                    response.sendRedirect("/blogs");
                    break;
                }
                case "edit": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    int user_id = loginUser.getId();
                    int category_id = Integer.parseInt(request.getParameter("category_id"));
                    String tittle = request.getParameter("tittle");
                    String content = request.getParameter("content");
                    String dateModified = blogService.getCurrentTime();
                    Blog blog = new Blog(id, category_id, user_id, tittle, content, dateModified);
                    blogService.updateById(id, blog);
                    response.sendRedirect("/blogs");
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    blogService.deleteById(id);
                    response.sendRedirect("/blogs");
                    break;
                }
            }
        }

    }
}
