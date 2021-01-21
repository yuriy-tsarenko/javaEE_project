package com.javaee.example.mapper;

import com.javaee.example.dto.ProductDto;
import com.javaee.example.entity.Product;

import java.util.List;

public interface ProductMapper {

    /**
     * @param list list of products
     * @return list of products dto
     */
    List<ProductDto> mapEntityToDto(List<Product> list);

    /**
     * @param dto - data transfer object
     * @return Product
     */
    Product mapDtoToEntity(ProductDto dto);
}
