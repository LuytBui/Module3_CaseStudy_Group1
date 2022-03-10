package com.codegym.dao.blog;

import com.codegym.dao.DBConnection;
import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogDAO implements IBlogDAO {
    Connection connection = DBConnection.getConnection();

    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM blogs");
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
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM blogs where id = ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO blogs (category_id, user_id, tittle, content, dateModified) values  (?, ?, ?, ?, ?)");
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
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from blogs where  id = ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE blogs set category_id = ?, user_id = ?, tittle = ?, content = ?, dateModified = ? where id = ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select C.id, C.name from blogs B join category C on B.category_id = C.id where B.id = ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select U.username from blogs B join users U on B.user_id = U.id where B.id = ?");
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
            PreparedStatement preparedStatement = connection.prepareStatement("select id, username from users");
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
}
