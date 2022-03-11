package com.codegym.dao.user;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.User;

public interface IUserDAO extends IGeneralDAO<User> {
    User findByEmail(String email);
    User findByUsername(String email);
    User findByPhone(String phone);

    int countBlog(User user);
}
