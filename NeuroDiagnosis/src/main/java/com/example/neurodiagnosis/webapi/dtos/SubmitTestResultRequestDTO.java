package com.example.neurodiagnosis.webapi.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
public class SubmitTestResultRequestDTO {
    private Integer testResult;
    private Date testDate;
    private UUID userId;
}
