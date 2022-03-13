package com.codegym.service.user;

import com.codegym.dao.user.IUserDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.SearchResult;
import com.codegym.model.User;

import java.util.List;

public class UserService implements IUserService {

    private IUserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByID(int id) {
        return userDAO.findByID(id);
    }

    @Override
    public boolean create(User user) {
        return userDAO.create(user);
    }

    @Override
    public boolean deleteById(int id) {
        return userDAO.deleteById(id);
    }

    @Override
    public boolean updateById(int id, User user) {
        return userDAO.updateById(id, user);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public User findByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }

    @Override
    public List<SearchResult> searchKeyword(String q) {
        return userDAO.searchKeyword(q);
    }

    @Override
    public User findAllUserByUserName(String username) {
        username = "%" + username + "%";
        return (User) userDAO.findAllUserByUserName(username);
    }

    public int countBlog(User user) {
        return userDAO.countBlog(user);
    }

    @Override
    public User findByUsername(String searchUsername) {
        return userDAO.findByUsername(searchUsername);
    }

}
