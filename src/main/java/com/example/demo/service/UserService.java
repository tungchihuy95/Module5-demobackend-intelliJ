package com.example.demo.service;

import java.util.List;

public interface UserService<T> {
    T findById(Long id);
    List<T> findAll();
    void remove(Long id);
    void save(T t);

}