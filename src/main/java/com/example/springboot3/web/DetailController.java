package com.example.springboot3.web;

import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
import com.example.springboot3.Dto.ReservationUserCommentDTO;
import com.example.springboot3.Dto.response.CommentsReponseDTO;
import com.example.springboot3.Dto.response.DetailResponseDTO;
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
    public DetailResponseDTO detailedDisplayInfo(@RequestParam(value = "displayId", required = false) int displayId) {

        DisplayInfoItemDTO product = reservationService.getDisplayInfoWithDisplayId(displayId);
        List<ProductImageDTO> productImages = reservationService.getProductImageWithDisplayId(displayId);
        List<DisplayInfoImageDTO> displayInfoImages = reservationService.getDisplayInfoImageWithDisplayId(displayId);
        int avgScore = reservationService.getAvgScoreWithDisplayId(displayId);
        List<ProductPriceDTO> productPrices = reservationService.getProductPriceWithDisplayId(displayId);

        return new DetailResponseDTO(product, productImages, displayInfoImages, avgScore,
            productPrices);

    }

    @GetMapping("/api/comments")
    public CommentsReponseDTO commentResponse(
        @RequestParam(value = "productId", required = false) int productId,
        @RequestParam(value = "start", defaultValue = "0") int start) {

        int totalCommentCount = reservationService.getTotalCommentCount();
        List<ReservationUserCommentDTO> reservationUserCommentWithProductId = reservationService.getReservationUserCommentWithProductId(
            productId, start);

        return new CommentsReponseDTO(totalCommentCount, reservationUserCommentWithProductId.size(),
            reservationUserCommentWithProductId);

    }
}
