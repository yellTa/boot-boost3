package com.example.springboot3.web;

import com.example.springboot3.Dto.ReservationInfosRequestDTO;
import com.example.springboot3.Dto.response.ReservationInfoResponseDTO;
import com.example.springboot3.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/api/reservationInfos")
    public ReservationInfoResponseDTO doReservation(@RequestBody ReservationInfosRequestDTO reservationParam) {
        return reservationService.saveAndReturnReservationResponse(reservationParam);
    }
}
