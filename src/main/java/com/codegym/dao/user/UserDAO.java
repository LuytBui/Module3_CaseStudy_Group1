package com.codegym.dao.user;

import com.codegym.model.User;

import java.util.List;

public class UserDAO implements IUserDao{
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByID(int id) {
        return null;
    }

    @Override
    public boolean create(User user) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean updateById(int id, User user) {
        return false;
    }
}
