package com.example.springboot3.Dto.response;

import com.example.springboot3.Dto.ReservationInfoPriceDTO;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationResponseDTO {

    int id;
    int productId;
    int cancelFlag;
    int displayInfoId;
    int userId;
    Instant reservationDate;
    Instant createDate;
    Instant modifyDate;
    List<ReservationInfoPriceDTO> prices;
}
