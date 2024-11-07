package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryItemDTO {

    private int id;
    private String name;
    private int count;

}
