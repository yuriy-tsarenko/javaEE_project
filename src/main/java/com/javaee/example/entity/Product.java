package com.javaee.example.entity;

import com.javaee.example.datasource.ClassBuilder;
import lombok.Data;

import java.util.Map;

@Data
public class Product implements ClassBuilder<Product> {

    private String rid;
    private String name;
    private String description;
    private String price;
    private String amount;

    @Override
    public Product build(Map<String, Object> fields) {
        Product product = new Product();
        if (fields.containsKey("@rid")) {
            product.setRid(fields.get("@rid").toString());
        }
        if (fields.containsKey("name")) {
            product.setName(fields.get("name").toString());
        }
        if (fields.containsKey("description")) {
            product.setDescription(fields.get("description").toString());
        }
        if (fields.containsKey("price")) {
            product.setPrice(fields.get("price").toString());
        }
        if (fields.containsKey("amount")) {
            product.setAmount(fields.get("amount").toString());
        }
        return product;
    }
}
