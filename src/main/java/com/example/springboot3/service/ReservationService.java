package com.example.springboot3.service;

import com.example.springboot3.Dao.ReservationDao;
import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import com.example.springboot3.Dto.displayInfoApi.DisplayInfoItemDTO;
import com.example.springboot3.Dto.promotionApi.PromotionItemDTO;
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

}
