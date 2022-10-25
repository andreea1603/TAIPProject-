package com.example.neurodiagnosis.application.interfaces.service.mripredict;

import com.example.neurodiagnosis.domain.models.AlzheimerPredictionScore;

public interface IAlzheimerPredictorFromMriScanService {
    AlzheimerPredictionScore makePrediction(String imageBase64);
}
