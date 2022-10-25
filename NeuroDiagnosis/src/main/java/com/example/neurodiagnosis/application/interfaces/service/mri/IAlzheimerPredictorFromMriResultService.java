package com.example.neurodiagnosis.application.interfaces.service.mri;

import jakarta.json.JsonObject;

public interface IAlzheimerPredictorFromMriResultService {
    JsonObject makePrediction(byte[] image);
}
