package com.codegym.dao.category;

import com.codegym.dao.DBConnection;
import com.codegym.model.Category;
import com.codegym.model.SearchResult;
import com.codegym.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategoryDAO{

    public CategoryDAO() {
    }
    Connection connection = DBConnection.getConnection();

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public Category findByID(int id) {
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from categories where id =?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int Category_id = rs.getInt("id");
                String name = rs.getString("name");
                category = new Category(Category_id, name);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public boolean create(Category category) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categories ( name) values ( ?)");
            preparedStatement.setString(1, category.getName());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE from categories where  id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, Category category) {
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categories set name = ? where id= ?");
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SearchResult> searchKeyword(String q) {
        String searchPattern = "%" + q + "%";
        List<SearchResult> searchResults = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select name, id from categories where name like ?");
            preparedStatement.setString(1, searchPattern);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                SearchResult searchResult = new SearchResult();
                searchResult.setType("Category");
                searchResult.setName(name);
                searchResult.setUrl("/blogs?action=viewCategoryBlog&id=" + id);
                searchResult.setPreviewContent("");

                searchResults.add(searchResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }
}
