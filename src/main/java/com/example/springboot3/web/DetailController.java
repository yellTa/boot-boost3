package com.example.springboot3.web;

import com.example.springboot3.Dto.detailApi.DetailResponseDTO;
import com.example.springboot3.Dto.detailApi.DisplayInfoImageDTO;
import com.example.springboot3.Dto.detailApi.ProductImageDTO;
import com.example.springboot3.Dto.detailApi.ProductPriceDTO;
import com.example.springboot3.Dto.displayInfoApi.DisplayInfoItemDTO;
import com.example.springboot3.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DetailController {

    private final ReservationService reservationService;

    @GetMapping("/api/displayinfos")
    public DetailResponseDTO detailedDisplayInfo(
        @RequestParam(value = "displayId", required = false) int displayId) {

        DisplayInfoItemDTO product = reservationService.getDisplayInfoWithDisplayId(displayId);
        List<ProductImageDTO> productImages = reservationService.getProductImageWithDisplayId(displayId);
        List<DisplayInfoImageDTO> displayInfoImages = reservationService.getDisplayInfoImageWithDisplayId(displayId);
        int avgScore = reservationService.getAvgScoreWithDisplayId(displayId);
        List<ProductPriceDTO> productPrices = reservationService.getProductPriceWithDisplayId(displayId);

        DetailResponseDTO detailResponseDTO = new DetailResponseDTO(product, productImages, displayInfoImages, avgScore,
            productPrices);

        return detailResponseDTO;
    }

}
