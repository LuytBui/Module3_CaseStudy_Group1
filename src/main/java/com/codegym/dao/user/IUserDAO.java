package com.codegym.dao.user;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.User;

public interface IUserDAO extends IGeneralDAO<User> {
    User findByEmail(String email);

    int countBlog(User user);
}
