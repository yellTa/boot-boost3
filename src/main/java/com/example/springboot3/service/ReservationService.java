package com.example.springboot3.service;

import static com.example.springboot3.Const.NO_CATEGORY;

import com.example.springboot3.Dao.ReservationDao;
import com.example.springboot3.Dao.repository.ReservationInfoPriceRepository;
import com.example.springboot3.Dto.CategoryItemDTO;
import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.JPAEntity.UserReservationDTO;
import com.example.springboot3.Dto.PricesRequestDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
import com.example.springboot3.Dto.PromotionItemDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.ReservationUserCommentDTO;
import com.example.springboot3.Dto.ReservationUserCommentImageDTO;
import com.example.springboot3.Dto.UserDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {

    private final ReservationDao reservationDao;
    private final ReservationInfoPriceRepository reservationInfoPriceRepository;

    public List<CategoryItemDTO> getCategoryInfoWithProductCount() {
        return reservationDao.getCategoryWithProductCount();
    }

    public List<DisplayInfoItemDTO> getDisplayInfoWithStartNumber(int categoryId, int start) {

        List<DisplayInfoItemDTO> result = new ArrayList<>();
        if (categoryId == NO_CATEGORY) {
            result = reservationDao.getAllDisplayItemInfo();
        }
        if (categoryId != NO_CATEGORY) {
            result = reservationDao.getDisplayItemInfo(categoryId, start);
        }
        return result;
    }

    public int getAllDisplayInfoCount(int categoryId) {
        int result = NO_CATEGORY;
        if (categoryId == NO_CATEGORY) {
            result = reservationDao.getAllDisplayItemInfoCount();
        }
        if (categoryId != NO_CATEGORY) {
            result = reservationDao.getCategoryDisplayItemInfoCount(categoryId);
        }
        return result;
    }

    public List<PromotionItemDTO> getPromotionItems() {
        return reservationDao.getPromotionItemInfo();
    }

    public DisplayInfoItemDTO getDisplayInfoWithDisplayId(int displayId) {
        return reservationDao.getDisplayInfoItemWithDisplayId(displayId);
    }

    public List<ProductImageDTO> getProductImageWithDisplayId(int displayId) {
        return reservationDao.getProductImageWithDisplayId(displayId);
    }

    public List<DisplayInfoImageDTO> getDisplayInfoImageWithDisplayId(int displayId) {
        return reservationDao.getDisplayInfoImages(displayId);
    }

    public int getAvgScoreWithDisplayId(int displayId) {
        return reservationDao.getAvgScoreOfReservationUserComment(displayId);
    }

    public List<ProductPriceDTO> getProductPriceWithDisplayId(int displayId) {
        return reservationDao.getProductPricesOrderByRateDesc(displayId);
    }

    public int getTotalCommentCount() {
        return reservationDao.getCommentTotalCount();
    }

    public List<ReservationUserCommentDTO> getReservationUserCommentWithProductId(int productId, int start) {
        List<ReservationUserCommentDTO> reservationUserComments = reservationDao.getReservationUserCommentWithProductId(
            productId, start);

        reservationUserComments.forEach(reservationUserComment -> {
            int reservationInfoId = reservationUserComment.getReservationInfoId();
            List<ReservationUserCommentImageDTO> reservationUserCommentImages = reservationDao.getReservationUserCommentImageWithReservationInfoId(
                reservationInfoId);
            reservationUserComment.setReservationUserCommentImages(reservationUserCommentImages);
        });

        return reservationUserComments;
    }

    @Transactional
    public ReservationInfoResponseDTO saveAndReturnReservationResponse(
        ReservationInfosRequestDTO reservationInfosRequestDTO
    ) {

        ReservationInfoDTO reservationInfo = createReservationInfoDTO(reservationInfosRequestDTO);
        int savedReservationInfoId = reservationDao.saveReservationInfo(reservationInfo);

        ReservationInfoPriceDTO reservationInfoPrice = createReservationInfoPrice(
            reservationInfosRequestDTO,
            savedReservationInfoId
        );

        reservationDao.saveReservationInfoPrice(reservationInfoPrice);

        UserReservationDTO userReservationInfo = createUserReservationInfo(
            reservationInfosRequestDTO,
            savedReservationInfoId
        );

        reservationDao.saveUserReservationInfo(userReservationInfo);

        ReservationInfoResponseDTO savedReservationResult = reservationDao.getSavedReservationResult(
            savedReservationInfoId);

        ReservationInfoPriceDTO savedReservationPriceInfo = reservationDao.getReservationPriceInfo(
            savedReservationInfoId);

        List<ReservationInfoPriceDTO> savedReservationPriceList = new ArrayList<>();

        savedReservationPriceList.add(savedReservationPriceInfo);
        savedReservationResult.setPrices(savedReservationPriceList);
        return savedReservationResult;
    }

    private UserReservationDTO createUserReservationInfo(
        ReservationInfosRequestDTO reservationInfosRequestDTO,
        int savedReservationInfoId
    ) {
        return new UserReservationDTO(reservationInfosRequestDTO.getUserId(), savedReservationInfoId);
    }

    private ReservationInfoPriceDTO createReservationInfoPrice(
        ReservationInfosRequestDTO reservationInfosRequestDTO,
        int savedReservationInfoId
    ) {
        PricesRequestDTO price = reservationInfosRequestDTO.getPrices().getFirst();
        return new ReservationInfoPriceDTO(savedReservationInfoId, price.getProductPriceId(), price.getCount());
    }

    private ReservationInfoDTO createReservationInfoDTO(ReservationInfosRequestDTO reservationInfosRequest) {
        UserDTO reservedUser = reservationDao.getReservedUserInfo(reservationInfosRequest.getUserId());
        int productId = reservationInfosRequest.getProductId();
        int displayInfoId = reservationInfosRequest.getDisplayInfo();
        String reservationName = reservedUser.getName();
        String reservationTel = reservedUser.getTel();
        String reservationEmail = reservedUser.getEmail();
        LocalDateTime reservationDate = LocalDateTime.of(
            reservationInfosRequest.getReservationYearMonthDay(), LocalTime.now());
        LocalDateTime createDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());
        LocalDateTime modifyDate = LocalDateTime.of(LocalDate.now(), LocalTime.now());

        return new ReservationInfoDTO(
            productId, displayInfoId, reservationName, reservationTel, reservationEmail, reservationDate, createDate,
            modifyDate
        );
    }

}
