package com.example.neurodiagnosis.webapi.dtos.test;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class TestDTO {
    public List<QuestionDetailsDTO> questions;
}
