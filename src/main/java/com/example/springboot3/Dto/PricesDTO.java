package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PricesDTO {

    int reservationInfoId;
    int productPriceId;
    int count;
}
