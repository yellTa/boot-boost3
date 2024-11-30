package com.example.springboot3.Dao;

import static com.example.springboot3.Const.MAIN_PICTURE;
import static com.example.springboot3.TableAlias.C;
import static com.example.springboot3.TableAlias.DI;
import static com.example.springboot3.TableAlias.DII;
import static com.example.springboot3.TableAlias.F;
import static com.example.springboot3.TableAlias.P;
import static com.example.springboot3.TableAlias.PI;
import static com.example.springboot3.TableAlias.PR;
import static com.example.springboot3.TableAlias.PRO;
import static com.example.springboot3.TableAlias.RI;
import static com.example.springboot3.TableAlias.RUC;
import static com.example.springboot3.TableAlias.RUCI;
import static com.example.springboot3.TableAlias.U;
import static com.example.springboot3.TableAlias.UR;
import static org.jooq.impl.DSL.count;

import com.example.springboot3.Dao.repository.ReservationInfoPriceRepository;
import com.example.springboot3.Dao.repository.ReservationInfoRepository;
import com.example.springboot3.Dao.repository.UserReservationRepository;
import com.example.springboot3.Dto.CategoryItemDTO;
import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.JPAEntity.UserReservationDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
import com.example.springboot3.Dto.PromotionItemDTO;
import com.example.springboot3.Dto.ReservationUserCommentDTO;
import com.example.springboot3.Dto.ReservationUserCommentImageDTO;
import com.example.springboot3.Dto.UserDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReservationDao {

    private final DSLContext dsl;
    private final ReservationInfoRepository reservationInfoRepository;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;
    private final UserReservationRepository userReservationRepository;

    public List<CategoryItemDTO> getCategoryWithProductCount() {
        return dsl.select(
                      C.field("id")
                       .as("id"),
                      C.field("name")
                       .as("name"),
                      count(P.field("id")).as("count")
                  )
                  .from(C)
                  .leftJoin(P)
                  .on(C.field(
                           "id",
                           Integer.class
                       )
                       .eq(P.field(
                           "category_id",
                           Integer.class
                       )))
                  .groupBy(
                      C.field("id"),
                      C.field("name")
                  )
                  .fetchInto(CategoryItemDTO.class);
    }

    public List<DisplayInfoItemDTO> getDisplayItemInfo(int categoryId, int start) {
        return dsl.select(
                      DI.field("id")
                        .as("id"),
                      C.field("id")
                       .as("categoryId"),
                      DI.field("id")
                        .as("displayInfo"),
                      C.field("name")
                       .as("name"),
                      P.field("description")
                       .as("description"),
                      P.field("content")
                       .as("content"),
                      P.field("event")
                       .as("event"),
                      DI.field("opening_hours")
                        .as("openingHours"),
                      DI.field("place_name")
                        .as("placeName"),
                      DI.field("place_lot")
                        .as("placeLot"),
                      DI.field("place_street")
                        .as("placeStreet"),
                      DI.field("tel")
                        .as("tel"),
                      DI.field("homepage")
                        .as("homepage"),
                      DI.field("email")
                        .as("email"),
                      DI.field("create_date")
                        .as("createDate"),
                      DI.field("modify_date")
                        .as("modifyDate"),
                      PI.field("file_id")
                        .as("fileId")
                  )
                  .from(DI)
                  .join(P)
                  .on(DI.field(
                            "product_id",
                            Integer.class
                        )
                        .eq(P.field(
                            "id",
                            Integer.class
                        )))
                  .join(C)
                  .on(P.field(
                           "category_id",
                           Integer.class
                       )
                       .eq(C.field(
                           "id",
                           Integer.class
                       )))
                  .leftJoin(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .where(C.field(
                              "id",
                              Integer.class
                          )
                          .eq(categoryId))
                  .and(PI.field(
                             "type",
                             String.class
                         )
                         .eq(MAIN_PICTURE))
                  .limit(4)
                  .offset(start)
                  .fetchInto(DisplayInfoItemDTO.class);
    }

    public List<DisplayInfoItemDTO> getAllDisplayItemInfo() {
        return dsl.select(
                      DI.field("id")
                        .as("id"),
                      C.field("id")
                       .as("categoryId"),
                      DI.field("id")
                        .as("displayInfo"),
                      C.field("name")
                       .as("name"),
                      P.field("description")
                       .as("description"),
                      P.field("content")
                       .as("content"),
                      P.field("event")
                       .as("event"),
                      DI.field("opening_hours")
                        .as("openingHours"),
                      DI.field("place_name")
                        .as("placeName"),
                      DI.field("place_lot")
                        .as("placeLot"),
                      DI.field("place_street")
                        .as("placeStreet"),
                      DI.field("tel")
                        .as("tel"),
                      DI.field("homepage")
                        .as("homepage"),
                      DI.field("email")
                        .as("email"),
                      DI.field("create_date")
                        .as("createDate"),
                      DI.field("modify_date")
                        .as("modifyDate"),
                      PI.field("file_id")
                        .as("fileId")
                  )
                  .from(DI)
                  .join(P)
                  .on(DI.field(
                            "product_id",
                            Integer.class
                        )
                        .eq(P.field(
                            "id",
                            Integer.class
                        )))
                  .leftJoin(C)
                  .on(P.field(
                           "category_id",
                           Integer.class
                       )
                       .eq(C.field(
                           "id",
                           Integer.class
                       )))
                  .leftJoin(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .where(PI.field(
                               "type",
                               String.class
                           )
                           .eq(MAIN_PICTURE))
                  .fetchInto(DisplayInfoItemDTO.class);
    }

    public int getCategoryDisplayItemInfoCount(int categoryId) {
        return dsl.selectCount()
                  .from(DI)
                  .join(P)
                  .on(DI.field(
                            "product_id",
                            Integer.class
                        )
                        .eq(P.field(
                            "id",
                            Integer.class
                        )))
                  .leftJoin(C)
                  .on(P.field(
                           "category_id",
                           Integer.class
                       )
                       .eq(C.field(
                           "id",
                           Integer.class
                       )))
                  .leftJoin(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .where(C.field(
                              "id",
                              Integer.class
                          )
                          .eq(categoryId)
                          .and(PI.field(
                                     "type",
                                     String.class
                                 )
                                 .eq(MAIN_PICTURE)))
                  .fetchOne(
                      0,
                      int.class
                  );

    }

    public int getAllDisplayItemInfoCount() {
        return dsl.selectCount()
                  .from(DI)
                  .join(P)
                  .on(DI.field(
                            "product_id",
                            Integer.class
                        )
                        .eq(P.field(
                            "id",
                            Integer.class
                        )))
                  .leftJoin(C)
                  .on(P.field(
                           "category_id",
                           Integer.class
                       )
                       .eq(C.field(
                           "id",
                           Integer.class
                       )))
                  .leftJoin(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .where(PI.field(
                               "type",
                               String.class
                           )
                           .eq(MAIN_PICTURE))
                  .fetchOne(
                      0,
                      int.class
                  );

    }

    public List<PromotionItemDTO> getPromotionItemInfo() {
        return dsl.select(
                      PRO.field("id")
                         .as("id"),
                      P.field("id")
                       .as("productId"),
                      C.field("id")
                       .as("categoryId"),
                      C.field("name")
                       .as("categoryName"),
                      P.field("description")
                       .as("description"),
                      PI.field("file_id")
                        .as("field")
                  )
                  .from(C)
                  .join(P)
                  .on(P.field(
                           "category_id",
                           Integer.class
                       )
                       .eq(C.field(
                           "id",
                           Integer.class
                       )))
                  .join(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .join(PRO)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PRO.field(
                           "product_id",
                           Integer.class
                       )))
                  .where(PI.field(
                               "type",
                               String.class
                           )
                           .eq(MAIN_PICTURE))
                  .fetchInto(PromotionItemDTO.class);
    }

    public DisplayInfoItemDTO getDisplayInfoItemWithDisplayId(int displayId) {
        return dsl.select(
                      DI.field("id")
                        .as("id"),
                      C.field("id")
                       .as("categoryId"),
                      DI.field("id")
                        .as("displayInfo"),
                      C.field("name")
                       .as("name"),
                      P.field("description")
                       .as("description"),
                      P.field("content")
                       .as("content"),
                      P.field("event")
                       .as("event"),
                      DI.field("opening_hours")
                        .as("openingHours"),
                      DI.field("place_name")
                        .as("placeName"),
                      DI.field("place_lot")
                        .as("placeLot"),
                      DI.field("place_street")
                        .as("placeStreet"),
                      DI.field("tel")
                        .as("tel"),
                      DI.field("homepage")
                        .as("homepage"),
                      DI.field("email")
                        .as("email"),
                      DI.field("create_date")
                        .as("createDate"),
                      DI.field("modify_date")
                        .as("modifyDate"),
                      PI.field("file_id")
                        .as("fileId")
                  )
                  .from(DI)
                  .join(P)
                  .on(DI.field(
                            "product_id",
                            Integer.class
                        )
                        .eq(P.field(
                            "id",
                            Integer.class
                        )))
                  .join(C)
                  .on(P.field(
                           "category_id",
                           Integer.class
                       )
                       .eq(C.field(
                           "id",
                           Integer.class
                       )))
                  .leftJoin(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       ))
                       .and(PI.field(
                                  "type",
                                  String.class
                              )
                              .eq(MAIN_PICTURE)))
                  .where(P.field(
                              "id",
                              Integer.class
                          )
                          .eq(displayId))
                  .fetchOneInto(DisplayInfoItemDTO.class);
    }

    public List<ProductImageDTO> getProductImageWithDisplayId(int displayId) {
        return dsl.select(
                      P.field("id")
                       .as("productId"),
                      PI.field("id")
                        .as("productImageId"),
                      PI.field("type")
                        .as("type"),
                      PI.field("file_id")
                        .as("fileInfoId"),
                      F.field("file_name")
                       .as("fileName"),
                      F.field("save_file_name")
                       .as("saveFileName"),
                      F.field("content_type")
                       .as("contentType"),
                      F.field("delete_flag")
                       .as("deleteFlag"),
                      F.field("create_date")
                       .as("createDate"),
                      F.field("modify_date")
                       .as("modifyDate")
                  )
                  .from(PI)
                  .join(P)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .leftJoin(F)
                  .on(F.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "file_id",
                           Integer.class
                       )))
                  .where(P.field(
                              "id",
                              Integer.class
                          )
                          .eq(displayId)
                          .and(PI.field(
                                     "type",
                                     String.class
                                 )
                                 .eq(MAIN_PICTURE)))
                  .fetchInto(ProductImageDTO.class);
    }

    public List<DisplayInfoImageDTO> getDisplayInfoImages(int displayId) {
        return dsl.select(
                      P.field("id")
                       .as("id"),
                      DI.field("id")
                        .as("displayInfoId"),
                      DII.field("file_id")
                         .as("fileId"),
                      F.field("file_name")
                       .as("fileName"),
                      F.field("save_file_name")
                       .as("saveFileName"),
                      F.field("content_type")
                       .as("contentType"),
                      F.field("delete_flag")
                       .as("deleteFlag"),
                      F.field("create_date")
                       .as("createDate"),
                      F.field("modify_date")
                       .as("modifyDate")
                  )
                  .from(P)
                  .join(DI)
                  .on(DI.field(
                            "id",
                            Integer.class
                        )
                        .eq(P.field(
                            "id",
                            Integer.class
                        )))
                  .join(DII)
                  .on(DI.field(
                            "id",
                            Integer.class
                        )
                        .eq(DII.field(
                            "display_info_id",
                            Integer.class
                        )))
                  .join(PI)
                  .on(P.field(
                           "id",
                           Integer.class
                       )
                       .eq(PI.field(
                           "product_id",
                           Integer.class
                       )))
                  .leftJoin(F)
                  .on(F.field(
                           "id",
                           Integer.class
                       )
                       .eq(DII.field(
                           "file_id",
                           Integer.class
                       )))
                  .where(P.field(
                              "id",
                              Integer.class
                          )
                          .eq(displayId)
                          .and(PI.field(
                                     "type",
                                     String.class
                                 )
                                 .eq(MAIN_PICTURE)))
                  .fetchInto(DisplayInfoImageDTO.class);
    }

    public int getAvgScoreOfReservationUserComment(int displayId) {
        return dsl.select(DSL.floor(DSL.avg(RUC.field(
                                 "score",
                                 Float.class
                             )))
                             .as("average_score"))
                  .from(RUC)
                  .where(RUC.field(
                                "product_id",
                                Integer.class
                            )
                            .eq(displayId))
                  .fetchOne(
                      0,
                      int.class
                  );
    }

    public List<ProductPriceDTO> getProductPricesOrderByRateDesc(int displayId) {
        return dsl.select(
                      PR.field("id")
                        .as("id"),
                      PR.field("product_id")
                        .as("productId"),
                      PR.field("price_type_name")
                        .as("priceTypeName"),
                      PR.field("price")
                        .as("price"),
                      PR.field("discount_rate")
                        .as("discountRate"),
                      PR.field("create_date")
                        .as("createDate"),
                      PR.field("modify_date")
                        .as("modifyDate")
                  )
                  .from(PR)
                  .where(PR.field(
                               "product_id",
                               Integer.class
                           )
                           .eq(displayId))
                  .orderBy(PR.field("discount_rate")
                             .desc())
                  .fetchInto(ProductPriceDTO.class);
    }

    public int getCommentTotalCount() {
        return dsl.selectCount()
                  .from(RUC)
                  .fetchOne(
                      0,
                      int.class
                  );
    }

    public List<ReservationUserCommentDTO> getReservationUserCommentWithProductId(int productId, int start) {
        return dsl.select(
                      RUC.field("reservation_info_id")
                         .as("id"),
                      RI.field("product_id")
                        .as("productId"),
                      RUC.field("id")
                         .as("reservationInfoId"),
                      RUC.field("score")
                         .as("score"),
                      RI.field("reservation_email")
                        .as("email"),
                      RUC.field("comment")
                         .as("comment"),
                      RUC.field("create_date")
                         .as("createDate"),
                      RUC.field("modify_date")
                         .as("modifyDate")
                  )
                  .from(RI)
                  .join(RUC)
                  .on(RI.field(
                            "product_id",
                            Integer.class
                        )
                        .eq(RUC.field(
                            "product_id",
                            Integer.class
                        ))
                        .and(RI.field(
                                   "id",
                                   Integer.class
                               )
                               .eq(RUC.field(
                                   "id",
                                   Integer.class
                               ))))
                  .where(RI.field(
                               "product_id",
                               Integer.class
                           )
                           .eq(productId))
                  .orderBy(RUC.field(
                                  "reservation_info_id",
                                  Integer.class
                              )
                              .desc())
                  .limit(5)
                  .offset(start)
                  .fetchInto(ReservationUserCommentDTO.class);
    }

    public List<ReservationUserCommentImageDTO> getReservationUserCommentImageWithReservationInfoId(
        int reservationInfoId
    ) {
        return dsl.selectFrom(RUCI)
                  .where(RUCI.RESERVATION_INFO_ID.eq(reservationInfoId))
                  .fetchInto(ReservationUserCommentImageDTO.class);
    }

    public UserDTO getReservedUserInfo(int userId) {
        return dsl.select(
                      U.TEL.as("tel"),
                      U.EMAIL.as("email"),
                      U.NAME.as("name")
                  )
                  .from(U)
                  .where(U.ID.eq(userId))
                  .fetchOneInto(UserDTO.class);
    }

    public int saveReservationInfo(ReservationInfoDTO reservationInfo) {
        ReservationInfoDTO savedReservationInfo = reservationInfoRepository.save(reservationInfo);
        return savedReservationInfo.getId();
    }

    public void saveReservationInfoPrice(ReservationInfoPriceDTO reservationInfoPrice) {
        reservationInfoPriceRepository.save(reservationInfoPrice);
    }

    public void saveUserReservationInfo(UserReservationDTO userReservationInfo) {
        userReservationRepository.save(userReservationInfo);
    }

    public ReservationInfoResponseDTO getSavedReservationResult(int reservationInfoId) {
        return dsl.selectDistinct(
                      RI.ID.as("id"),
                      RI.PRODUCT_ID.as("productId"),
                      RI.CANCEL_FLAG.as("cancelFlag"),
                      RI.DISPLAY_INFO_ID.as("displayInfoId"),
                      UR.USER_ID.as("userId"),
                      RI.RESERVATION_DATE.as("reservationDate"),
                      RI.CREATE_DATE.as("createDate"),
                      RI.MODIFY_DATE.as("modifyDate")
                  )
                  .from(RI)
                  .join(UR)
                  .on(RI.ID.eq(reservationInfoId))
                  .fetchOneInto(ReservationInfoResponseDTO.class);
    }

    public ReservationInfoPriceDTO getReservationPriceInfo(int savedReservationInfoId) {
        return reservationInfoPriceRepository.findByReservationInfoId(savedReservationInfoId);
    }
}
