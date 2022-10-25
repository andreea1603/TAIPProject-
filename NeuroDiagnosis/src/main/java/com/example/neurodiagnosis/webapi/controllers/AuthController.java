package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.webapi.dtos.ApplicationUserDTO;
import com.example.neurodiagnosis.webapi.dtos.JwtTokenResponse;
import com.example.neurodiagnosis.webapi.dtos.LoginRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.ws.rs.*;

@Path("/auth/")
public class AuthController {

    private final IUsersService _userService;
    public AuthController() {
        //TODO: Resolve din containerul de IoC
        _userService = null;
    }
    public AuthController(IUsersService userService) {
        _userService = userService;
    }

    @POST
    @Path("login")
    @Consumes("application/json")
    @Produces("application/json")
    public JwtTokenResponse loginUser(final LoginRequestDTO loginRequestDTO) {

//        String jwtToken = _userService.loginUser(loginRequestDTO.userNameOrEmail, loginRequestDTO.password);

        return new JwtTokenResponse();
    }

    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public ApplicationUserDTO registerUser(final RegisterRequestDTO registerRequestDTO) {


        return new ApplicationUserDTO();
    }



}