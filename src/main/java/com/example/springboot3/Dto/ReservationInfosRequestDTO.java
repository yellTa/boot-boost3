package com.example.springboot3.Dto;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationInfosRequestDTO {

    private int userId;
    private LocalDate reservationYearMonthDay;
    private int displayInfo;
    private int productId;
    private List<Prices> prices;
}
