package com.example.springboot3.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservation_info")
@NoArgsConstructor
@Getter
public class ReservationInfoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    int productId;
    int displayInfoId;
    String reservationName;
    String reservationTel;
    String reservationEmail;
    LocalDate reservationDate;
    int cancelFlag;
    LocalDate createDate;
    LocalDate modifyDate;

    public ReservationInfoDTO(int productId, int displayInfoId, String reservationName, String reservationTel,
        String reservationEmail, LocalDate reservationDate, LocalDate createDate, LocalDate modifyDate) {
        this.productId = productId;
        this.displayInfoId = displayInfoId;
        this.reservationName = reservationName;
        this.reservationTel = reservationTel;
        this.reservationEmail = reservationEmail;
        this.reservationDate = reservationDate;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

}
