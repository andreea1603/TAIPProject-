package com.example.neurodiagnosis.repository.Mmse;
import java.util.UUID;
import java.util.Date;

public interface IMmseTestResultsRepository {
    void addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score);
}
