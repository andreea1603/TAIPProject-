package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.sql.Blob;
import java.util.UUID;
@Entity
@Table(name="mri")
public class Mri extends BaseEntity {
    private UUID userId;
    @Lob
    private Blob image;

    public Mri(UUID imageID, UUID userId, Blob image) {
        super(imageID);
        this.userId = userId;
        this.image = image;
    }

    public Mri() {

    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getUserId() {
        return userId;
    }

    public Blob getImage() {
        return image;
    }
}
