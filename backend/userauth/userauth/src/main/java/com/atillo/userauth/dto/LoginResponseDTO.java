package com.atillo.userauth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDTO {
    private String token;
    private String email;

    public LoginResponseDTO(String token, String email){
        this.token = token;
        this.email = email;
    }
}
