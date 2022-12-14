package com.example.neurodiagnosis.application.service.mripredict;

import com.example.neurodiagnosis.domain.models.AlzheimerPredictionScore;

public class AlzheimerPredictorFromMriScanService implements IAlzheimerPredictorFromMriScanService {

    @Override
    public AlzheimerPredictionScore makePrediction(String imageBase64) {
        var result = new AlzheimerPredictionScore(){};
        result.setHasAlzheimer(true);
        result.setConfidence(90.9);

        return result;

    }
}
