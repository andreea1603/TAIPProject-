package com.example.neurodiagnosis.application.interfaces.repositories;
import java.util.UUID;
import java.util.Date;

public interface IMmseTestResultsRepository {
    boolean addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score);
}
