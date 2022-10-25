package com.example.neurodiagnosis.domain.shared;


public interface IDomainEvent {
    void registerListener(EventListenerCallback<? extends IDomainEvent> callback);
}

