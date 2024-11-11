package com.example.springboot3.Dao;

import static com.yeji.jooq.generated.tables.Category.CATEGORY;
import static com.yeji.jooq.generated.tables.DisplayInfo.DISPLAY_INFO;
import static com.yeji.jooq.generated.tables.DisplayInfoImage.DISPLAY_INFO_IMAGE;
import static com.yeji.jooq.generated.tables.FileInfo.FILE_INFO;
import static com.yeji.jooq.generated.tables.Product.PRODUCT;
import static com.yeji.jooq.generated.tables.ProductImage.PRODUCT_IMAGE;
import static com.yeji.jooq.generated.tables.ProductPrice.PRODUCT_PRICE;
import static com.yeji.jooq.generated.tables.Promotion.PROMOTION;
import static com.yeji.jooq.generated.tables.ReservationInfo.RESERVATION_INFO;
import static com.yeji.jooq.generated.tables.ReservationUserComment.RESERVATION_USER_COMMENT;
import static com.yeji.jooq.generated.tables.ReservationUserCommentImage.RESERVATION_USER_COMMENT_IMAGE;

import com.example.springboot3.Dto.CategoryItemDTO;
import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
import com.example.springboot3.Dto.PromotionItemDTO;
import com.example.springboot3.Dto.ReservationUserCommentDTO;
import com.example.springboot3.Dto.ReservationUserCommentImageDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
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
        return dsl.selectCount()
            .from(DISPLAY_INFO)
            .join(PRODUCT)
            .on(DISPLAY_INFO.PRODUCT_ID.eq(PRODUCT.ID))
            .leftJoin(CATEGORY)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .leftJoin(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .where(CATEGORY.ID.eq(categoryId)
                .and(PRODUCT_IMAGE.TYPE.eq("ma")))
            .fetchOne(0, int.class);

    }

    public int getAllDisplayItemInfoCount() {
        return dsl.selectCount()
            .from(DISPLAY_INFO)
            .join(PRODUCT)
            .on(DISPLAY_INFO.PRODUCT_ID.eq(PRODUCT.ID))
            .leftJoin(CATEGORY)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .leftJoin(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .where(PRODUCT_IMAGE.TYPE.eq("ma"))
            .fetchOne(0, int.class);

    }

    public List<PromotionItemDTO> getPromotionItemInfo() {
        return dsl.select(
                PROMOTION.ID.as("id"),
                PRODUCT.ID.as("productId"),
                CATEGORY.ID.as("categoryId"),
                CATEGORY.NAME.as("categoryName"),
                PRODUCT.DESCRIPTION.as("description"),
                PRODUCT_IMAGE.FILE_ID.as("field")
            )
            .from(CATEGORY)
            .join(PRODUCT)
            .on(PRODUCT.CATEGORY_ID.eq(CATEGORY.ID))
            .join(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .join(PROMOTION)
            .on(PRODUCT.ID.eq(PROMOTION.PRODUCT_ID))
            .where(PRODUCT_IMAGE.TYPE.eq("ma"))
            .fetchInto(PromotionItemDTO.class);
    }

    public DisplayInfoItemDTO getDisplayInfoItemWithDisplayId(int displayId) {
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
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID)
                .and(PRODUCT_IMAGE.TYPE.eq("ma")))
            .where(PRODUCT.ID.eq(displayId))
            .fetchOneInto(DisplayInfoItemDTO.class);
    }

    public List<ProductImageDTO> getProductImageWithDisplayId(int displayId) {
        return dsl.select(
                PRODUCT.ID.as("productId"),
                PRODUCT_IMAGE.ID.as("productImageId"),
                PRODUCT_IMAGE.TYPE.as("type"),
                PRODUCT_IMAGE.FILE_ID.as("fileInfoId"),
                FILE_INFO.FILE_NAME.as("fileName"),
                FILE_INFO.SAVE_FILE_NAME.as("saveFileName"),
                FILE_INFO.CONTENT_TYPE.as("contentType"),
                FILE_INFO.DELETE_FLAG.as("deleteFlag"),
                FILE_INFO.CREATE_DATE.as("createDate"),
                FILE_INFO.MODIFY_DATE.as("modifyDate")
            )
            .from(PRODUCT_IMAGE)
            .join(PRODUCT)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .leftJoin(FILE_INFO)
            .on(FILE_INFO.ID.eq(PRODUCT_IMAGE.FILE_ID))
            .where(PRODUCT.ID.eq(displayId)
                .and(PRODUCT_IMAGE.TYPE.eq("ma")))
            .fetchInto(ProductImageDTO.class);
    }

    public List<DisplayInfoImageDTO> getDisplayInfoImages(int displayId) {
        return dsl.select(
                PRODUCT.ID.as("id"),
                DISPLAY_INFO.ID.as("displayInfoId"),
                DISPLAY_INFO_IMAGE.FILE_ID.as("fileId"),
                FILE_INFO.FILE_NAME.as("fileName"),
                FILE_INFO.SAVE_FILE_NAME.as("saveFileName"),
                FILE_INFO.CONTENT_TYPE.as("contentType"),
                FILE_INFO.DELETE_FLAG.as("deleteFlag"),
                FILE_INFO.CREATE_DATE.as("createDate"),
                FILE_INFO.MODIFY_DATE.as("modifyDate")
            )
            .from(PRODUCT)
            .join(DISPLAY_INFO)
            .on(DISPLAY_INFO.ID.eq(PRODUCT.ID))
            .join(DISPLAY_INFO_IMAGE)
            .on(DISPLAY_INFO.ID.eq(DISPLAY_INFO_IMAGE.DISPLAY_INFO_ID))
            .join(PRODUCT_IMAGE)
            .on(PRODUCT.ID.eq(PRODUCT_IMAGE.PRODUCT_ID))
            .leftJoin(FILE_INFO)
            .on(FILE_INFO.ID.eq(DISPLAY_INFO_IMAGE.FILE_ID))
            .where(PRODUCT.ID.eq(1)
                .and(PRODUCT_IMAGE.TYPE.eq("ma")))
            .fetchInto(DisplayInfoImageDTO.class);
    }

    public int getAvgScoreOfReservationUserComment(int displayId) {
        return dsl.select(DSL.floor(DSL.avg(RESERVATION_USER_COMMENT.SCORE))
                .as("average_score"))
            .from(RESERVATION_USER_COMMENT)
            .where(RESERVATION_USER_COMMENT.PRODUCT_ID.eq(1))
            .fetchOne(0, int.class);
    }

    public List<ProductPriceDTO> getProductPricesOrderByRateDesc(int displayId) {
        return dsl.select(
                PRODUCT_PRICE.ID.as("id"),
                PRODUCT_PRICE.PRODUCT_ID.as("productId"),
                PRODUCT_PRICE.PRICE_TYPE_NAME,
                PRODUCT_PRICE.PRICE,
                PRODUCT_PRICE.DISCOUNT_RATE.as("discountRate"),
                PRODUCT_PRICE.CREATE_DATE.as("createDate"),
                PRODUCT_PRICE.MODIFY_DATE.as("modifyDate")
            )
            .from(PRODUCT_PRICE)
            .where(PRODUCT_PRICE.PRODUCT_ID.eq(displayId))
            .orderBy(PRODUCT_PRICE.DISCOUNT_RATE.desc())
            .fetchInto(ProductPriceDTO.class);
    }

    public int getCommentTotalCount() {
        Integer count = dsl.selectCount()
            .from(RESERVATION_USER_COMMENT)
            .fetchOne(0, int.class);
        return count != null ? count : 0;
    }

    public List<ReservationUserCommentDTO> getReservationUserCommentWithProductId(int productId, int start) {
        return dsl.select(
                RESERVATION_USER_COMMENT.RESERVATION_INFO_ID.as("id"),
                RESERVATION_INFO.PRODUCT_ID.as("productId"),
                RESERVATION_USER_COMMENT.RESERVATION_INFO_ID.as("reservationInfoId"),
                RESERVATION_USER_COMMENT.SCORE.as("score"),
                RESERVATION_INFO.RESERVATION_EMAIL.as("email"),
                RESERVATION_USER_COMMENT.COMMENT.as("comment"),
                RESERVATION_USER_COMMENT.CREATE_DATE.as("createDate"),
                RESERVATION_USER_COMMENT.MODIFY_DATE.as("modifyDate")
            )
            .from(RESERVATION_INFO)
            .join(RESERVATION_USER_COMMENT)
            .on(RESERVATION_INFO.PRODUCT_ID.eq(RESERVATION_USER_COMMENT.PRODUCT_ID)
                .and(RESERVATION_INFO.ID.eq(RESERVATION_USER_COMMENT.RESERVATION_INFO_ID)))
            .where(RESERVATION_INFO.PRODUCT_ID.eq(productId))
            .orderBy(RESERVATION_USER_COMMENT.RESERVATION_INFO_ID.desc())
            .limit(5)
            .offset(start)
            .fetchInto(ReservationUserCommentDTO.class);
    }

    public List<ReservationUserCommentImageDTO> getReservationUserCommentImageWithReservationInfoId(
        int reservationInfoId) {
        return dsl.selectFrom(RESERVATION_USER_COMMENT_IMAGE)
            .where(RESERVATION_USER_COMMENT_IMAGE.RESERVATION_INFO_ID.eq(reservationInfoId))
            .fetchInto(ReservationUserCommentImageDTO.class);
    }
}
