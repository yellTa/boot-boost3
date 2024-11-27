package com.example.springboot3.Dto.response;

import com.example.springboot3.Dto.ReservationInfoPriceDTO;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReservationInfoResponseDTO {

    private int id;
    private int productId;
    private int cancelFlag;
    private int displayInfoId;
    private int userId;
    private Timestamp reservationDate;
    private Timestamp createDate;
    private Timestamp modifyDate;
    private List<ReservationInfoPriceDTO> prices;
}
