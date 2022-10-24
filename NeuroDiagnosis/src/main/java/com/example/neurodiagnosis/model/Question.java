package com.example.neurodiagnosis.model;

import java.sql.Blob;
import java.util.UUID;

public class Question {
    private final UUID questionId;
    private String question;
    private final String response;
    private Blob image;


    public Question(UUID questionId, String response, Blob image) {
        this.questionId = questionId;
        this.response = response;
        this.image = image;
    }

    public Question(UUID questionId, String question, String response) {
        this.questionId = questionId;
        this.question = question;
        this.response = response;
    }

    public UUID getQuestionId() {
        return questionId;
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
