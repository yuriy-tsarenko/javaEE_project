package com.javaee.example.datasource.entity_repository.implementation;

import com.javaee.example.datasource.entity_repository.ProductRepository;
import com.javaee.example.datasource.generic_repository.implementation.RepositoryImpl;
import com.javaee.example.entity.Product;

import javax.ejb.Stateless;

@Stateless
public class ProductRepositoryBean extends RepositoryImpl<Product> implements ProductRepository {
    {
        createInstance(Product.class);
    }
}
