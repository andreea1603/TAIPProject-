package com.example.neurodiagnosis.domain.entities;

import com.example.neurodiagnosis.domain.shared.BaseEntity;

import java.util.Date;
import java.util.UUID;

public class TestResult extends BaseEntity {
    private Integer testResult;
    private Date testDate;
    private UUID userId;

    public TestResult(UUID resultId, Date testDate, Integer testResult, UUID userId) {
        super(resultId);
        this.testResult = testResult;
        this.testDate = testDate;
        this.userId = userId;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
