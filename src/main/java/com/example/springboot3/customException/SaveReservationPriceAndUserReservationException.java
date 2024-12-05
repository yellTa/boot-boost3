package com.example.springboot3.customException;

public class SaveReservationPriceAndUserReservationException extends RuntimeException {
	public SaveReservationPriceAndUserReservationException(String message) {
		super(message);
	}
}
