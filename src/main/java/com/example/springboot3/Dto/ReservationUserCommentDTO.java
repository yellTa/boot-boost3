package com.example.springboot3.Dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class ReservationUserCommentDTO {

	private final int id;
	private final int productId;
	private final int reservationInfoId;
	private final int score;
	private final String reservationEmail;
	private final String comment;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	private final LocalDateTime createDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
	private final LocalDateTime modifyDate;

	private List<ReservationUserCommentImageDTO> reservationUserCommentImages = new ArrayList<>();

	public ReservationUserCommentDTO(int id, int productId, int reservationInfoId, int score, String reservationEmail,
		String comment, LocalDateTime createDate, LocalDateTime modifyDate) {
		this.id = id;
		this.productId = productId;
		this.reservationInfoId = reservationInfoId;
		this.score = score;
		this.reservationEmail = reservationEmail;
		this.comment = comment;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
	}

	public void setReservationUserCommentImages(List<ReservationUserCommentImageDTO> reservationUserCommentImages) {
		this.reservationUserCommentImages = reservationUserCommentImages;
	}

}
