package com.javaee.example.datasource.db_connector;

import java.sql.SQLException;
import java.util.List;

public interface OrientDbConnector {
    /**
     *
     * @param queryString sql query
     * @return boolean
     * @throws SQLException when db operation failed
     */
    boolean insert(String queryString) throws SQLException;

    /**
     * @param queryString sql query
     * @return boolean
     * @throws SQLException when db operation failed
     */
    boolean delete(String queryString) throws SQLException;

    /**
     * @param queryString sql query
     * @return int
     * @throws SQLException when db operation failed
     */
    int update(String queryString) throws SQLException;

    /**
     * @param queryString sql query
     * @param expectedObj expectedObj
     * @param <T>-
     * @return List<T>
     * @throws SQLException when db operation failed
     */
    <T extends ClassBuilder<T>> List<T> select(String queryString, T expectedObj) throws SQLException;
}
