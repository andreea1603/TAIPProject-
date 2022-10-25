package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.interfaces.service.mripredict.IAlzheimerPredictorFromMriScanService;
import com.example.neurodiagnosis.webapi.dtos.MriScanDTO;
import com.example.neurodiagnosis.webapi.dtos.TestResultDTO;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/ml/")
public class AlzheimerMLController {

    private final IAlzheimerPredictorFromMriScanService _alzheimerPredictorFromMriScan;

    public AlzheimerMLController() {
        //TODO: Resolve din containerul de IoC
        _alzheimerPredictorFromMriScan = null;
    }
    public AlzheimerMLController(IAlzheimerPredictorFromMriScanService alzheimerPredictorFromMriScan) {
        _alzheimerPredictorFromMriScan = alzheimerPredictorFromMriScan;
    }

    @POST
    @Path("predictAlzheimersFromMri")
    @Consumes("application/json")
    @Produces("application/json")
    public TestResultDTO predictAlzheimers(final MriScanDTO mriScanDTO) {

        return new TestResultDTO();
    }

}