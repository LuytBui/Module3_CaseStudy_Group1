package com.codegym.dao.category;

import com.codegym.dao.IGeneralDAO;
import com.codegym.model.Category;
import com.codegym.model.SearchResult;

import java.util.List;

public interface ICategoryDAO extends IGeneralDAO<Category> {
    List<SearchResult> searchKeyword(String q);
}
