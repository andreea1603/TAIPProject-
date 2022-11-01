package com.example.neurodiagnosis.application.service.user;

public class PasswordHashGeneratorService implements IPasswordHashGeneratorService {
    @Override
    public String generatePasswordHash(String password) {
        return "hash";
    }
}
