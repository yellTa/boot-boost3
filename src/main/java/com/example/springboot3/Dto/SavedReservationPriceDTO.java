package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SavedReservationPriceDTO {
	private int reservationInfoId;
	private int count;
	private int price;
}
