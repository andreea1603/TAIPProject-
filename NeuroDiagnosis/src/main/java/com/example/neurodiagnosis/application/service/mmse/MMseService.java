package com.example.neurodiagnosis.application.service.mmse;

import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.domain.entities.TestResult;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Named("MMseService")
@SessionScoped
public class MMseService implements IMmseService, Serializable {
    private IMmseTestResultsRepository mmseTestResultsRepository;

    @Inject
    public MMseService(@Named("mmseTestResultsRepository") IMmseTestResultsRepository MmseTestResultsRepository) {
        this.mmseTestResultsRepository = MmseTestResultsRepository;
    }
    @Override
    public TestResult submitTestResults(UUID userId, Date dateTimeOffset, int score) {
            return this.mmseTestResultsRepository.addNewTestResultsEntry(userId, dateTimeOffset, score);
    }
}
