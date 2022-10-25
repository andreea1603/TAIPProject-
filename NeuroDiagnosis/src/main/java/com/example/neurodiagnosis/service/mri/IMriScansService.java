package com.example.neurodiagnosis.service.mri;

import com.example.neurodiagnosis.model.Mri;

import java.util.Date;
import java.util.UUID;

public interface IMriScansService {
    Mri submitMriScan (UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType);
}
