package com.example.springboot3.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ReservationUserCommentDTO {

    private int id;
    private int productId;
    private int reservationInfoId;
    private int score;
    private String reservationEmail;
    private String comment;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime modifyDate;

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
