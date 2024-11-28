package com.example.springboot3.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.PricesRequestDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import com.example.springboot3.service.ReservationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ReservationControllerTest {

    @Autowired
    private ReservationService reservationService;

    @Test
    @DisplayName("실제 DB 연동해서 결과확인")
    public void testSaveReservation() throws JsonProcessingException {
        // Given: 실제 데이터 생성
        ReservationInfosRequestDTO request = new ReservationInfosRequestDTO(
            1, LocalDate.now(), 1, 1, List.of(new PricesRequestDTO(6, 2)));

        // When: 서비스 계층을 호출
        ReservationInfoResponseDTO response = reservationService.saveAndReturnReservationResponse(request);
        ObjectMapper objectMapper = new ObjectMapper();

        // Then: 데이터베이스에 값이 저장되었는지 확인
        assertNotNull(response);
        assertEquals(response.getProductId(), 1);
        assertEquals(response.getCancelFlag(), 0);
        assertEquals(response.getDisplayInfoId(), 1);
        assertEquals(response.getUserId(), 1);
        List<ReservationInfoPriceDTO> prices = response.getPrices();
        assertEquals(prices.getFirst().getProductPriceId(), 2);
        assertEquals(prices.getFirst().getCount(), 6);

    }

}
