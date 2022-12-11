package com.example.neurodiagnosis.infrastructure.seed;

import com.example.neurodiagnosis.application.service.question.IQuestionService;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("questionsFactory")
public class QuestionsFactory implements Serializable {
    private final IQuestionService questionService;

    @Inject
    public QuestionsFactory(@Named("questionsService") IQuestionService questionService) {

        this.questionService = questionService;
    }

    public void seedQuestions() {

        if (this.questionService.getQuestionsCount() > 0) {
            return;
        }

        questionService.addNewQuestion("What year is this?", null, null, null, null);
        questionService.addNewQuestion("What season is this?", null, null, null, null);
        questionService.addNewQuestion("What month is this (as a number)?", null, null, null, null);
        questionService.addNewQuestion("What is today's date?", null, null, null, null);
        questionService.addNewQuestion("What day of the week is this?", null, null, null, null);
        questionService.addNewQuestion("What country are you from?", null, null, null, null);
        questionService.addNewQuestion("What province are you from?", null, null, null, null);
        questionService.addNewQuestion("What city are you from?", null, null, null, null);
        questionService.addNewQuestion("You are going to see three object names. After you have " +
                        "seen all three names, you have to repeat them." +
                        "Remember what they are, you will be asked to name them again in a few minutes.",
                "Ball Car Man", "Bell Jar Fan", "Bill Tar Can", null);
        questionService.addNewQuestion("Spell the word WORLD", "world", null, null, null);
        questionService.addNewQuestion("Now spell it backwards please", "dlrow", null, null, null);
        questionService.addNewQuestion("Now what were the three objects that I asked you to remember?",
                "Ball Car Man", "Bell Jar Fan", "Bill Tar Can", null);
        questionService.addNewQuestion("What is this called?", "watch", null, null, null);
        questionService.addNewQuestion("What is this called?", "pencil", null, null, null);
        questionService.addNewQuestion("Remember this phrase and pick it from the next options",
                "no ifs, ands or buts", "no ands or buts", "no ands or buts and ifs", null);
        questionService.addNewQuestion("Remember this drawing and pick the correct answer from the next options",
                "no ifs, ands or buts", "no ands or buts", "no ands or buts and ifs", null);

    }
}
