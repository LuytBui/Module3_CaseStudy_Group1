package com.codegym.service.category;

import com.codegym.model.Category;
import com.codegym.model.SearchResult;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface ICategoryService extends IGeneralService<Category> {
    List<SearchResult> searchKeyword(String q);
}
