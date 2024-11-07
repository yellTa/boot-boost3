package com.example.springboot3.Dto.detailApi;

import com.example.springboot3.Dto.displayInfoApi.DisplayInfoItemDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailResponseDTO {

    private DisplayInfoItemDTO product;
    private List<ProductImageDTO> productImages;
    private List<DisplayInfoImageDTO> displayInfoImages;
    private int avgScore;
    private List<ProductPriceDTO> productPrices;

}
