package com.lookup.dao;

public interface Dao<T> {
    T findById(int id);

    T insert(T model);

    T update(T model);

    int delete(int id);
}
