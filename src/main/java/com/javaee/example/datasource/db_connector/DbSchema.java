package com.javaee.example.datasource.db_connector;

import java.sql.SQLException;

public interface DbSchema {

    void createSchema() throws SQLException;
}
