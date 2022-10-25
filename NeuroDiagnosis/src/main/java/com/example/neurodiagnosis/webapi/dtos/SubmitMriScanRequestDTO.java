package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SubmitMriScanRequestDTO {
    public String mriScanBase64;
}
