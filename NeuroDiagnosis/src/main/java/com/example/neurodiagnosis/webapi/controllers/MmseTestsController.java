package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mmse.IMmseService;
import com.example.neurodiagnosis.webapi.dtos.SubmitTestResultRequestDTO;
import com.example.neurodiagnosis.webapi.dtos.TestResultDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/mmse/")
public class MmseTestsController {

    private final IMmseService _mmseService;
    public MmseTestsController() {
        //TODO: Resolve din containerul de IoC
        _mmseService = null;
    }
    public MmseTestsController(IMmseService mmseService) {
        _mmseService = mmseService;
    }

    @POST
    @Path("submitTest")
    @Consumes("application/json")
    @Produces("application/json")
    public TestResultDTO submitTestsResults(final SubmitTestResultRequestDTO submitTestResultRequestDTO) {

        return new TestResultDTO();
    }

}