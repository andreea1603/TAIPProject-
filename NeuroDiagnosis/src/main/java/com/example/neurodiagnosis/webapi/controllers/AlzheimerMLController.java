package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.service.mripredict.IAlzheimerPredictorFromMriScanService;
import com.example.neurodiagnosis.webapi.dtos.MriScanDTO;
import com.example.neurodiagnosis.webapi.dtos.TestResultDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import lombok.AllArgsConstructor;

@Path("/ml/")
@AllArgsConstructor
public class AlzheimerMLController {

    public AlzheimerMLController(IAlzheimerPredictorFromMriScanService alzheimerPredictorFromMriScan) {

    }

    @POST
    @Path("predictAlzheimersFromMri")
    @Consumes("application/json")
    @Produces("application/json")
    public TestResultDTO predictAlzheimers(final MriScanDTO mriScanDTO) {
        return new TestResultDTO();
    }

}