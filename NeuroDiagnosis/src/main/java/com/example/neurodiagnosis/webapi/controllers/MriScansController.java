package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.application.service.mri.MriScansService;
import com.example.neurodiagnosis.webapi.dtos.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

@Path("/mris/")
public class MriScansController {

    private final IMriScansService _mriScansService;

    @Inject
    public MriScansController(@Named("MriScansService")MriScansService mriScansService) {
        //TODO: Resolve din containerul de IoC
        _mriScansService = mriScansService;
    }
    public MriScansController(IMriScansService mriScansService) {
        _mriScansService = mriScansService;
    }

    @POST
    @Path("submitMriScan")
    @Produces("application/json")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public MriScanDTO submitMriScan(@FormParam("id")String userId, @FormParam("image") File image) throws IOException {
        var result = _mriScansService.submitMriScan(UUID.fromString(userId),image);

        return new MriScanDTO(result.getUserId(), Arrays.toString(result.getImage()));
    }

}
