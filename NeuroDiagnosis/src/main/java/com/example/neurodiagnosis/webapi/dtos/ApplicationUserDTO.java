package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationUserDTO {
    private UUID id;
    private String emailAddress;
    private String phoneNumber;
    private String firstName;
    private String username;
    private String lastName;
}
