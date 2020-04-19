package com.alibaba.cola.domain;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.event.DomainEventI;
import com.alibaba.cola.event.EventBus;
import org.springframework.beans.factory.annotation.Autowired;


public class DefaultDomainEventService implements DomainEventServiceI {

    @Autowired
    private EventBus eventBus;

    @Override
    public Response publish(DomainEventI domainEvent) {
        return eventBus.fire(domainEvent);
    }

    @Override
    public void publishAll(DomainEventI domainEvent) {
        eventBus.fireAll(domainEvent);
    }

    @Override
    public void asyncPublish(DomainEventI event) {
        eventBus.asyncFire(event);
    }
}
