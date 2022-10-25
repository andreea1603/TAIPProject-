package com.example.neurodiagnosis.domain.shared;

public interface EventListenerCallback<T> {

    public void runCallback(T event);
}
