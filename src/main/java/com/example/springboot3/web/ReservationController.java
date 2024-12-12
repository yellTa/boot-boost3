package com.example.springboot3.web;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.JPAEntity.UserReservationDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import com.example.springboot3.customException.SaveReservationPriceAndUserReservationException;
import com.example.springboot3.service.ReservationService;
import com.example.springboot3.service.utils.DTOMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservationInfos")
public class ReservationController {

	private final ReservationService reservationService;
	private final DTOMapper dtoMapper;

	@PostMapping
	public ResponseEntity<?> doReservation(@RequestBody ReservationInfosRequestDTO reservationParam) {
		ReservationInfoDTO savedReservationInfo = new ReservationInfoDTO();
		UserReservationDTO userReservation = new UserReservationDTO();

		try {
			ReservationInfoDTO reservationInfo = dtoMapper.createReservationInfoDTO(reservationParam);
			savedReservationInfo = reservationService.saveReservationInfo(reservationInfo);

			ReservationInfoPriceDTO reservationInfoPrice = dtoMapper.createReservationInfoPrice(reservationParam, savedReservationInfo.getId());
			userReservation = dtoMapper.createUserReservationInfo(reservationParam, savedReservationInfo.getId());
			reservationService.saveReservationInfoPriceAndUserReservation(reservationInfoPrice, userReservation);

		} catch (SaveReservationPriceAndUserReservationException e) {
			reservationService.deleteReservationInfo(savedReservationInfo);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(e.toString());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(e.toString());

		}

		ReservationInfoPriceDTO savedReservationInfoPrice = reservationService.getSavedReservationInfoPrice(savedReservationInfo.getId());
		ReservationInfoResponseDTO reservationInfoResponse = dtoMapper.createReservationInfoResponse(savedReservationInfo, savedReservationInfoPrice,
			reservationParam.getUserId());

		return ResponseEntity.status(HttpStatus.OK)
							 .body(reservationInfoResponse);

	}

	@PutMapping
	public Map<String, String> deleteReservation(@RequestBody int reservationId) {
		try {
			reservationService.cancelReservation(reservationId);
		} catch (Exception e) {
			return Map.of("result", "fail");
		}
		return Map.of("result", "success");
	}
}
