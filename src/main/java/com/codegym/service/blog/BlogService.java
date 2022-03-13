package com.codegym.service.blog;

import com.codegym.dao.blog.IBlogDAO;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.model.SearchResult;
import com.codegym.service.IGeneralService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

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

    @Override
    public Category findCategoryByBlogId(int id) {
        return blogDAO.findCategoryByBlogId(id);
    }

    @Override
    public String getUserNameByBlogId(int id) {
        return blogDAO.getUserNameByBlogId(id);
    }

    @Override
    public Map<Integer, String> getMap_userId_userName() {
        return blogDAO.getMap_userId_userName();
    }

    @Override
    public Map<Integer, String> getMap_categoryId_categoryName() {return blogDAO.getMap_categoryId_categoryName();
    }

    @Override
    public List<Blog> findAllBlogByUserId(int user_id) {
        return blogDAO.findAllBlogByUserId(user_id);
    }

    @Override
    public List<Blog> findAllBlogByCategoryID(int category_id) {
        return blogDAO.findAllBlogByCategoryID(category_id);
    }

    @Override
    public String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return String.valueOf(now);
    }

    @Override
    public List<SearchResult> searchKeyword(String q) {
        return blogDAO.searchKeyword(q);
    }

}
