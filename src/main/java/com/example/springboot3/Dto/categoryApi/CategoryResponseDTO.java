package com.example.springboot3.Dto.categoryApi;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryResponseDTO {

    private int size;
    private List<CategoryItemDTO> categoryItemList;

}
