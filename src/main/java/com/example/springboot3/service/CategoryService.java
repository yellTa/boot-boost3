package com.example.springboot3.service;

import com.example.springboot3.Dao.CategoryApiDao;
import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryApiDao categoryApiDao;

    public List<CategoryItemDTO> getCategoryInfoWithProductCount(){
        return categoryApiDao.getCategoryWithProductCount();
    }

}
