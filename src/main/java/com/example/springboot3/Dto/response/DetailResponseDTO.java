package com.example.springboot3.Dto.response;

import com.example.springboot3.Dto.DisplayInfoImageDTO;
import com.example.springboot3.Dto.DisplayInfoItemDTO;
import com.example.springboot3.Dto.ProductImageDTO;
import com.example.springboot3.Dto.ProductPriceDTO;
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
