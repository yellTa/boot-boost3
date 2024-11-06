package com.example.springboot3.Dao;

import static com.yeji.jooq.generated.tables.Category.CATEGORY;
import static com.yeji.jooq.generated.tables.DisplayInfo.DISPLAY_INFO;
import static com.yeji.jooq.generated.tables.Product.PRODUCT;
import static com.yeji.jooq.generated.tables.ProductImage.PRODUCT_IMAGE;

import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import com.example.springboot3.Dto.displayInfoApi.DisplayInfoItemDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationDao {

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

    public List<DisplayInfoItemDTO> getDisplayItemInfo(int categoryId, int start) {
        return dsl.select(
                DISPLAY_INFO.ID.as("id"),
                CATEGORY.ID.as("categoryId"),
                DISPLAY_INFO.ID.as("displayInfo"),
                CATEGORY.NAME.as("name"),
                PRODUCT.DESCRIPTION.as("description"),
                PRODUCT.CONTENT.as("content"),
                PRODUCT.EVENT.as("event"),
                DISPLAY_INFO.OPENING_HOURS.as("openingHours"),
                DISPLAY_INFO.PLACE_NAME.as("placeName"),
                DISPLAY_INFO.PLACE_LOT.as("placeLot"),
                DISPLAY_INFO.PLACE_STREET.as("placeStreet"),
                DISPLAY_INFO.TEL.as("tel"),
                DISPLAY_INFO.HOMEPAGE.as("homepage"),
                DISPLAY_INFO.EMAIL.as("email"),
                DISPLAY_INFO.CREATE_DATE.as("createDate"),
                DISPLAY_INFO.MODIFY_DATE.as("modifyDate"),
                PRODUCT_IMAGE.FILE_ID.as("fileId")
            )
            .from(DISPLAY_INFO)
            .join(PRODUCT)
            .on(DISPLAY_INFO.PRODUCT_ID.eq(PRODUCT.ID))
            .join(CATEGORY)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .leftJoin(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .where(CATEGORY.ID.eq(categoryId))
            .and(PRODUCT_IMAGE.TYPE.eq("ma"))
            .limit(4)
            .offset(start)
            .fetchInto(DisplayInfoItemDTO.class);

    }

    public List<DisplayInfoItemDTO> getAllDisplayItemInfo() {
        return dsl.select(
                DISPLAY_INFO.ID.as("id"),
                CATEGORY.ID.as("categoryId"),
                DISPLAY_INFO.ID.as("displayInfo"),
                CATEGORY.NAME.as("name"),
                PRODUCT.DESCRIPTION.as("description"),
                PRODUCT.CONTENT.as("content"),
                PRODUCT.EVENT.as("event"),
                DISPLAY_INFO.OPENING_HOURS.as("openingHours"),
                DISPLAY_INFO.PLACE_NAME.as("placeName"),
                DISPLAY_INFO.PLACE_LOT.as("placeLot"),
                DISPLAY_INFO.PLACE_STREET.as("placeStreet"),
                DISPLAY_INFO.TEL.as("tel"),
                DISPLAY_INFO.HOMEPAGE.as("homepage"),
                DISPLAY_INFO.EMAIL.as("email"),
                DISPLAY_INFO.CREATE_DATE.as("createDate"),
                DISPLAY_INFO.MODIFY_DATE.as("modifyDate"),
                PRODUCT_IMAGE.FILE_ID.as("fileId")
            )
            .from(DISPLAY_INFO)
            .join(PRODUCT)
            .on(DISPLAY_INFO.PRODUCT_ID.eq(PRODUCT.ID))
            .leftJoin(CATEGORY)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .leftJoin(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .where(PRODUCT_IMAGE.TYPE.eq("ma"))
            .fetchInto(DisplayInfoItemDTO.class);
    }

    public int getCategoryDisplayItemInfoCount(int categoryId) {
        Integer count = dsl.selectCount()
            .from(DISPLAY_INFO)
            .join(PRODUCT)
            .on(DISPLAY_INFO.PRODUCT_ID.eq(PRODUCT.ID))
            .leftJoin(CATEGORY)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .leftJoin(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .where(CATEGORY.ID.eq(categoryId)
                .and(PRODUCT_IMAGE.TYPE.eq("ma")))
            .fetchOneInto(Integer.class);

        return count != null ? count : 0;

    }

    public int getAllDisplayItemInfoCount() {
        Integer count = dsl.selectCount()
            .from(DISPLAY_INFO)
            .join(PRODUCT)
            .on(DISPLAY_INFO.PRODUCT_ID.eq(PRODUCT.ID))
            .leftJoin(CATEGORY)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .leftJoin(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .where(PRODUCT_IMAGE.TYPE.eq("ma"))
            .fetchOneInto(Integer.class);

        return count != null ? count : 0;

    }
}
