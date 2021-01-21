package com.javaee.example.datasource.db_connector;

import java.util.Map;

/**
 * Class Builder interface
 * This interface provides building new class object from map
 * All classes which implements this interface can be used as entity for saving data from db
 *
 * @param <T> - expected type of returned object
 */
public interface ClassBuilder<T> {

    /**
     * @param fields - content for expected object
     * @return T
     */
    T build(Map<String, Object> fields);

    /**
     * This method makes a map of class fields, improves field validation process etc.
     *
     * @return Map
     */
    Map<String, Object> getFieldMap();
}
