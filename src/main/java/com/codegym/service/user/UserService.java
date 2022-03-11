package com.codegym.service.user;

import com.codegym.dao.user.IUserDAO;
import com.codegym.dao.user.UserDAO;
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

    public User findByEmail(String email) {
        return ((UserDAO) userDAO).findByEmail(email);
    }
}
