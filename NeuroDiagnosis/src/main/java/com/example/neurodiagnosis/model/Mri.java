package com.example.neurodiagnosis.model;

import java.sql.Blob;
import java.util.UUID;

public class Mri {
    private final UUID imageID;
    private final UUID userId;
    private final Blob image;


    public Mri(UUID imageID, UUID userId, Blob image) {
        this.imageID = imageID;
        this.userId = userId;
        this.image = image;
    }

    public UUID getImageID() {
        return imageID;
    }

    public UUID getUserId() {
        return userId;
    }

    public Blob getImage() {
        return image;
    }
}
