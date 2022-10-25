package com.example.neurodiagnosis.application.interfaces.repositories;
import java.util.UUID;
import java.util.Date;

public interface IMmseTestResultsRepository {
    void addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score);
}
