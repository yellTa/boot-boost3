package com.example.springboot3.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductPriceDTO {

    private int id;
    private int productId;
    private String priceTypeName;
    private int price;
    private int discountRate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime modifyDate;

}
