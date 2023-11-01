package com.example.database.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer qty;
    private Long categoryID;
}
