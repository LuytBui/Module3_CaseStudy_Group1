package com.codegym.service.category;

import com.codegym.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService{
    private static List<Category> categories = new ArrayList<>();
    @Override
    public List<Category> findAll() {
        return categories;
    }

    @Override
    public Category findByID(int id) {
        return null;
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
