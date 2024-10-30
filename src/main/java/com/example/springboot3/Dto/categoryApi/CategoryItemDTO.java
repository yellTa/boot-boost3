package com.example.springboot3.Dto.categoryApi;

import lombok.Getter;

@Getter
public class CategoryItemDTO {

    private int id;
    private String name;
    private int count;

    public CategoryItemDTO(int id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }
}
