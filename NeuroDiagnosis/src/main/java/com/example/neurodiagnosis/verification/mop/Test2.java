package com.example.neurodiagnosis.verification.mop;

import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.domain.exceptions.RegistrationException;
import com.example.neurodiagnosis.webapi.dtos.ApplicationUserDTO;
import com.example.neurodiagnosis.webapi.dtos.RegisterRequestDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

public class Test2 {
    private final IUsersService _userService;

    public Test2() {
        //TODO: Resolve din containerul de IoC
        _userService = null;
    }
    @POST
    @Path("register")
    @Consumes("application/json")
    @Produces("application/json")
    public ApplicationUserDTO registerUser(final RegisterRequestDTO registerRequestDTO) throws Exception {

        var userOpt = _userService.registerUser(registerRequestDTO.getUsername(), registerRequestDTO.getFirstName(),
                registerRequestDTO.getLastName(), registerRequestDTO.getEmailAddress(), registerRequestDTO.getPassword()
        );

        if (userOpt.isEmpty()) {
            throw new RegistrationException("Couldn't register user!");
        }

        var user = userOpt.get();

        return new ApplicationUserDTO(user.getId(), user.getEmailAddress(), user.getPhoneNumber(), user.getFirstName(), user.getUsername(),
                user.getLastName());
    }
}
