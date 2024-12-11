package com.example.springboot3.web;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.springboot3.Dto.PricesRequestDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@Transactional
class ReservationControllerTest {

	private static long startTime;
	@Autowired
	private ReservationController reservationController;

	@BeforeAll
	public static void startTest() {
		startTime = System.nanoTime();
	}

	@AfterAll
	public static void endTest() {
		long endTime = System.nanoTime();
		System.out.println("총 수행 시간 = " + (endTime - startTime) / 1_000_000 + " ms");
	}

	@Test
	@DisplayName("실제 DB 연동해서 결과확인")
	public void testSaveReservation() throws JsonProcessingException {
		// Given: 실제 데이터 생성
		ReservationInfosRequestDTO request = new ReservationInfosRequestDTO(1, LocalDate.now(), 2, 1, List.of(new PricesRequestDTO(3, 2)));

		// When: 서비스 계층을 호출
		Object body = reservationController.doReservation(request)
										   .getBody();
		ObjectMapper objectMapper = new ObjectMapper();

		String jsonString = objectMapper.writeValueAsString(body);
		// System.out.println(jsonString);
		ReservationInfoResponseDTO res = objectMapper.readValue(jsonString, ReservationInfoResponseDTO.class);

		// Then: 데이터베이스에 값이 저장되었는지 확인
		assertThat(res.getProductId()).isEqualTo(1);
		assertThat(res.getCancelFlag()).isEqualTo(0);
		assertThat(res.getDisplayInfoId()).isEqualTo(2);
		assertThat(res.getUserId()).isEqualTo(1);
		assertThat(res.getPrices()
					  .getFirst()
					  .getCount()).isEqualTo(3);
	}

}
