package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;

import java.sql.Blob;
import java.util.UUID;

public class Question extends BaseEntity {
    private String question;
    private final String response;
    private Blob image;


    public Question(UUID questionId, String response, Blob image) {
        super(questionId);
        this.response = response;
        this.image = image;
    }

    public Question(UUID questionId, String question, String response) {
        super(questionId);

        this.question = question;
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }

    public Blob getImage() {
        return image;
    }

}
