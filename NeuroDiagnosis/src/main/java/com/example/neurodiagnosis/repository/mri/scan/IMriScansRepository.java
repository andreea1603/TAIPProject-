package com.example.neurodiagnosis.repository.mri.scan;

import com.example.neurodiagnosis.model.Mri;

import java.util.Date;
import java.util.UUID;

public interface IMriScansRepository {
    Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType);
}
