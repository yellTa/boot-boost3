package com.example.springboot3.service;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReservationServiceTest {
	@Autowired
	private ReservationService reservationService;

	@Test
	@DisplayName("User_reservation에서 reservation_info_id추출")
	public void test() {
		List<Integer> reservationList = reservationService.getReservationIdList(1);
		System.out.println("the result  =  " + reservationList.toString());

	}
}