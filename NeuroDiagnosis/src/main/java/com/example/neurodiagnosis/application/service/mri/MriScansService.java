package com.example.neurodiagnosis.application.service.mri;

import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;

import java.util.Date;
import java.util.UUID;

public class MriScansService implements IMriScansService {
    private IMriScansRepository mriScansRepository;

    @Override
    public Mri submitMriScan(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        return null;
    }
}
