package com.javaee.example.servlet;

import com.google.gson.Gson;
import com.javaee.example.datasource.DataSource;
import com.javaee.example.dto.Product;

import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/product")
@Getter
public class ProductServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        DataSource dataSource = new DataSource();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        List<Product> allFromDb = dataSource.findAllProducts();
        try {
            response.getWriter().write(new Gson().toJson(allFromDb));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        DataSource dataSource = new DataSource();
        StringBuilder builder = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        String inputLine = null;
        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader();
            while ((inputLine = reader.readLine()) != null) {
                builder.append(inputLine);
            }
            reader.close();
            jsonObject = (JSONObject) JSONValue.parseWithException(builder.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        String name = (String) jsonObject.get("name");
        String description = (String) jsonObject.get("description");
        Long priceLong = (Long) jsonObject.get("price");
        Long amount = (Long) jsonObject.get("amount");
        BigDecimal price = BigDecimal.valueOf(priceLong);

        dataSource.createProduct(name, description, price, amount);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        DataSource dataSource = new DataSource();
        StringBuilder builder = new StringBuilder();
        JSONObject jsonObject = new JSONObject();
        String inputLine = null;
        try {
            request.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader();
            while ((inputLine = reader.readLine()) != null) {
                builder.append(inputLine);
            }
            reader.close();
            jsonObject = (JSONObject) JSONValue.parseWithException(builder.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        Long id = (Long) jsonObject.get("id");
        String name = (String) jsonObject.get("name");
        String description = (String) jsonObject.get("description");
        Long priceLong = (Long) jsonObject.get("price");
        Long amount = (Long) jsonObject.get("amount");
        BigDecimal price = BigDecimal.valueOf(priceLong);

        dataSource.updateExitingProduct(id, name, description, price, amount);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.valueOf(request.getParameter("id"));
        DataSource dataSource = new DataSource();
        dataSource.deleteOneProduct(id);
    }
}
