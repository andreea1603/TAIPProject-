package com.example.neurodiagnosis.domain.shared;

import lombok.Getter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@MappedSuperclass
public class BaseEntity {
    @Id
    private UUID id;
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
