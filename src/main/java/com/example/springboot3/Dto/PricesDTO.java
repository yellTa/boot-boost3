package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PricesDTO {

    private int id;
    private int reservationInfoId;
    private int productPriceId;
    private int count;
}
