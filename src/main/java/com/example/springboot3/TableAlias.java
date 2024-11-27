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
import static com.yeji.jooq.generated.tables.UserReservation.USER_RESERVATION;
import static com.yeji.jooq.generated.tables.Users.USERS;

import com.yeji.jooq.generated.tables.Category;
import com.yeji.jooq.generated.tables.DisplayInfo;
import com.yeji.jooq.generated.tables.DisplayInfoImage;
import com.yeji.jooq.generated.tables.FileInfo;
import com.yeji.jooq.generated.tables.Product;
import com.yeji.jooq.generated.tables.ProductImage;
import com.yeji.jooq.generated.tables.ProductPrice;
import com.yeji.jooq.generated.tables.Promotion;
import com.yeji.jooq.generated.tables.ReservationInfo;
import com.yeji.jooq.generated.tables.ReservationUserComment;
import com.yeji.jooq.generated.tables.ReservationUserCommentImage;
import com.yeji.jooq.generated.tables.UserReservation;
import com.yeji.jooq.generated.tables.Users;

public class TableAlias {

    public static final Category C = CATEGORY.as("C");
    public static final Product P = PRODUCT.as("P");
    public static final DisplayInfo DI = DISPLAY_INFO.as("DI");
    public static final ProductImage PI = PRODUCT_IMAGE.as("PI");
    public static final Promotion PRO = PROMOTION.as("PRO");
    public static final FileInfo F = FILE_INFO.as("F");
    public static final DisplayInfoImage DII = DISPLAY_INFO_IMAGE.as("DII");
    public static final ReservationUserComment RUC = RESERVATION_USER_COMMENT.as("RUC");
    public static final ProductPrice PR = PRODUCT_PRICE.as("PR");
    public static final ReservationInfo RI = RESERVATION_INFO.as("RI");
    public static final ReservationUserCommentImage RUCI = RESERVATION_USER_COMMENT_IMAGE.as("RUCI");
    public static final Users U = USERS.as("U");
    public static final UserReservation UR = USER_RESERVATION.as("UR");

    private TableAlias() {
    }
}
