package com.example.springboot3.Dto.JPAEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JPA 전용 DTO
@Entity
@Table(name = "reservation_info")
@NoArgsConstructor
@Getter
public class ReservationInfoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productId;
    private int displayInfoId;
    private String reservationName;
    private String reservationTel;
    private String reservationEmail;
    private LocalDateTime reservationDate;
    private int cancelFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public ReservationInfoDTO(
        int productId, int displayInfoId, String reservationName, String reservationTel,
        String reservationEmail, LocalDateTime reservationDate, LocalDateTime createDate, LocalDateTime modifyDate
    ) {
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
