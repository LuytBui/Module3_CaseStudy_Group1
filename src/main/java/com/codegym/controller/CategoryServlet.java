package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.service.category.CategoryService;
import com.codegym.service.category.ICategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CategoryServlet", value = "/categories")
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService;

    public CategoryServlet() {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create": {
                showCreateCategoryForm(request, response);
                break;
            }
            default:{
                showListCategory(request, response);
                break;
            }
        }
    }

    private void showListCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateCategoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create" : {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                Category category = new Category(id , name);
                categoryService.create(category);

                request.setAttribute("massage", "Tạo mới thành công");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
}
