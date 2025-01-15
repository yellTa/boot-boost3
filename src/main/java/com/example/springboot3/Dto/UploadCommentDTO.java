package com.example.springboot3.Dto;

import java.beans.ConstructorProperties;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;

@Getter
public class UploadCommentDTO {

	private final int reservationInfoId;
	private final int userId;
	private final int score;
	private final String comment;
	private final MultipartFile file;

	@ConstructorProperties({"reservationInfoId", "userId", "score", "comment", "file"})
	public UploadCommentDTO(int reservationInfoId, int userId, int score, String comment, MultipartFile file) {
		this.reservationInfoId = reservationInfoId;
		this.userId = userId;
		this.score = score;
		this.comment = comment;
		this.file = file;
	}

}
