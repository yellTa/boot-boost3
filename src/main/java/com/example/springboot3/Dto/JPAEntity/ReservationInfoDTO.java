package com.example.springboot3.Dto.JPAEntity;

import java.time.LocalDateTime;

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
@Table(name = "reservation_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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
}
