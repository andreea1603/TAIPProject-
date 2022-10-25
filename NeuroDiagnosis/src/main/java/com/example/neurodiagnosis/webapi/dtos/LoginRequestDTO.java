package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class LoginRequestDTO {
    public String userNameOrEmail;
    public String password;
}
