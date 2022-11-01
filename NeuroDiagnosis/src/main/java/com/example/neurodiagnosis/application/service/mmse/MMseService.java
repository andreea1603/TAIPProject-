package com.example.neurodiagnosis.application.service.mmse;

import com.example.neurodiagnosis.application.interfaces.email.IEmailService;
import com.example.neurodiagnosis.application.interfaces.repositories.IMmseTestResultsRepository;
import com.example.neurodiagnosis.application.interfaces.repositories.IUserRepository;
import com.example.neurodiagnosis.application.service.user.IPasswordHashGeneratorService;
import com.example.neurodiagnosis.application.service.validators.IEmailValidatorService;
import com.example.neurodiagnosis.infrastructure.repositories.MmseTestResultsRepository;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.AllArgsConstructor;
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
    public boolean submitTestResults(UUID userId, Date dateTimeOffset, int score) {
            return this.mmseTestResultsRepository.addNewTestResultsEntry(userId, dateTimeOffset, score);
    }
}
