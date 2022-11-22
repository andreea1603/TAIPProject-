package com.example.neurodiagnosis.aspects.beans;

import jakarta.enterprise.context.ApplicationScoped;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Named;
import java.io.Serializable;

@Singleton
@Named("userLoggedInCounterBean")
@ApplicationScoped
public class UserLoggedInCounterBean implements Serializable {
    private int counterSucceeded;
    private int counterFailed;

    @PostConstruct
    public void init() {
        counterSucceeded = 0;
        counterFailed = 0;
    }

    public void addSucceededLogin() {
        counterSucceeded ++;
    }

    public void addFailedLogin() {
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