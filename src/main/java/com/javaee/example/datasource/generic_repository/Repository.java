package com.javaee.example.datasource.generic_repository;

import com.javaee.example.datasource.db_connector.ClassBuilder;
import java.util.List;

public interface Repository<T extends ClassBuilder<T>> {

    List<T> findAll();

    boolean addOne(T obj);
}
