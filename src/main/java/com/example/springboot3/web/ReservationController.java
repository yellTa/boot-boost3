package com.example.springboot3.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.JPAEntity.UserReservationDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.SavedReservationInfoDTO;
import com.example.springboot3.Dto.SavedReservationInfoWithPriceDTO;
import com.example.springboot3.Dto.SavedReservationPriceDTO;
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

	@GetMapping
	public ResponseEntity<?> getReservation(@RequestBody int userId) {
		List<Integer> reservationList = reservationService.getReservationIdList(userId);

		List<SavedReservationInfoDTO> reservationInfo = reservationService.getSavedReservationInfo(reservationList);
		List<SavedReservationPriceDTO> reservationInfoPrice = reservationService.getReservationInfoPrice(reservationList);
		List<SavedReservationInfoWithPriceDTO> result = IntStream.range(0, reservationList.size())
																 .mapToObj(i -> dtoMapper.createSavedReservationInfo(reservationInfo.get(i),
																	 reservationInfoPrice.get(i), userId))
																 .collect(Collectors.toList());

		Map<String, Object> response = new HashMap<>();
		response.put("size", result.size());
		response.put("items", result);

		return ResponseEntity.status(HttpStatus.OK)
							 .body(response);
	}

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
