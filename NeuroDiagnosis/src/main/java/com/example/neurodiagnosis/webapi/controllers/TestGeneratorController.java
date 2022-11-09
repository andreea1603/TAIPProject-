package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.test.ITestGeneratorService;
import com.example.neurodiagnosis.application.service.test.TestGeneratorService;
import com.example.neurodiagnosis.webapi.annotations.EnforcesUserAuthorization;
import com.example.neurodiagnosis.webapi.dtos.test.QuestionDetailsDTO;
import com.example.neurodiagnosis.webapi.dtos.test.TestDTO;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;

import java.security.Principal;
import java.util.*;

//http://localhost:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/test/generate
@Path("/test/")
public class TestGeneratorController {
    @Context
    SecurityContext securityContext;
    private final ITestGeneratorService testGeneratorService;

    @Inject
    public TestGeneratorController(@Named("TestGeneratorService") TestGeneratorService _testGeneratorService) {
        testGeneratorService = _testGeneratorService;
    }

    public TestGeneratorController(ITestGeneratorService _testGeneratorService) {
        testGeneratorService = _testGeneratorService;
    }

    @GET
    @Path("generate")
    @Produces("application/json")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @EnforcesUserAuthorization
    public TestDTO submitMriScan() {
        Principal userRequesting = securityContext.getUserPrincipal();
        UUID userId = UUID.fromString(userRequesting.getName());

        return testGeneratorService.generateTest(userId);
    }
}
