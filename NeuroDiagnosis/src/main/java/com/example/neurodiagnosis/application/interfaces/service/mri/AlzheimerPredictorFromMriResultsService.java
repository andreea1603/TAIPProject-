package com.example.neurodiagnosis.application.interfaces.service.mri;

import jakarta.json.JsonObject;

public class AlzheimerPredictorFromMriResultsService implements IAlzheimerPredictorFromMriResultService {

    @Override
    public JsonObject makePrediction(byte[] image) {
        return null;
    }
}
