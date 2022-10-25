package com.example.neurodiagnosis.service.mri;

import jakarta.json.JsonObject;

public interface IAlzheimerPredictorFromMriResultService {
    JsonObject makePrediction(byte[] image);
}
