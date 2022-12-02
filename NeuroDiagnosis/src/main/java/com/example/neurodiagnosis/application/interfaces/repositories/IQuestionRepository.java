package com.example.neurodiagnosis.application.interfaces.repositories;

import com.example.neurodiagnosis.domain.entities.Mri;
import com.example.neurodiagnosis.domain.entities.Question;
import com.example.neurodiagnosis.infrastructure.repositories.base.IRepository;
import jakarta.persistence.Lob;

import java.sql.Blob;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IQuestionRepository extends IRepository {
    Question addNewQuestion(String question, String response,
                            String option1, String option2, Blob image);
    List<Question> getQuestions();

}
