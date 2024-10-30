package com.example.springboot3.web;

import com.example.springboot3.Dao.CategoryApiDao;
import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import com.example.springboot3.Dto.categoryApi.CategoryResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryApiDao categoryApiDao;

    @GetMapping("/api/categories")
    public CategoryResponseDTO categoryResponse() {

        Result<Record3<Integer, String, Integer>> result = categoryApiDao.getCategoryWithProductCount();
        int count = result.size();
        List<CategoryItemDTO> dtoList = result.stream()
            .map(record -> new CategoryItemDTO(record.value1(), record.value2(), record.value3()))
            .collect(
                Collectors.toList());

        CategoryResponseDTO responseDTO = new CategoryResponseDTO(count, dtoList);
        return responseDTO;
    }
}
