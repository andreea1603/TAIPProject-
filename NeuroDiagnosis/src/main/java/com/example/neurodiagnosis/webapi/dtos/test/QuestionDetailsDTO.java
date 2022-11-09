package com.example.neurodiagnosis.webapi.dtos.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDetailsDTO {
    public String question;
    public boolean needsTextInputFromUser; // daca e true -> in UI userul trebuie sa scrie text
                                           // daca e false -> in UI userul o sa aiba variante de raspuns
    public String correctAnswer;
    public String option1;
    public String option2;
    public String option3;

    public QuestionDetailsDTO(String question, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;

        this.needsTextInputFromUser = true;
//        option1 = option2 = option3 = "";
    }

    public QuestionDetailsDTO(String question, String correctAnswer, String option1, String option2, String option3) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;

        this.needsTextInputFromUser = false;
    }
}
