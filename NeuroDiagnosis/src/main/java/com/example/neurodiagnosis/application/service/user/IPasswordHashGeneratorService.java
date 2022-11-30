package com.example.neurodiagnosis.application.service.user;

public interface IPasswordHashGeneratorService {

    String calculateHash(String password, String alg);
}
