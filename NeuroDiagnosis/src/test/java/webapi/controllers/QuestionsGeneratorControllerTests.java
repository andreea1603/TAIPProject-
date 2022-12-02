package webapi.controllers;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.application.service.question.QuestionService;
import com.example.neurodiagnosis.infrastructure.repositories.QuestionRepository;
import com.example.neurodiagnosis.infrastructure.seed.QuestionsFactory;
import com.example.neurodiagnosis.webapi.controllers.QuestionGeneratorController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionsGeneratorControllerTests {

    @Test
    public void givenQuestionsController__WhenGenerateIsRequested__ShouldReturnOkStatus() {


        QuestionGeneratorController qgc = new QuestionGeneratorController(new QuestionsFactory
                (new QuestionService(new QuestionRepository(new DatabaseContextTests()))));

        var response = qgc.generateQuestions();

        assertEquals(response, "OK");
    }
}
