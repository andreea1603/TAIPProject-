package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.application.service.mri.MriScansService;
import com.example.neurodiagnosis.webapi.dtos.MriScanDTO;
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
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public String submitMriScan(@FormParam("id")String userId, @FormParam("image") File image) throws IOException {

        return mriScansService.submitMriScan(UUID.fromString(userId),image);
    }

}
