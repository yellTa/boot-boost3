package com.example.springboot3.Dto.response;

import com.example.springboot3.Dto.ReservationUserCommentDTO;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentsReponseDTO {

    private int totalCount;
    private int commentCount;
    private List<ReservationUserCommentDTO> reservationUserComments;

}
