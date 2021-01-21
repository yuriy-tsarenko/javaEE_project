package com.javaee.example.entity;

import com.javaee.example.datasource.db_connector.ClassBuilder;
import lombok.Data;

import java.util.Map;

import static com.javaee.example.util.CollectionUtil.mapOf;

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
        if (fields.containsKey("@rid") && fields.get("@rid") != null) {
            product.setRid(fields.get("@rid").toString());
        }
        if (fields.containsKey("name") && fields.get("name") != null) {
            product.setName(fields.get("name").toString());
        }
        if (fields.containsKey("description") && fields.get("description") != null) {
            product.setDescription(fields.get("description").toString());
        }
        if (fields.containsKey("price") && fields.get("price") != null) {
            product.setPrice(fields.get("price").toString());
        }
        if (fields.containsKey("amount") && fields.get("amount") != null) {
            product.setAmount(fields.get("amount").toString());
        }
        return product;
    }

    @Override
    public Map<String, Object> getFieldMap() {
        return mapOf("@rid", rid, "name", name, "description", description, "price", price, "amount", amount);
    }
}
