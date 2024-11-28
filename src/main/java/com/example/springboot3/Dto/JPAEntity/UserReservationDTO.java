package com.example.springboot3.Dto.JPAEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JPA 전용 DTO
@Entity
@Table(name = "user_reservation")
@Getter
@NoArgsConstructor
public class UserReservationDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int reservationInfoId;

    public UserReservationDTO(int userId, int reservationInfoId) {
        this.userId = userId;
        this.reservationInfoId = reservationInfoId;
    }
}
