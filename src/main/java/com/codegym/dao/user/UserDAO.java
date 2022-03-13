package com.codegym.dao.user;

import com.codegym.dao.DBConnection;
import com.codegym.model.SearchResult;
import com.codegym.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO implements IUserDAO {
    public static final String SQL_SELECT_ALL_USER = "SELECT * FROM users;";
    public static final String SQL_SELECT_USER_BY_ID = "SELECT * from users where  id = ?;";
    public static final String SQL_INSERT_USER = "INSERT into users (username,password,phone,email) value (?,?,?,?);";
    public static final String SQL_UPDATE_USER_BY_ID = "update users set username =?,password =?,phone =?,email =?,dateOfBirth =?,gender=?,address = ?,status =? , role_id =? where id =?;";
    public static final String SQL_DELETE_USER_BY_ID = "delete  from users where  id = ?;";
    public static final String SELECT_FROM_USERS_WHERE_EMAIL = "SELECT *from users where email =?;";
    private Connection connection = DBConnection.getConnection();

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USER);
            ResultSet resultSet = preparedStatement.executeQuery();
            users = getListUserFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByID(int search) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_ID);
            preparedStatement.setInt(1, search);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = getListUserFromResultSet(resultSet);
            if (result.size() > 0) {
                user = result.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    private List<User> getListUserFromResultSet(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String phone = resultSet.getString("phone");
            String email = resultSet.getString("email");
            String dateOfBirth = resultSet.getString("dateOfBirth");
            Boolean gender = resultSet.getBoolean("gender");
            String address = resultSet.getString("address");
            boolean status = resultSet.getBoolean("status");
            int roleId = resultSet.getInt("role_id");
            User user = new User(id, username, password, phone, email, dateOfBirth, gender, address, status);
            user.setRole_id(roleId);
            users.add(user);
        }
        return users;
    }

    @Override
    public boolean create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail().toLowerCase());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
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
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPhone());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getDateOfBirth());
            preparedStatement.setBoolean(6, user.isGender());
            preparedStatement.setString(7, user.getAddress());
            preparedStatement.setBoolean(8, user.isStatus());
            preparedStatement.setInt(9, user.getRole_id());
            preparedStatement.setInt(10, user.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findByEmail(String email) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FROM_USERS_WHERE_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = getListUserFromResultSet(resultSet);
            if (result.size() > 0) {
                user = result.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = getListUserFromResultSet(resultSet);
            if (result.size() > 0) {
                user = result.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByPhone(String phone) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where phone = ?");
            preparedStatement.setString(1, phone);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = getListUserFromResultSet(resultSet);
            if (result.size() > 0) {
                user = result.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findAllUserByUserName(String username) {
        User users = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> result = getListUserFromResultSet(resultSet);
            if (result.size() > 0) {
                users = result.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public int countBlog(User user) {
        int count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select users.id, count(users.id) as \"count\"\n" +
                    "from users join blogs on users.id = blogs.user_id\n" +
                    "where users.id = ?;");
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("count");
                break;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public List<SearchResult> searchKeyword(String q) {
        String searchPattern = "%" + q + "%";
        List<SearchResult> searchResults = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select username, id from users where username like ?");
            preparedStatement.setString(1, searchPattern);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                User user = findByID(id);
                String username = resultSet.getString("username");

                SearchResult searchResult = new SearchResult();
                searchResult.setType("User");
                searchResult.setName(username);
                searchResult.setUrl("/blogs?action=viewUserBlog&user_id=" + id);
                searchResult.setPreviewContent("Co " + countBlog(user) + " bai viet.");

                searchResults.add(searchResult);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    @Override
    public Map<Integer, String> map_roleId_roleName() {
        Map<Integer, String> map_roleId_roleName = new HashMap<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select id, role from roles;");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int roleId = resultSet.getInt("id");
                String roleName = resultSet.getString("role");
                map_roleId_roleName.put(roleId, roleName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map_roleId_roleName;
    }
}
