package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.infrastructure.seed.QuestionsFactory;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/question/")
//http://localhost:8080/NeuroDiagnosis-1.0-SNAPSHOT/api/question/question1
public class QuestionGeneratorController {

    private final QuestionsFactory questionsFactory;

    @Inject
    public QuestionGeneratorController(@Named("questionsFactory") QuestionsFactory questionsFactory) {
        this.questionsFactory = questionsFactory;
    }

    @GET
    @Path("question1")
    @Consumes("application/json")
    @Produces("application/json")
    public String generateQuestions() {

        questionsFactory.seedQuestions();

        return "OK";
    }
}
