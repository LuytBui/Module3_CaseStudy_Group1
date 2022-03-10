package com.codegym.service.user;

import com.codegym.dao.user.IUserDAO;
import com.codegym.dao.user.UserDAO;
import com.codegym.model.User;

import java.util.List;

public class UserService implements IUserService {
    private IUserDAO iUserDAO;

    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UserService(IUserDAO iUserDAO) {
        this.iUserDAO = iUserDAO;
    }

    @Override
    public List<User> findAll() {
        return iUserDAO.findAll();
    }

    @Override
    public User findByID(int id) {
        return iUserDAO.findByID(id);
    }

    @Override
    public boolean create(User user) {
        return iUserDAO.create(user);
    }

    @Override
    public boolean deleteById(int id) {
        return iUserDAO.deleteById(id);
    }

    @Override
    public boolean updateById(int id, User user) {
        return iUserDAO.updateById(id, user);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}
