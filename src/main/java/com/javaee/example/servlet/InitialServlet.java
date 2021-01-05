package com.javaee.example.servlet;

import com.javaee.example.datasource.DbSchema;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

@WebServlet(loadOnStartup = 1)
public class InitialServlet extends HttpServlet {

    @EJB
    DbSchema schema;

    @Override
    public void init() throws ServletException {
        try {
            schema.createSchema();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
