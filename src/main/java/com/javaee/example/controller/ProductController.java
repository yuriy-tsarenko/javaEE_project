package com.javaee.example.controller;

import com.javaee.example.dto.ProductDto;
import com.javaee.example.mapper.ProductMapper;
import com.javaee.example.datasource.entity_repository.ProductRepository;
import com.javaee.example.util.CustomResponseBody;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
public class ProductController {

    @EJB
    ProductMapper mapper;

    @EJB
    ProductRepository repository;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<ProductDto> getAll() {
        return mapper.mapEntityToDto(repository.findAll());
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public CustomResponseBody addOne(ProductDto dto) {
        if (repository.addOne(mapper.mapDtoToEntity(dto))) {
            return new CustomResponseBody("POST", "success");
        }
        return new CustomResponseBody("POST", "task failed");
    }
}
