package com.codegym.controller;

import com.codegym.model.Category;
import com.codegym.model.User;
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
    public static final int ROLE_ID_ADMIN = 1;
    public CategoryServlet() {
        categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
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

    protected void doGetAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            response.sendRedirect("");
        } else {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create": {
                    showCreateCategoryForm(request, response);
                    break;
                }
                case "delete": {
                    int id = Integer.parseInt(request.getParameter("id"));
                    Category category = categoryService.findByID(id);
                    request.setAttribute("category", category);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/category/delete.jsp");
                    dispatcher.forward(request, response);
                    break;
                }
                case "edit": {
                    showEditCategoryForm(request, response);
                    break;
                }
                default: {
                    showListCategory(request, response);
                    break;
                }
            }
        }
    }

    private void showEditCategoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findByID(id);
        request.setAttribute("category", category);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/edit.jsp");
        dispatcher.forward(request, response);
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
                createCategory(request, response);
                break;
            }
            case "delete" :{
                deleteCategory(request, response);
                break;
            }
            case "edit" :{
                editCategory(request, response);
                break;
            }
        }
    }

    private void editCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category = new Category(id, name);
        categoryService.updateById(id, category);
        response.sendRedirect("/categories");
        return;
    }

    private void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        categoryService.deleteById(id);
        response.sendRedirect("/categories");
    }

    private void createCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Category category = new Category( name);
        categoryService.create(category);

        request.setAttribute("message", "Tạo mới thành công");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/category/create.jsp");
        dispatcher.forward(request, response);
        return;
    }
}
