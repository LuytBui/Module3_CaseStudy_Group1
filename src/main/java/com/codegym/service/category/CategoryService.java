package com.codegym.service.category;

import com.codegym.dao.category.CategoryDAO;
import com.codegym.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{
    private static List<Category> categories = new ArrayList<>();
    private CategoryDAO categoryDAO;
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
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean updateById(int id, Category category) {
        return false;
    }
}
