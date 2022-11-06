package com.example.neurodiagnosis.domain.shared;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Transient
    protected List<IDomainEvent> domainEvents;
    protected BaseEntity(UUID id) {

        this.domainEvents = new ArrayList<>();
        this.id = id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public List<IDomainEvent> getDomainEvents() {
        return domainEvents;
    }

    public void setDomainEvents(List<IDomainEvent> domainEvents) {
        this.domainEvents = domainEvents;
    }

    protected BaseEntity(){}


    protected void addDomainEvent(IDomainEvent domainEvent) {

        if (domainEvents.contains(domainEvent)) {
            return;
        }

        domainEvents.add(domainEvent);
    }
}
