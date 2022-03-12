package com.codegym.service.blog;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IGeneralService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface IBlogService extends IGeneralService<Blog> {
    Category findCategoryByBlogId (int id);
    String getUserNameByBlogId (int id);
    Map<Integer, String> getMap_userId_userName();
    Map<Integer, String> getMap_categoryId_categoryName();
    List<Blog> findAllBlogByUserId (int user_id);
    List<Blog> findAllBlogByCategoryID (int category_id);
    String getCurrentTime ();
}

