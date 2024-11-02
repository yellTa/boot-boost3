package com.example.springboot3.Dao;

import static com.yeji.jooq.generated.tables.Category.CATEGORY;
import static com.yeji.jooq.generated.tables.Product.PRODUCT;

import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryApiDao {

    private final DSLContext dsl;

    public List<CategoryItemDTO> getCategoryWithProductCount() {
        return dsl.select(
                CATEGORY.ID.as("id"),
                CATEGORY.NAME.as("name"),
                dsl.selectCount()
                    .from(PRODUCT)
                    .where(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
                    .asField("count")
            )
            .from(CATEGORY)
            .fetchInto(CategoryItemDTO.class);
    }
}
