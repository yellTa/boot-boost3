package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PromotionItemDTO {

    private int id;
    private int productId;
    private int categoryId;
    private String categoryName;
    private String description;
    private int field;
}
