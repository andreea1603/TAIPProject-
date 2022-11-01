package com.example.neurodiagnosis.webapi;

import com.example.neurodiagnosis.application.service.user.JwtService;
import com.example.neurodiagnosis.domain.entities.User;

import java.util.UUID;

public class tobedeleted {

    public static void main(String[] args) {


//        String emailAddress, String firstName, String username, String lastName, UUID userId
        var token =
                JwtService.createJWT(
                        new User("zero6305@gmail.com", "firstName", "userName", "lastName", UUID.randomUUID()),
                        "javaApi",
                        "neurodiagnoser users"
                );

        var claims = JwtService.decodeJWT(token);

        System.out.println(token);
    }
}
