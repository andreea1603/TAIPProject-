package com.example.neurodiagnosis.application.service.test;

import com.example.neurodiagnosis.application.interfaces.repositories.IQuestionRepository;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.domain.entities.Question;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.webapi.dtos.test.QuestionDetailsDTO;
import com.example.neurodiagnosis.webapi.dtos.test.TestDTO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@NoArgsConstructor
@Named("TestGeneratorService")
@SessionScoped
public class TestGeneratorService implements ITestGeneratorService, Serializable {
    public static final String FLAG_FOR_QUESTION_NOT_AVAILABLE = "PASS";

    private IUserRepository userRepository;
    private IQuestionRepository questionRepository;

    @Inject
    public TestGeneratorService(@Named("userRepository") IUserRepository userRepository,
                                @Named("questionRepository") IQuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    public TestDTO generateTest(UUID userId) {
        List<QuestionDetailsDTO> questionsToUser = new ArrayList<>();

        Optional<User> isUser = userRepository.findById(userId);

        for (Question questionFromDB : questionRepository.getQuestions()) {
            QuestionDetailsDTO questionToUser = computeQuestionForUser(questionFromDB, isUser);
            if (questionToUser.getCorrectAnswer().equals(FLAG_FOR_QUESTION_NOT_AVAILABLE)) {
                continue;
            }

            questionsToUser.add(questionToUser);
        }

        return new TestDTO(questionsToUser);
    }

    private String getSeason(int month) {
        if (3 <= month && month <= 5) return "Spring";
        if (6 <= month && month <= 8) return "Summer";
        if (9 <= month && month <= 11) return "Autumn";

        return "Winter";
    }

    private QuestionDetailsDTO computeQuestionForUser(Question questionFromDB, Optional<User> isUser) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        String currentDate = dtf.format(now);

        if (questionFromDB.getOption1() != null) { // intrebari atat cu raspunsul corect in DB cat si cu variantele de raspuns in DB
            return new QuestionDetailsDTO(
                    questionFromDB.getQuestion(),
                    questionFromDB.getResponse(),
                    questionFromDB.getOption1(),
                    questionFromDB.getOption2(),
                    questionFromDB.getResponse()
            );
        } else if (questionFromDB.getResponse() != null) { // intrebari doar cu raspunsul corect in DB (fara variante)
            return new QuestionDetailsDTO(
                    questionFromDB.getQuestion(),
                    questionFromDB.getResponse()
            );
        }
        // mai jos doar intrebari fara raspuns si fara variante (la care calculam dinamic raspunsul: dupa data, sau datele userului)
        else if (questionFromDB.getQuestion().equals("What year is this?")) {
            return new QuestionDetailsDTO(
                    questionFromDB.getQuestion(),
                    currentDate.split("/")[2]
            );
        } else if (questionFromDB.getQuestion().equals("What season is this?")) {
            int month = Integer.parseInt(currentDate.split("/")[1]);
            String anotimp = getSeason(month);

            return new QuestionDetailsDTO(
                    questionFromDB.getQuestion(),
                    anotimp
            );
        } else if (questionFromDB.getQuestion().equals("What month is this (as a number)?")) {
            return new QuestionDetailsDTO(
                    questionFromDB.getQuestion(),
                    currentDate.split("/")[1]
            );
        } else if (questionFromDB.getQuestion().equals("What is today's date?")) {
            return new QuestionDetailsDTO(questionFromDB.getQuestion(), currentDate);
        } else if (questionFromDB.getQuestion().equals("What day of the week is this?")) {
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.get(Calendar.DAY_OF_WEEK);

            DateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            String day = formatter.format(date);

            return new QuestionDetailsDTO(questionFromDB.getQuestion(), day);
        } else if (questionFromDB.getQuestion().equals("What country are you from?") && isUser.isPresent() && isUser.get().getCountry() != null) {
            return new QuestionDetailsDTO(questionFromDB.getQuestion(), isUser.get().getCountry());
        } else if (questionFromDB.getQuestion().equals("What province are you from") && isUser.isPresent() && isUser.get().getProvince() != null) {
            return new QuestionDetailsDTO(questionFromDB.getQuestion(), isUser.get().getProvince());
        } else if (questionFromDB.getQuestion().equals("What city are you from?") && isUser.isPresent() && isUser.get().getCity() != null) {
            return new QuestionDetailsDTO(questionFromDB.getQuestion(), isUser.get().getCity());
        } else{
            return new QuestionDetailsDTO(questionFromDB.getQuestion(), FLAG_FOR_QUESTION_NOT_AVAILABLE);
        }
    }
}
