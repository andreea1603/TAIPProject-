package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.domain.exceptions.LoginException;
import com.example.neurodiagnosis.domain.exceptions.RegistrationException;
import com.example.neurodiagnosis.webapi.dtos.JwtTokenResponse;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.Optional;

@Path("/auth/")
public class AuthController {

    private final IUsersService userService;
    @Inject
    public AuthController(@Named("usersService") IUsersService userService) {
        this.userService = userService;
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public JwtTokenResponse loginUser(final LoginRequestDTO loginRequestDTO) throws LoginException {

        Optional<String> jwtToken = userService.loginUser(loginRequestDTO.userNameOrEmail, loginRequestDTO.password);

        if (jwtToken.isEmpty()) {
            throw new LoginException("Invalid credentials");
        }

        return new JwtTokenResponse(jwtToken.get());
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public User registerUser(final RegisterRequestDTO registerRequestDTO) throws RegistrationException {

        var userOpt = userService.registerUser(registerRequestDTO);

        if (userOpt.isEmpty()) {
            throw new RegistrationException("Couldn't register user!");
        }

        return userOpt.get();
    }
}