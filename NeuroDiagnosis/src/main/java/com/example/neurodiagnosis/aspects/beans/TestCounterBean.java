package com.example.neurodiagnosis.aspects.beans;


import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Singleton
@Named("testCounterBean")
@ApplicationScoped
public class TestCounterBean implements Serializable {
    private int generationCounter;
    private Map<UUID, Integer> userTestSubmissions = new HashMap<>();

    @PostConstruct
    public void init() {
        generationCounter = 0;
    }

    public void addNewGeneratedTest() {
        generationCounter++;
    }

    public void addNewTestSubmission(UUID userId) {
        if (userTestSubmissions.containsKey(userId)) {
            userTestSubmissions.put(userId, userTestSubmissions.get(userId) + 1);
        } else {
            userTestSubmissions.put(userId, 1);
        }
    }

    public int getGenerationCounter() {
        return generationCounter;
    }

    public void setGenerationCounter(int generationCounter) {
        this.generationCounter = generationCounter;
    }

    public Map<UUID, Integer> getUserTestSubmissions() {
        return userTestSubmissions;
    }

    public void setUserTestSubmissions(Map<UUID, Integer> userTestSubmissions) {
        this.userTestSubmissions = userTestSubmissions;
    }
}