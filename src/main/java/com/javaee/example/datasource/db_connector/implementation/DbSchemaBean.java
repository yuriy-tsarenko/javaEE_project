package com.javaee.example.datasource.db_connector.implementation;

import com.javaee.example.datasource.db_connector.DbSchema;
import com.javaee.example.datasource.db_connector.OrientDbConnector;
import com.javaee.example.datasource.query_builder.QueryBuilder;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.sql.SQLException;

import static com.javaee.example.datasource.query_builder.Keywords.EXTENDS;
import static com.javaee.example.datasource.query_builder.Keywords.IF_NOT_EXISTS;
import static com.javaee.example.datasource.query_builder.Keywords.SPACE;
import static com.javaee.example.datasource.query_builder.Keywords.V_CLASS;
import static com.javaee.example.util.Constants.CUSTOMER_CLASS;
import static com.javaee.example.util.Constants.PRODUCT_CLASS;

@Stateless
public class DbSchemaBean implements DbSchema {

    @EJB
    OrientDbConnector connector;

    @EJB
    QueryBuilder queryBuilder;

    @Override
    public void createSchema() throws SQLException {
        connector.insert(queryBuilder
                .createClass(CUSTOMER_CLASS, SPACE, IF_NOT_EXISTS, SPACE, EXTENDS, SPACE, V_CLASS));
        connector.insert(queryBuilder
                .createClass(PRODUCT_CLASS, SPACE, IF_NOT_EXISTS, SPACE, EXTENDS, SPACE, V_CLASS));
    }
}
