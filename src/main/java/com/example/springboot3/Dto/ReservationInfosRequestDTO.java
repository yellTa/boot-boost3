package com.example.springboot3.Dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationInfosRequestDTO {

    int userId;
    LocalDate reservationYearMonthDay;
    int displayInfo;
    int productId;
    List<Prices> prices;
}
