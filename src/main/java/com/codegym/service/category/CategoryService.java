package com.codegym.service.category;

import com.codegym.dao.category.CategoryDAO;
import com.codegym.model.Category;

import java.util.List;

public class CategoryService implements ICategoryService{

    private CategoryDAO categoryDAO;

    public CategoryService() {
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findByID(int id) {
        return categoryDAO.findByID(id);
    }

    @Override
    public boolean create(Category category) {
        return categoryDAO.create(category);
    }

    @Override
    public boolean deleteById(int id) {
        return categoryDAO.deleteById(id);
    }

    @Override
    public boolean updateById(int id, Category category) {
        return categoryDAO.updateById(id ,category);
    }
}
