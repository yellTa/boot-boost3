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

import org.jooq.Table;

public class TableAlias {

    public static final Table<?> C = CATEGORY;
    public static final Table<?> P = PRODUCT;
    public static final Table<?> DI = DISPLAY_INFO;
    public static final Table<?> PI = PRODUCT_IMAGE;
    public static final Table<?> PRO = PROMOTION;
    public static final Table<?> F = FILE_INFO;
    public static final Table<?> DII =  DISPLAY_INFO_IMAGE;
    public static final Table<?> RUC =  RESERVATION_USER_COMMENT;
    public static final Table<?> PR =  PRODUCT_PRICE;
    public static final Table<?> RI =  RESERVATION_INFO;
    public static final Table<?> RUCI =  RESERVATION_USER_COMMENT_IMAGE;


}
