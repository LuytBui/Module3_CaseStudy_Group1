package com.codegym.dao.blog;

import com.codegym.model.Blog;

import java.util.List;

public class BlogDAO implements IBlogDAO{
    @Override
    public List<Blog> findAll() {
        return null;
    }

    @Override
    public Blog findByID(int id) {
        return null;
    }

    @Override
    public boolean create(Blog blog) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean updateById(int id, Blog blog) {
        return false;
    }
}
