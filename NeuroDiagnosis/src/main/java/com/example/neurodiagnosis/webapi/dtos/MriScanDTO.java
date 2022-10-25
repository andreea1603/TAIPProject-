package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
public class MriScanDTO {
    private UUID userId;
    private Blob image;
}
