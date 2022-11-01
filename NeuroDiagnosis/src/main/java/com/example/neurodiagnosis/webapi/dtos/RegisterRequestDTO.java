package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterRequestDTO {
    private String emailAddress;
    private String phoneNumber;
    private String firstName;
    private String username;
    private String lastName;
    private String password;
    private Date birthDate;
    private Boolean gender;
    private Boolean handedness;

    public RegisterRequestDTO(String emailAddress, String username, String firstName, String lastName, String password) {

        this.emailAddress = emailAddress;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
}
