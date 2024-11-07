package com.example.springboot3.service;

import com.example.springboot3.Dao.ReservationDao;
import com.example.springboot3.Dto.CategoryItemDTO;
import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
import com.example.springboot3.Dto.PromotionItemDTO;
import com.example.springboot3.Dto.ReservationUserCommentDTO;
import com.example.springboot3.Dto.ReservationUserCommentImageDTO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDao reservationDao;

    public List<CategoryItemDTO> getCategoryInfoWithProductCount() {
        return reservationDao.getCategoryWithProductCount();
    }

    public List<DisplayInfoItemDTO> getDisplayInfoWithStartNumber(int categoryId, int start) {

        List<DisplayInfoItemDTO> result = new ArrayList<>();
        if (categoryId == 0) {
            result = reservationDao.getAllDisplayItemInfo();
        }
        if (categoryId != 0) {
            result = reservationDao.getDisplayItemInfo(categoryId, start);
        }
        return result;
    }

    public int getAllDisplayInfoCount(int categoryId) {
        int result = 0;
        if (categoryId == 0) {
            result = reservationDao.getAllDisplayItemInfoCount();
        }
        if (categoryId != 0) {
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

        for (ReservationUserCommentDTO reservationUserComment : reservationUserComments) {
            int reservationInfoId = reservationUserComment.getReservationInfoId();
            List<ReservationUserCommentImageDTO> reservationUserCommentImages = reservationDao.getReservationUserCommentImageWithReservationInfoId(
                reservationInfoId);

            reservationUserComment.setReservationUserCommentImages(reservationUserCommentImages);
        }
        return reservationUserComments;
    }

}
