package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;
import jakarta.persistence.*;

import java.sql.Blob;
import java.util.UUID;
@Entity
@Table(name="question")
@NamedQueries({
        @NamedQuery(name = "Question.getAll", query = "SELECT q FROM Question q")
})
public class Question extends BaseEntity {
    private String question;
    private String response;
    private String option1;
    private String option2;
    @Lob
    private Blob image;

    public Question(UUID questionId, String response, Blob image) {
        super(questionId);
        this.response = response;
        this.image = image;
    }

    public Question(String question, String response, String option1, String option2, Blob image) {
        this.question = question;
        this.response = response;
        this.option1 = option1;
        this.option2 = option2;
        this.image = image;
    }

    public Question(UUID questionId, String question, String response) {
        super(questionId);

        this.question = question;
        this.response = response;
    }

    public Question() {
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
    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

}
