package com.alibaba.craftsman.common.util;

import com.alibaba.cola.domain.DomainEventServiceI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.event.DomainEventI;
import com.alibaba.cola.event.EventBusI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DomainEventPublisher, this is for demo purpose, the Event is sent to EventBus
 *
 * Normally DomainEvent should be sent to Messaging Middleware
 *
 * @author Frank Zhang
 * @date 2019-01-04 11:05 AM
 */
@Component
public class DomainEventPublisher implements DomainEventServiceI{

    @Autowired
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