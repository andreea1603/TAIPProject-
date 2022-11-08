package com.example.neurodiagnosis.webapi.controllers;

import com.example.neurodiagnosis.application.interfaces.repositories.IQuestionRepository;
import com.example.neurodiagnosis.application.service.database.DatabaseContextLive;
import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.mri.IMriScansService;
import com.example.neurodiagnosis.application.service.question.IQuestionService;
import com.example.neurodiagnosis.application.service.question.QuestionService;
import com.example.neurodiagnosis.domain.entities.Question;
import com.example.neurodiagnosis.infrastructure.repositories.QuestionRepository;
import com.example.neurodiagnosis.webapi.dtos.MriScanDTO;
import com.example.neurodiagnosis.webapi.dtos.SubmitMriScanRequestDTO;
import jakarta.inject.Named;
import jakarta.ws.rs.*;

import java.sql.Blob;

@Path("/question/")

public class QuestionGeneratorController {
    private final IQuestionService _questionService;

    public QuestionGeneratorController(IQuestionService questionService) {
        _questionService = questionService;
    }

    public QuestionGeneratorController() {
        //TODO: Resolve din containerul de IoC
        _questionService = null;
    }
    @GET
    @Path("question1")
    @Consumes("application/json")
    @Produces("application/json")
    public String submitMriScan() {
        QuestionService questionService= new QuestionService(new QuestionRepository( new DatabaseContextTests()));
        Question q = questionService.addNewQuestion("Hello?",
                "Yes","No","nope", null);
        questionService.addNewQuestion("What year is this?", null, null, null, null);
        questionService.addNewQuestion("What season is this?", null, null, null, null);
        questionService.addNewQuestion("What month is this?", null, null, null, null);
        questionService.addNewQuestion("What is today's date?", null, null, null,null);
        questionService.addNewQuestion("What day of the week is this??", null, null, null,null);
        questionService.addNewQuestion("What country are you from?", null, null, null, null);
        questionService.addNewQuestion("What province are you from?", null, null, null,null);
        questionService.addNewQuestion("What city are you from?", null, null, null,null);
        questionService.addNewQuestion("You are going to see three object names. After you have " +
                "seen all three names, you have to repeat them." +
                "Remember what they are, you will be asked to name them again in a few minutes.",
                "Ball Car Man","Bell Jar Fan","Bill Tar Can", null);
        questionService.addNewQuestion("Spell the word WORLD", "world",null,null, null);
        questionService.addNewQuestion("Now spell it backwards please", "dlrow",null,null, null);
        questionService.addNewQuestion("Now what were the three objects that I asked you to remember?",
                                                    "Ball Car Man","Bell Jar Fan","Bill Tar Can", null);
        questionService.addNewQuestion("What is this called?", "watch",null,null, null);
        questionService.addNewQuestion("What is this called?", "pencil",null,null, null);
        questionService.addNewQuestion("Remember this phrase and pick it from the next options",
                "no ifs, ands or buts","no ands or buts","no ands or buts and ifs", null);
        questionService.addNewQuestion("Remember this drawing and pick the correct answer from the next options",
                "no ifs, ands or buts","no ands or buts","no ands or buts and ifs", null);

        return q.getQuestion() + q.getResponse();
    }
}
