package com.example.neurodiagnosis.domain.shared;

public interface EventListenerCallback<T> {

    void runCallback(T event);
}
