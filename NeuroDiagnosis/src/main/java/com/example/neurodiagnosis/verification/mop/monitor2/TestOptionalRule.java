package com.example.neurodiagnosis.verification.mop.monitor2;

import com.example.neurodiagnosis.application.service.database.DatabaseContextTests;
import com.example.neurodiagnosis.domain.entities.User;
import com.example.neurodiagnosis.infrastructure.repositories.UserRepository;

import java.util.Optional;

public class TestOptionalRule {
    public static void main(String[] args) {
        Optional<User> opt = new UserRepository(new DatabaseContextTests()).findByEmail("email@gmail.com");

        User user = opt.get();

        System.out.println(user);
    }
}
