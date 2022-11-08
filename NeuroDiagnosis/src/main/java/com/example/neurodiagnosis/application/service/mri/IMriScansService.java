package com.example.neurodiagnosis.application.service.mri;

import com.example.neurodiagnosis.domain.entities.Mri;

import java.io.File;
import java.util.UUID;

public interface IMriScansService {
    Mri submitMriScan (UUID usedId, File mineType);
}
