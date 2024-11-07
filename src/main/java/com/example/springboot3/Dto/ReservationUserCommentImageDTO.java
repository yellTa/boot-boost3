package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservationUserCommentImageDTO {

    private int id;
    private int reservationInfoId;
    private int reservationUserCommentId;
    private int fileId;

}
