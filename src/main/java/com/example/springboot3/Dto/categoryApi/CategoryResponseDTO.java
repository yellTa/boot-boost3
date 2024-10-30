package com.example.springboot3.Dto.categoryApi;

import java.util.List;
import lombok.Getter;

@Getter
public class CategoryResponseDTO {

    private int size;
    private List<CategoryItemDTO> items;

    public CategoryResponseDTO(int size, List<CategoryItemDTO> items) {
        this.size = size;
        this.items = items;
    }
}
