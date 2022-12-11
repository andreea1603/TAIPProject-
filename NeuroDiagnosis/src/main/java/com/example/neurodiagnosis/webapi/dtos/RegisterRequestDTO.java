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
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phoneNumber;

    private int age;
    private Boolean gender;
    private String country;
    private String province;
    private String city;
    private Boolean handedness;
    private String marriedStatus;

    public RegisterRequestDTO(String emailAddress, String username, String firstName, String lastName, String password,
                              String phoneNumber) {

        this.emailAddress = emailAddress;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
