package com.example.neurodiagnosis.application.service.mri;

import com.example.neurodiagnosis.domain.entities.Mri;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public interface IMriScansService {
    String submitMriScan (UUID usedId, File mineType) throws IOException;
}
