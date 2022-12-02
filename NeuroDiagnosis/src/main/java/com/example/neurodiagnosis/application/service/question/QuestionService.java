package com.example.neurodiagnosis.application.service.question;

import com.example.neurodiagnosis.application.interfaces.repositories.IQuestionRepository;
import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.domain.entities.Question;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.UUID;

@Named("questionsService")
@SessionScoped
public class QuestionService implements IQuestionService, Serializable {
    private final IQuestionRepository questionRepository;

    @Inject
    public QuestionService(@Named("questionRepository")IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    @Override
    public Question addNewQuestion(String question, String response, String option1, String option2, Blob image) {
        return questionRepository.addNewQuestion(question, response, option1, option2, image);
    }

    @Override
    public int getQuestionsCount() {
        return questionRepository.getQuestions().size();
    }

}
