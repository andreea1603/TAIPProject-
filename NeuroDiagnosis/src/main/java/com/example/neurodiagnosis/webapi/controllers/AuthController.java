package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.webapi.annotations.EnforcesUserAuthorization;
import com.example.neurodiagnosis.webapi.dtos.ApplicationUserDTO;
import com.example.neurodiagnosis.webapi.dtos.JwtTokenResponse;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;

import java.util.Optional;

@Path("/auth/")
public class AuthController {

    private IUsersService _userService;
    @Inject
    public AuthController(@Named("usersService") IUsersService userService) {
        _userService = userService;
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public JwtTokenResponse loginUser(final LoginRequestDTO loginRequestDTO) throws Exception {

        Optional<String> jwtToken = _userService.loginUser(loginRequestDTO.userNameOrEmail, loginRequestDTO.password);

        if (jwtToken.isEmpty()) {
            throw new Exception("Invalid credentials");
        }

        return new JwtTokenResponse(jwtToken.get());
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public ApplicationUserDTO registerUser(final RegisterRequestDTO registerRequestDTO) throws Exception {

        var userOpt = _userService.registerUser(registerRequestDTO.getUsername(),
                registerRequestDTO.getFirstName(),
                registerRequestDTO.getLastName(),
                registerRequestDTO.getEmailAddress(),
                registerRequestDTO.getPassword()
                );

        if (userOpt.isEmpty()) {
            throw new Exception("Couldn't register user!");
        }

        var user = userOpt.get();

        return new ApplicationUserDTO(user.getId(), user.getEmailAddress(),
                user.getPhoneNumber(), user.getFirstName(), user.getUsername(),
                user.getLastName());
    }
}