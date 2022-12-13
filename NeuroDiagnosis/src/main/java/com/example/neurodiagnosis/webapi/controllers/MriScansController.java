package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.application.service.mri.MriScansService;
import com.example.neurodiagnosis.webapi.annotations.EnforcesUserAuthorization;
import com.example.neurodiagnosis.webapi.dtos.MriScanDTO;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.UUID;

@Path("/mris/")
public class MriScansController {

    @Context
    public SecurityContext securityContext;
    private final IMriScansService mriScansService;

    @Inject
    public MriScansController(@Named("MriScansService")MriScansService mriScansService) {
        this.mriScansService = mriScansService;
    }
    public MriScansController(IMriScansService mriScansService) {
        this.mriScansService = mriScansService;
    }

    @POST
    @Path("submitMriScan")
    @Produces("application/json")
    @EnforcesUserAuthorization

    public String submitMriScan(@FormParam("image") File image) throws IOException {
        Principal userRequesting = securityContext.getUserPrincipal();
        UUID userId = UUID.fromString(userRequesting.getName());
        System.out.println(userId);
        return mriScansService.submitMriScan(userId,image);
    }

}
