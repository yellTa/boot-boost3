package com.example.springboot3.Dto.displayInfoApi;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DisplayInfoResponseDTO {

    private int totalCount;
    private int productCount;
    private List<DisplayInfoItemDTO> displayInfoItemList;


}
