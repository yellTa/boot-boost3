package com.example.springboot3.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DisplayInfoImageDTO {

    private int id;
    private int displayInfoId;
    private int fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private int deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.S")
    private LocalDateTime modifyDate;

}
