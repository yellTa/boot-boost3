package com.example.springboot3.Dao;

import static com.yeji.jooq.generated.tables.Category.CATEGORY;
import static com.yeji.jooq.generated.tables.Product.PRODUCT;

import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Result;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryApiDao {

    private final DSLContext dsl;

    public Result<Record3<Integer, String, Integer>> getCategoryWithProductCount() {
        Field<Integer> productCount = dsl.selectCount()
            .from(PRODUCT)
            .where(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .asField("count");

        return dsl.select(
                CATEGORY.ID.as("id"),
                CATEGORY.NAME.as("name"),
                productCount
            )
            .from(CATEGORY)
            .fetch();
    }
}
