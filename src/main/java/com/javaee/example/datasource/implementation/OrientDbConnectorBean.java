package com.javaee.example.datasource.implementation;

import com.javaee.example.datasource.ClassBuilder;
import com.javaee.example.datasource.OrientDbConnector;
import com.orientechnologies.orient.jdbc.OrientJdbcConnection;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Stateless
public class OrientDbConnectorBean implements OrientDbConnector {

    private Connection connection;
    private Statement statement;

    @Override
    public boolean insert(String queryString) throws SQLException {
        return execute(queryString);
    }

    @Override
    public boolean delete(String queryString) throws SQLException {
        return execute(queryString);
    }

    @Override
    public int update(String queryString) throws SQLException {
        Statement statement = openStatement();
        int count = statement.executeUpdate(queryString);
        closeStatement();
        return count;
    }

    @Override
    public <T extends ClassBuilder<T>> List<T> select(String queryString, T expectedObj) throws SQLException {
        List<T> records = new ArrayList<>();
        ResultSet dbResponse = executeQuery(queryString);
        ResultSetMetaData metaData = dbResponse.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (dbResponse.next()) {
            Map<String, Object> objData = new HashMap<>();
            objData.put("@rid", dbResponse.getString("@rid"));
            for (int sqlIndex = 1; sqlIndex <= columnCount; sqlIndex++) {
                String columnName = metaData.getColumnName(sqlIndex);
                Object value = dbResponse.getObject(sqlIndex);
                objData.put(columnName, value);
            }
            records.add(expectedObj.build(objData));
        }
        dbResponse.close();
        connection.close();
        return records;
    }

    private ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = openStatement();
        ResultSet set = statement.executeQuery(sql);
        closeStatement();
        return set;
    }

    private boolean execute(String sql) throws SQLException {
        Statement statement = openStatement();
        boolean response = statement.execute(sql);
        closeStatement();
        return response;
    }

    private Statement openStatement() throws SQLException {
        Properties info = new Properties();
        info.put("user", "admin");
        info.put("password", "admin");
        connection = (OrientJdbcConnection) DriverManager
                .getConnection("jdbc:orient:remote://localhost:2480/test_db", info);
        statement = connection.createStatement();
        return statement;
    }

    private void closeStatement() throws SQLException {
        connection.close();
        statement.close();
    }

}
