package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mmse.IMmseService;
import com.example.neurodiagnosis.domain.entities.TestResult;
import com.example.neurodiagnosis.webapi.annotations.EnforcesUserAuthorization;
import com.example.neurodiagnosis.webapi.dtos.SubmitTestResultRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.TestResultDTO;
import com.example.neurodiagnosis.webapi.security.AuthSecurityContext;
import com.example.neurodiagnosis.webapi.security.UserPrincipal;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Path("/mmse/")
public class MmseTestsController {
    @Context
    public SecurityContext securityContext;

    private IMmseService _mmseService;
    @Inject
    public MmseTestsController(@Named("MMseService") IMmseService mmseService) {
        this._mmseService = mmseService;
        //TODO: Resolve din containerul de IoC
    }

    public Principal tryGetUserPrincipal() {
        return securityContext.getUserPrincipal();
    }

    @POST
    @Path("submitTest")
    @Consumes("application/json")
    @Produces("application/json")
    @EnforcesUserAuthorization
    public TestResultDTO submitTestsResults(final SubmitTestResultRequestDTO submitTestResultRequestDTO) {

        Principal userRequesting = tryGetUserPrincipal();
        var userId = UUID.fromString(userRequesting.getName());

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());

        var result = _mmseService.submitTestResults(userId, date,
                submitTestResultRequestDTO.getTestResult());

        return new TestResultDTO(result.getTestResult(), result.getTestDate(), result.getUserId());
    }

    @GET
    @Path("history")
    @Consumes("application/json")
    @Produces("application/json")
    @EnforcesUserAuthorization
    public List<TestResult> getHistory() throws Exception {
        Principal userRequesting = securityContext.getUserPrincipal();
        UUID userId = UUID.fromString(userRequesting.getName());

        return _mmseService.getHistory(userId);
    }

}