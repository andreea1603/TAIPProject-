package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IQuestionRepository;
import com.example.neurodiagnosis.application.service.database.IDatabaseContext;
import com.example.neurodiagnosis.domain.entities.Question;
import com.example.neurodiagnosis.infrastructure.repositories.base.BaseRepository;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.sql.Blob;
import java.util.UUID;

@Named("questionRepository")

public class QuestionRepository extends BaseRepository implements IQuestionRepository, Serializable {
    @Inject
    public QuestionRepository(@Named("DatabaseContextLive") IDatabaseContext databaseContext) {
        super(databaseContext);
    }
    @Override
    public Question addNewQuestion(String question, String response, String option1, String option2, Blob image) {
        Question question1 = new Question(question, response, option1, option2, image);
        question1.setId(UUID.randomUUID());
        if(!em.getTransaction().isActive()){
            em.getTransaction().begin();
        }
        em.persist(question1);

        em.getTransaction().commit();
        System.out.println(question1);
        return question1;
    }
}
