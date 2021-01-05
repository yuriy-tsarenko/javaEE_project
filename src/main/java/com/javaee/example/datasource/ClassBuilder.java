package com.javaee.example.datasource;

import java.util.Map;

/**
 * Class Builder interface
 * This interface provides building new class object from map
 * All classes which implements this interface can be used as entity for saving data from db
 *
 * @param <T> - expected type of returned object
 */
public interface ClassBuilder<T> {

    T build(Map<String, Object> fields);
}
