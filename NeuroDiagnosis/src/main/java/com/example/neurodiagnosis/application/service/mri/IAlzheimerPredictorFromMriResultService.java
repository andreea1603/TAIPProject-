package com.example.neurodiagnosis.application.service.mri;

import jakarta.json.JsonObject;

public interface IAlzheimerPredictorFromMriResultService {
    JsonObject makePrediction(byte[] image);
}
