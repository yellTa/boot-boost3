package com.example.springboot3.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoDTO;
import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;
import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import com.example.springboot3.service.ReservationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	private final ModelMapper modelMapper;

	@PostMapping("/api/reservationInfos")
	public ResponseEntity<?> doReservation(@RequestBody ReservationInfosRequestDTO reservationParam) {
		try {
			int savedReservationInfoId = reservationService.saveReservationInfo(reservationParam);
			ReservationInfoDTO savedReservationInfo = reservationService.getSavedUserReservationByReservationId(savedReservationInfoId);
			ReservationInfoPriceDTO savedReservationInfoPrice = reservationService.getSavedReservationInfoPrice(savedReservationInfoId);

			List<ReservationInfoPriceDTO> priceList = new ArrayList<>();
			priceList.add(savedReservationInfoPrice);

			ModelMapper mapper = new ModelMapper();

			mapper.createTypeMap(ReservationInfoDTO.class, ReservationInfoResponseDTO.class)
				  .setConverter(context -> {
					  ReservationInfoDTO src = context.getSource();
					  return ReservationInfoResponseDTO.builder()
													   .id(src.getId())
													   .productId(src.getProductId())
													   .cancelFlag(src.getCancelFlag())
													   .displayInfoId(src.getDisplayInfoId())
													   .userId(reservationParam.getUserId())
													   .reservationDate(Timestamp.valueOf(src.getReservationDate()))
													   .createDate(Timestamp.valueOf(src.getCreateDate()))
													   .modifyDate(Timestamp.valueOf(src.getModifyDate()))
													   .build();
				  });

			ReservationInfoResponseDTO reservationResult = mapper.map(savedReservationInfo, ReservationInfoResponseDTO.class);

			reservationResult.setPrices(priceList);
			return ResponseEntity.status(HttpStatus.OK)
								 .body(reservationResult);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
								 .body(e.toString());
		}

	}
}
