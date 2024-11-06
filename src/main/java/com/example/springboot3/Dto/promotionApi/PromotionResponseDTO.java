package com.example.springboot3.Dto.promotionApi;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PromotionResponseDTO {

    private int size;
    private List<PromotionItemDTO> items;
}
