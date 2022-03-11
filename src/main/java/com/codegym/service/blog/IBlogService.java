package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IGeneralService;

import java.util.List;
import java.util.Map;

public interface IBlogService extends IGeneralService<Blog> {
    Category findCategoryByBlogId (int id);
    String getUserNameByBlogId (int id);
    Map<Integer, String> getMap_userId_userName();
    List<Blog> findAllBlogByUserId ();
    List<Blog> findAllBlogByCategoryID ();
}
