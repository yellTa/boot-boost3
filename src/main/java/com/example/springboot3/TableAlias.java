package com.example.springboot3;

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
import static com.yeji.jooq.generated.tables.Users.USERS;

import com.yeji.jooq.generated.tables.Users;
import com.yeji.jooq.generated.tables.records.CategoryRecord;
import com.yeji.jooq.generated.tables.records.DisplayInfoImageRecord;
import com.yeji.jooq.generated.tables.records.DisplayInfoRecord;
import com.yeji.jooq.generated.tables.records.FileInfoRecord;
import com.yeji.jooq.generated.tables.records.ProductImageRecord;
import com.yeji.jooq.generated.tables.records.ProductPriceRecord;
import com.yeji.jooq.generated.tables.records.ProductRecord;
import com.yeji.jooq.generated.tables.records.PromotionRecord;
import com.yeji.jooq.generated.tables.records.ReservationInfoRecord;
import com.yeji.jooq.generated.tables.records.ReservationUserCommentImageRecord;
import com.yeji.jooq.generated.tables.records.ReservationUserCommentRecord;
import org.jooq.Table;

public class TableAlias {

    public static final Table<CategoryRecord> C = CATEGORY;
    public static final Table<ProductRecord> P = PRODUCT;
    public static final Table<DisplayInfoRecord> DI = DISPLAY_INFO;
    public static final Table<ProductImageRecord> PI = PRODUCT_IMAGE;
    public static final Table<PromotionRecord> PRO = PROMOTION;
    public static final Table<FileInfoRecord> F = FILE_INFO;
    public static final Table<DisplayInfoImageRecord> DII = DISPLAY_INFO_IMAGE;
    public static final Table<ReservationUserCommentRecord> RUC = RESERVATION_USER_COMMENT;
    public static final Table<ProductPriceRecord> PR = PRODUCT_PRICE;
    public static final Table<ReservationInfoRecord> RI = RESERVATION_INFO;
    public static final Table<ReservationUserCommentImageRecord> RUCI = RESERVATION_USER_COMMENT_IMAGE;
    public static final Users U = USERS.as("U");

    private TableAlias() {
    }
}
