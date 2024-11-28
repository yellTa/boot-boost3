package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {

    private int id;
    private String username;
    private String tel;
    private String email;
    private String name;

    public UserDTO(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    public UserDTO(int userId, String username) {
        this.id = userId;
        this.username = username;
    }

}
