package com.example.springboot3.Dto.JPAEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//JPA 전용 DTO
@Entity
@Table(name = "reservation_info_price")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
