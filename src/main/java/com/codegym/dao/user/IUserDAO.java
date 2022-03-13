package com.codegym.dao.user;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.SearchResult;
import com.codegym.model.User;

import java.util.List;
import java.util.Map;

public interface IUserDAO extends IGeneralDAO<User> {
    User findByEmail(String email);

    User findByUsername(String email);

    User findByPhone(String phone);

    List<User> findAllUserByUserName(String username);

    int countBlog(User user);

    List<SearchResult> searchKeyword(String q);
    Map<Integer, String> map_roleId_roleName();
}
