package com.example.neurodiagnosis.domain.shared;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    private UUID id;

    @Transient
    protected List<IDomainEvent> domainEvents;
    protected BaseEntity(UUID id) {

        this.domainEvents = new ArrayList<>();
        this.id = id;
    }
    protected BaseEntity(){}


    protected void addDomainEvent(IDomainEvent domainEvent) {

        if (domainEvents.contains(domainEvent)) {
            return;
        }

        domainEvents.add(domainEvent);
    }
}
