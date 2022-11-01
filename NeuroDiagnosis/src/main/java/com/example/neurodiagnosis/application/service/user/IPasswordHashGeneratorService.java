package com.example.neurodiagnosis.application.service.user;

public interface IPasswordHashGeneratorService {

    public String generatePasswordHash(String password);
}
