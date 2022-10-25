package com.example.neurodiagnosis.repository.mri.scan;

import com.example.neurodiagnosis.model.Mri;
import com.example.neurodiagnosis.repository.mri.scan.IMriScansRepository;

import java.util.Date;
import java.util.UUID;

public class MriScansRepository implements IMriScansRepository {
    @Override
    public Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        return null;
    }
}
