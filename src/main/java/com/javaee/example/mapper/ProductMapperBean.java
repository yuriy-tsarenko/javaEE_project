package com.javaee.example.mapper;

import com.javaee.example.dto.ProductDto;
import com.javaee.example.entity.Product;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class ProductMapperBean implements ProductMapper {

    @Override
    public List<ProductDto> mapEntityToDto(List<Product> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(product -> {
                    ProductDto dto = new ProductDto();
                    dto.setRid(product.getRid());
                    dto.setName(product.getName());
                    dto.setDescription(product.getDescription());
                    dto.setPrice(product.getPrice());
                    dto.setAmount(product.getAmount());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Product mapDtoToEntity(ProductDto dto) {
        Product product = new Product();
        product.setRid(dto.getRid());
        product.setName(dto.getName());
        product.setAmount(dto.getAmount());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        return product;
    }
}
