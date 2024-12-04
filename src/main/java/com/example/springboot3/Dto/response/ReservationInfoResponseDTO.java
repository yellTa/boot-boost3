package com.example.springboot3.Dto.response;

import java.sql.Timestamp;
import java.util.List;

import com.example.springboot3.Dto.JPAEntity.ReservationInfoPriceDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class ReservationInfoResponseDTO {

	private int id;
	private int productId;
	private int cancelFlag;
	private int displayInfoId;
	private int userId;
	private Timestamp reservationDate;
	private Timestamp createDate;
	private Timestamp modifyDate;
	private List<ReservationInfoPriceDTO> prices;

	public void setPrices(List<ReservationInfoPriceDTO> prices) {
		this.prices = prices;
	}
}
