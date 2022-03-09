package com.codegym.service;

import java.util.List;

public interface IGeneralService<T> {
    List<T> findAll();

    T findByID(int id);

    boolean create(T t);  // thanh cong = true

    boolean deleteById(int id);  // thanh cong = true

    boolean updateById(int id, T t);  // thanh cong = true

}
