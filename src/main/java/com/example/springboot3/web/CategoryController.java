package com.example.springboot3.web;

import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import com.example.springboot3.Dto.categoryApi.CategoryResponseDTO;
import com.example.springboot3.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/api/categories")
    public CategoryResponseDTO categoryResponse() {
        List<CategoryItemDTO> categoryWithProductCount = categoryService.getCategoryInfoWithProductCount();
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoryWithProductCount.size(),
            categoryWithProductCount);
        return responseDTO;
    }
}
