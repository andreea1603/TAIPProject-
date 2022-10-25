package com.example.neurodiagnosis.application.interfaces.service.mri;

import com.example.neurodiagnosis.domain.entities.Mri;

import java.util.Date;
import java.util.UUID;

public interface IMriScansService {
    Mri submitMriScan (UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType);
}
