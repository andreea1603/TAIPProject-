package com.example.neurodiagnosis.application.service.mri;

import org.json.simple.JSONObject;

public class AlzheimerPredictorFromMriResultsService implements IAlzheimerPredictorFromMriResultService {

    @Override
    public JSONObject makePrediction(byte[] image) {
        return new JSONObject();
    }
}
