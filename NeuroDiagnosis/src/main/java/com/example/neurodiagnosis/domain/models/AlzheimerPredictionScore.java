package com.example.neurodiagnosis.domain.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class AlzheimerPredictionScore {
    private double confidence;
    private boolean hasAlzheimer;
}
