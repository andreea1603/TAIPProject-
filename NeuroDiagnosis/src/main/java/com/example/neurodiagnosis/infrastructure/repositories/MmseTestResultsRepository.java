package com.example.neurodiagnosis.infrastructure.repositories;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;

import java.util.Date;
import java.util.UUID;

public class MmseTestResultsRepository implements IMmseTestResultsRepository {
    @Override
    public void addNewTestResultsEntry(UUID userId, Date dateTimeOffset, int score) {
    }
}
