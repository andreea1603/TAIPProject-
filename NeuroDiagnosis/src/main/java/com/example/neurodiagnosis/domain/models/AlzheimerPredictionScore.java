package com.example.neurodiagnosis.domain.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AlzheimerPredictionScore {
    private double confidence;

    public double getConfidence() {
        return confidence;
    }

    public boolean isHasAlzheimer() {
        return hasAlzheimer;
    }

    private boolean hasAlzheimer;

    public void setHasAlzheimer(boolean hasAlzheimer) {
        this.hasAlzheimer = hasAlzheimer;
    }
    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
