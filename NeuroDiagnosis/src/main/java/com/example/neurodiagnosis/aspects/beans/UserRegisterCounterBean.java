package com.example.neurodiagnosis.aspects.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;


import java.io.Serializable;

@Singleton
@Named("userRegisterCounterBean")
@ApplicationScoped
public class UserRegisterCounterBean implements Serializable {
    private int counterSucceeded;
    private int counterFailed;

    @PostConstruct
    public void init() {
        counterSucceeded = 0;
        counterFailed = 0;
    }

    public void addSucceededRegister() {
        counterSucceeded ++;
    }

    public void addFailedRegister() {
        counterFailed++;
    }

    public int getCounterSucceeded() {
        return counterSucceeded;
    }

    public void setCounterSucceeded(int counterSucceeded) {
        this.counterSucceeded = counterSucceeded;
    }

    public int getCounterFailed() {
        return counterFailed;
    }

    public void setCounterFailed(int counterFailed) {
        this.counterFailed = counterFailed;
    }
}