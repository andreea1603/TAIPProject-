package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.personaldata.IPersonalDataService;
import com.example.neurodiagnosis.application.service.user.IUsersService;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.annotations.EnforcesUserAuthorization;
import com.example.neurodiagnosis.webapi.dtos.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;

@Path("/data/")
public class PersonalDataController {
    @Context
    public SecurityContext securityContext;
    private IPersonalDataService personalDataService;
    @Inject
    public PersonalDataController(@Named("PersonalDataService") IPersonalDataService personalDataService) {
        this.personalDataService = personalDataService;
    }

    @POST
    @Path("update")
    @Consumes("application/json")
    @Produces("application/json")
    @EnforcesUserAuthorization
    public User updatePersonalData(final PersonalDataDTO personalDataDTO) throws Exception {
        Principal userRequesting = securityContext.getUserPrincipal();
        UUID userId = UUID.fromString(userRequesting.getName());

        return personalDataService.updateData(userId, personalDataDTO);
    }
}