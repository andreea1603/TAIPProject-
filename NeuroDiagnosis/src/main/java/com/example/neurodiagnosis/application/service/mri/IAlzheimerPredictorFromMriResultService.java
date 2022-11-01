package com.example.neurodiagnosis.application.service.mri;

import org.json.simple.JSONObject;

public interface IAlzheimerPredictorFromMriResultService {
    JSONObject makePrediction(byte[] image);
}
