package com.codegym.dao.user;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.User;

public interface IUserDAO extends IGeneralDAO<User> {
    User findByUsername(String searchUsername);
}
