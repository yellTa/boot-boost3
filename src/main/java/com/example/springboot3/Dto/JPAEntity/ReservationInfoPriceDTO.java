package com.example.springboot3.Dto.JPAEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JPA 전용 DTO
@Entity
@Table(name = "reservation_info_price")
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationInfoPriceDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "reservation_info_id")
    private int reservationInfoId;

    @Column(name = "product_price_id")
    private int productPriceId;

    @Column(name = "count")
    private int count;

    public ReservationInfoPriceDTO(int reservationInfoId, int productPriceId, int count) {
        this.reservationInfoId = reservationInfoId;
        this.productPriceId = productPriceId;
        this.count = count;
    }

}
