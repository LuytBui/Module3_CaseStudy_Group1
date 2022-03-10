package com.codegym.service.blog;

import com.codegym.dao.blog.IBlogDAO;
import com.codegym.model.Blog;
import com.codegym.service.IGeneralService;

import java.util.List;

public class BlogService implements IBlogService{
    IBlogDAO blogDAO;

    public BlogService(IBlogDAO blogDAO) {
        this.blogDAO = blogDAO;
    }

    @Override
    public List<Blog> findAll() {
        return blogDAO.findAll();
    }

    @Override
    public Blog findByID(int id) {
        return blogDAO.findByID(id);
    }

    @Override
    public boolean create(Blog blog) {
        return blogDAO.create(blog);
    }

    @Override
    public boolean deleteById(int id) {
        return blogDAO.deleteById(id);
    }

    @Override
    public boolean updateById(int id, Blog blog) {
        return blogDAO.updateById(id, blog);
    }
}
