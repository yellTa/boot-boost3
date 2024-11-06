package com.example.springboot3.web;

import com.example.springboot3.Dto.categoryApi.CategoryItemDTO;
import com.example.springboot3.Dto.categoryApi.CategoryResponseDTO;
import com.example.springboot3.Dto.displayInfoApi.DisplayInfoItemDTO;
import com.example.springboot3.Dto.displayInfoApi.DisplayInfoResponseDTO;
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
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO(categoryWithProductCount.size(),
            categoryWithProductCount);
        return categoryResponseDTO;
    }

    @GetMapping("/api/displayInfos")
    public DisplayInfoResponseDTO displayInfos(
        @RequestParam(value = "categoryId", required = false) Integer categoryId,
        @RequestParam(value = "start", defaultValue = "0") int start) {

        int allDisplayInfoCount = reservationService.getAllDisplayInfoCount(categoryId);
        List<DisplayInfoItemDTO> displayInfoWithStartNumber = reservationService.getDisplayInfoWithStartNumber(
            categoryId, start);

        DisplayInfoResponseDTO displayInfoResponseDTO = new DisplayInfoResponseDTO(allDisplayInfoCount,
            displayInfoWithStartNumber.size(), displayInfoWithStartNumber);

        return displayInfoResponseDTO;
    }

}
