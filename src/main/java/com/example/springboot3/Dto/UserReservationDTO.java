package com.example.springboot3.Dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserReservationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    int userId;
    int reservationInfoId;

    public UserReservationDTO(int userId, int reservationInfoId) {
        this.userId = userId;
        this.reservationInfoId = reservationInfoId;
    }
}
