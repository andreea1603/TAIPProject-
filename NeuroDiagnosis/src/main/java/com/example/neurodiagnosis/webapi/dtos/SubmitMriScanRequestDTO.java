package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class SubmitMriScanRequestDTO {
    public File mriScanBase64;
    public UUID userId;
}
