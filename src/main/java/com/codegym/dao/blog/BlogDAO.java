package com.codegym.dao.blog;

import com.codegym.dao.DBConnection;
import com.codegym.model.Blog;
import com.codegym.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogDAO implements IBlogDAO {
    public static final String SELECT_ALL_FROM_BLOGS_ORDER_BY_DATE_MODIFIED_DESC = "SELECT * FROM blogs order by dateModified desc";
    public static final String SELECT_ALL_FROM_BLOGS_BY_ID = "SELECT * FROM blogs where id = ?";
    public static final String INSERT_TO_BLOGS = "INSERT INTO blogs (category_id, user_id, tittle, content, dateModified) values  (?, ?, ?, ?, ?)";
    public static final String DELETE_BLOG_BY_ID = "DELETE from blogs where  id = ?";
    public static final String UPDATE_BLOG_BY_ID = "UPDATE blogs set category_id = ?, user_id = ?, tittle = ?, content = ?, dateModified = ? where id = ?";
    public static final String SELECT_CATEGORY_BY_BLOG_ID = "select C.id, C.name from blogs B join categories C on B.category_id = C.id where B.id = ?";
    public static final String SELECT_USERNAME_BY_BLOG_ID = "select U.username from blogs B join users U on B.user_id = U.id where B.id = ?";
    public static final String SELECT_USERNAME_FROM_USERS = "select id, username from users";
    public static final String SELECT_CATEGORIES = "select id, name from categories";
    public static final String SELECT_BLOGS_BY_USER_ID = "SELECT * FROM blogs WHERE user_id = ? order by dateModified desc";
    public static final String SELECT_BLOGS_BY_CATEGORY_ID = "SELECT * FROM blogs  WHERE category_id = ? order by dateModified desc";
    Connection connection = DBConnection.getConnection();

    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_BLOGS_ORDER_BY_DATE_MODIFIED_DESC);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int category_id = rs.getInt("category_id");
                int user_id = rs.getInt("user_id");
                String tittle = rs.getString("tittle");
                String content = rs.getString("content");
                String dateModified = rs.getString("dateModified");

                Blog blog = new Blog(id, category_id, user_id, tittle, content, dateModified);
                blogs.add(blog);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }

    @Override
    public Blog findByID(int id) {
        Blog blog = new Blog();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_BLOGS_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int category_id = rs.getInt("category_id");
                int user_id = rs.getInt("user_id");
                String tittle = rs.getString("tittle");
                String content = rs.getString("content");
                String dateModified = rs.getString("dateModified");
                blog = new Blog(id, category_id, user_id, tittle, content, dateModified);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blog;
    }

    @Override
    public boolean create(Blog blog) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TO_BLOGS);
            preparedStatement.setInt(1, blog.getCategory_id());
            preparedStatement.setInt(2, blog.getUser_id());
            preparedStatement.setString(3, blog.getTittle());
            preparedStatement.setString(4, blog.getContent());
            preparedStatement.setString(5, blog.getDateModified());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BLOG_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Blog blog) {
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BLOG_BY_ID);
            preparedStatement.setInt(1, blog.getCategory_id());
            preparedStatement.setInt(2, blog.getUser_id());
            preparedStatement.setString(3, blog.getTittle());
            preparedStatement.setString(4, blog.getContent());
            preparedStatement.setString(5, blog.getDateModified());
            preparedStatement.setInt(6, blog.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Category findCategoryByBlogId(int id) {
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_BLOG_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int cId = rs.getInt("id");
                String name = rs.getString("name");
                category = new Category(cId, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public String getUserNameByBlogId(int id) {
        String username = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERNAME_BY_BLOG_ID);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    @Override
    public Map<Integer, String> getMap_userId_userName() {
        Map<Integer, String> map_userId_userName = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERNAME_FROM_USERS);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                map_userId_userName.put(id, username);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map_userId_userName;
    }

    @Override
    public Map<Integer, String> getMap_categoryId_categoryName() {
        Map<Integer, String> categoryId_categoryName = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORIES);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categoryId_categoryName.put(id, name);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoryId_categoryName;
    }

    @Override
    public List<Blog> findAllBlogByUserId(int user_id) {
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BLOGS_BY_USER_ID);
            preparedStatement.setInt(1, user_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("category_id");
                int category_id = rs.getInt("category_id");
                int user_idSql = rs.getInt("user_id");
                String tittle = rs.getString("tittle");
                String content = rs.getString("content");
                String dateModified = rs.getString("dateModified");

                Blog blog = new Blog(id, category_id, user_idSql, tittle, content, dateModified);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogs;
    }

    @Override
    public List<Blog> findAllBlogByCategoryID(int category_id) {
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BLOGS_BY_CATEGORY_ID);
            preparedStatement.setInt(1, category_id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("category_id");
                int category_idSql = rs.getInt("category_id");
                int user_idSql = rs.getInt("user_id");
                String tittle = rs.getString("tittle");
                String content = rs.getString("content");
                String dateModified = rs.getString("dateModified");

                Blog blog = new Blog(id, category_idSql, user_idSql, tittle, content, dateModified);
                blogs.add(blog);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return blogs;
    }

}
