package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMriScansRepository;
import com.example.neurodiagnosis.domain.entities.Mri;

import java.util.Date;
import java.util.UUID;

public class MriScansRepository implements IMriScansRepository {
    @Override
    public Mri addNewScanEntry(UUID usedId, Date date, String url, Date expiresAt, Date signedAt, String mineType) {
        return null;
    }
}
