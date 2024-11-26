package com.example.springboot3.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation_info_price")
@Getter
@NoArgsConstructor
public class ReservationInfoPriceDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int reservationInfoId;
    int productPriceId;
    int count;

    public ReservationInfoPriceDTO(int reservationInfoId, int productPriceId, int count) {
        this.reservationInfoId = reservationInfoId;
        this.productPriceId = productPriceId;
        this.count = count;
    }

}
