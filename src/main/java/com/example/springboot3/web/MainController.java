package com.example.springboot3.web;

import com.example.springboot3.Dto.CategoryItemDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.PromotionItemDTO;
import com.example.springboot3.Dto.response.CategoryResponseDTO;
import com.example.springboot3.Dto.response.DisplayInfoResponseDTO;
import com.example.springboot3.Dto.response.PromotionResponseDTO;
import com.example.springboot3.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ReservationService reservationService;

    @GetMapping("/api/categories")
    public CategoryResponseDTO categoryResponse() {
        List<CategoryItemDTO> categoryWithProductCount = reservationService.getCategoryInfoWithProductCount();
        return new CategoryResponseDTO(categoryWithProductCount.size(),
            categoryWithProductCount);

    }

    @GetMapping("/api/displayInfos")
    public DisplayInfoResponseDTO displayInfos(
        @RequestParam(value = "categoryId", required = false) int categoryId,
        @RequestParam(value = "start", defaultValue = "0") int start) {

        int allDisplayInfoCount = reservationService.getAllDisplayInfoCount(categoryId);
        List<DisplayInfoItemDTO> displayInfoWithStartNumber = reservationService.getDisplayInfoWithStartNumber(
            categoryId, start);

        return new DisplayInfoResponseDTO(allDisplayInfoCount,
            displayInfoWithStartNumber.size(), displayInfoWithStartNumber);
    }

    @GetMapping("/api/promotions")
    public PromotionResponseDTO promotionList() {
        List<PromotionItemDTO> promotionItems = reservationService.getPromotionItems();

        return new PromotionResponseDTO(promotionItems.size(), promotionItems);
    }

}
