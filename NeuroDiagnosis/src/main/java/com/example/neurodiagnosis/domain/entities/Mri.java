package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import java.sql.Blob;
import java.util.UUID;
@Entity
@Table(name="mri")
public class Mri extends BaseEntity {
    @Id
    private UUID userId;
    @Lob
    private Blob image;

    public Mri(UUID imageID, UUID userId, Blob image) {
        super(imageID);

        this.userId = userId;
        this.image = image;
    }

    public Mri() {
        super();
    }


    public UUID getUserId() {
        return userId;
    }

    public Blob getImage() {
        return image;
    }
}
