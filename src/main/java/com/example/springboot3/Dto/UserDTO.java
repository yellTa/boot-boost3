package com.example.springboot3.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {

    int id;
    String username;
    String tel;
    String email;
    String name;

    public UserDTO(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

}
