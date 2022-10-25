package com.example.neurodiagnosis.application.service.mripredict;

import com.example.neurodiagnosis.domain.models.AlzheimerPredictionScore;

public interface IAlzheimerPredictorFromMriScanService {
    AlzheimerPredictionScore makePrediction(String imageBase64);
}
