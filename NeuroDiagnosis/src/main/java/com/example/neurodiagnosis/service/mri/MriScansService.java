package com.example.neurodiagnosis.service.mri;

import com.example.neurodiagnosis.model.Mri;
import com.example.neurodiagnosis.repository.mri.IMriScansRepository;

import java.util.Date;
import java.util.UUID;

public class MriScansService implements IMriScansService {
    private IMriScansRepository mriScansRepository;

    @Override
    public Mri submitMriScan(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        return null;
    }
}
