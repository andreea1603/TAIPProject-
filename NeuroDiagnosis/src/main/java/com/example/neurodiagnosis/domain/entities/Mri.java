package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;

import java.sql.Blob;
import java.util.UUID;

public class Mri extends BaseEntity {
    private final UUID userId;
    private final Blob image;

    public Mri(UUID imageID, UUID userId, Blob image) {
        super(imageID);

        this.userId = userId;
        this.image = image;
    }


    public UUID getUserId() {
        return userId;
    }

    public Blob getImage() {
        return image;
    }
}
