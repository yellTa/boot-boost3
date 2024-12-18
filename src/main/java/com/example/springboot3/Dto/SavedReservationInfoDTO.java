package com.example.springboot3.Dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SavedReservationInfoDTO {
	private int reservationId;
	private int productId;
	private int displayInfoId;
	private int cancelFlag;
	private String productDescription;
	private String productContent;
	private Timestamp reservationDate;
	private Timestamp createDate;
	private Timestamp modifyDate;
}
