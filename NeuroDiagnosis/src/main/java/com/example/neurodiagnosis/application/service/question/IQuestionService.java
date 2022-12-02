package com.example.neurodiagnosis.application.service.question;

import com.example.neurodiagnosis.domain.entities.Question;

import java.sql.Blob;

public interface IQuestionService {

    public Question addNewQuestion(String question, String response, String option1, String option2, Blob image);


    public int getQuestionsCount();
}
