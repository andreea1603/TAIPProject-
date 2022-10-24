package com.example.neurodiagnosis.model;

import java.util.Date;
import java.util.UUID;

public class TestResult {
    private Integer testResult;
    private Date testDate;
    private UUID resultId;
    private UUID userId;

    public TestResult(Integer testResult, Date testDate, UUID resultId, UUID userId) {
        this.testResult = testResult;
        this.testDate = testDate;
        this.resultId = resultId;
        this.userId = userId;
    }

    public TestResult() {
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

    public UUID getResultId() {
        return resultId;
    }

    public void setResultId(UUID resultId) {
        this.resultId = resultId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
