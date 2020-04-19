package com.alibaba.cola.domain;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.event.DomainEventI;
import com.alibaba.cola.event.EventBusI;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class DefaultDomainEventService implements DomainEventServiceI {

    private EventBusI eventBus;

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
