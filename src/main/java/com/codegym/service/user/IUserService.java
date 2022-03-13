package com.codegym.service.user;

import com.codegym.model.SearchResult;
import com.codegym.model.User;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Map;

public interface IUserService extends IGeneralService<User> {
    User findByUsername(String searchUsername);
    User findByEmail(String email);
    User findByPhone(String phone);
    List<SearchResult> searchKeyword(String q);
    User findAllUserByUserName(String username);
    Map<Integer, String> map_roleId_roleName();
}
