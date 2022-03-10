package com.codegym.dao.user;

import com.codegym.dao.DBConnection;
import com.codegym.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO implements IUserDAO {
    public static final String SQL_SELECT_ALL_USER = "SELECT * FROM user ";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT * from user where  id = ?";
    public static final String SQL_INSERT_USER = "INSERT into user (username,passwork,phone,email,dateOfBirth,gender,address,status,role_id) value (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_UPDATE_USER_BY_ID = "update user set username =?,password =?,phone =?,email =?,dateOfBirth =?,gender=?,address = ?,status =? , role_id =?";
    public static final String SQL_DELETE_USER_BY_ID = "delete  from user where  id = ?";
    private Connection connection = DBConnection.getConnection();

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                Boolean gender = resultSet.getBoolean("gender");
                String address = resultSet.getString("address");
                boolean status = resultSet.getBoolean("status");
                int roleId = resultSet.getInt("role_id");
                User user = new User(id, username, password, phone, email, dateOfBirth, gender, address,status);
                user.setRole_id(roleId);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByID(int id) {
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Date dateOfBirth = resultSet.getDate("dateOfBirth");
                Boolean gender = resultSet.getBoolean("gender");
                String address = resultSet.getString("address");
                boolean status = resultSet.getBoolean("status");
                int roleId = resultSet.getInt("role_id");
                user = new User(id, username, password, phone, email, dateOfBirth, gender, address,status);
                user.setRole_id(roleId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getPhone());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setDate(5, (java.sql.Date) user.getDateOfBirth());
            preparedStatement.setBoolean(6,user.isGender());
            preparedStatement.setString(7,user.getAddress());
            preparedStatement.setBoolean(8,user.isStatus());
            preparedStatement.setInt(9,user.getRole_id());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateById(int id, User user) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_BY_ID);
            preparedStatement.setString(1,user.getUsername());
            preparedStatement.setString(2,user.getPassword());
            preparedStatement.setString(3,user.getPhone());
            preparedStatement.setString(4,user.getEmail());
            preparedStatement.setDate(5, (java.sql.Date) user.getDateOfBirth());
            preparedStatement.setBoolean(6,user.isGender());
            preparedStatement.setString(7,user.getAddress());
            preparedStatement.setBoolean(8,user.isStatus());
            preparedStatement.setInt(9,user.getRole_id());
            return preparedStatement.executeUpdate() >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return false;
    }
}
