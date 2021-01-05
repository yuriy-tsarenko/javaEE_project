package com.javaee.example.controller;

import com.javaee.example.datasource.OrientDbConnector;
import com.javaee.example.dto.ProductDto;

import com.javaee.example.entity.Product;
import com.javaee.example.mapper.ProductMapper;
import com.javaee.example.query_builder.QueryBuilder;
import com.javaee.example.util.CustomResponseBody;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

import static com.javaee.example.query_builder.Keywords.SPACE;
import static com.javaee.example.query_builder.Keywords.VALUES;
import static com.javaee.example.util.Constants.PRODUCT_CLASS;

@Path("/product")
public class ProductController {

    @EJB
    OrientDbConnector connector;

    @EJB
    QueryBuilder builder;

    @EJB
    ProductMapper mapper;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ProductDto> getAll() {
        List<ProductDto> products = null;
        try {
            products = mapper.mapEntityToDto(connector.select(builder.select(PRODUCT_CLASS), new Product()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public CustomResponseBody addOne(ProductDto dto) {
        try {
            boolean insert = connector.insert(builder.insert(PRODUCT_CLASS, SPACE,
                    "(name, description, price, amount)", VALUES, SPACE,
                    "('", dto.getName(), "', '", dto.getDescription(), "', '", dto.getPrice(), "', '",
                    dto.getAmount(), "')"));
            if (insert) {
                return new CustomResponseBody("POST", "success");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new CustomResponseBody("POST", "task failed");
    }
}
