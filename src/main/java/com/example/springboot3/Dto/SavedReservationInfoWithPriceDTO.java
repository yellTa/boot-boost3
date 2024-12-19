package com.example.springboot3.Dto;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
public class SavedReservationInfoWithPriceDTO {
	private int id;
	private int productId;
	private int displayInfoId;
	private int cancelFlag;
	private String productDescription;
	private String productContent;
	private int userId;
	private int sumPrice;
	private Timestamp reservationDate;
	private Timestamp createDate;
	private Timestamp modifyDate;
}
