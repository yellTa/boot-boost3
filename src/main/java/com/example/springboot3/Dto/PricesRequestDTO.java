package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PricesRequestDTO {

    private int count;
    private int productPriceId;
}
