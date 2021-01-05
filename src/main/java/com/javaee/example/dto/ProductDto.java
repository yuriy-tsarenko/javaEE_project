package com.javaee.example.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductDto implements Serializable {

    private String rid;
    private String name;
    private String description;
    private String price;
    private String amount;
}