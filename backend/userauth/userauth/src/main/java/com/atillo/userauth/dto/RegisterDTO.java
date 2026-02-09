package com.atillo.userauth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
}
