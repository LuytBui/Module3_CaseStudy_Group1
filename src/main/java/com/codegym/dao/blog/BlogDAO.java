package com.codegym.dao.blog;

import com.codegym.dao.DBConnection;
import com.codegym.model.Blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
}
