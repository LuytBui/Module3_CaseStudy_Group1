package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.service.IGeneralService;

public interface IUserService extends IGeneralService<User> {
    User findByUsername(String searchUsername);
    User findByEmail(String email);
    User findByPhone(String phone);
    User findAllUserByUserName(String username);
}
