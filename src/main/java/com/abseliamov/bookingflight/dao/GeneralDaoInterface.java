package com.abseliamov.bookingflight.dao;

import java.util.List;

public interface GeneralDaoInterface<T> {

    void create(T t);

    T getById(long id);

    List<T> getAll();

    void update(T t);

    void delete(T t);
}