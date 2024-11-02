package com.example.springboot3.Dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import com.example.springboot3.service.CategoryService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CategoryApiDaoTest {
    @Mock
    private CategoryApiDao categoryApiDao;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategoryWithProductCount() {

        List<CategoryItemDTO> mockData = Arrays.asList(
            new CategoryItemDTO(1, "전시", 10),
            new CategoryItemDTO(2, "뮤지컬", 15),
            new CategoryItemDTO(3, "콘서트", 8)
        );

        when(categoryApiDao.getCategoryWithProductCount()).thenReturn(mockData);

        List<CategoryItemDTO> result = categoryService.getCategoryInfoWithProductCount();

        assertNotNull(result, "데이터==  null");
        assertTrue(result.size() > 0, "데이터 크기가 0");
        assertEquals(mockData.size(), result.size(), "Mock데이터와 실제 결과의 크기가 같지 않음");

        // 특정 데이터 검증
        assertEquals("전시", result.get(0).getName());
        assertEquals(10, result.get(0).getCount());

    }
}