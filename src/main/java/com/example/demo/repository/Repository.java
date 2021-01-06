package com.example.demo.repository;

import java.util.List;

public interface Repository<T> {
    T findById(Long id);
    List<T> findAll();
    void save(T model);
    void remove(Long id);
}