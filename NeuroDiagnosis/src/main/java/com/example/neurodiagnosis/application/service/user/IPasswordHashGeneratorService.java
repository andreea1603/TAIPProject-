package com.example.neurodiagnosis.application.service.user;

public interface IPasswordHashGeneratorService {

    public String calculateHash(String password);
}
