package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.webapi.dtos.*;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/mris/")
public class MriScansController {

    private final IMriScansService _mriScansService;
    public MriScansController() {
        //TODO: Resolve din containerul de IoC
        _mriScansService = null;
    }
    public MriScansController(IMriScansService mriScansService) {
        _mriScansService = mriScansService;
    }

    @POST
    @Path("submitMriScan")
    @Consumes("application/json")
    @Produces("application/json")
    public MriScanDTO submitMriScan(final SubmitMriScanRequestDTO loginRequestDTO) {

        return new MriScanDTO(null, null);
    }

}