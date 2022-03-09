package com.codegym.dao.category;

import com.codegym.model.Category;

import java.util.List;

public class CategoryDAO implements ICategoryDAO{
    @Override
    public List<Category> findAll() {
        return null;
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
