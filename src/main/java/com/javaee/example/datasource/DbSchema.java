package com.javaee.example.datasource;

import java.sql.SQLException;

public interface DbSchema {

    void createSchema() throws SQLException;
}
