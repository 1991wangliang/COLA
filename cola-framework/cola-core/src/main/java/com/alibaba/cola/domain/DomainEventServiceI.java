package com.alibaba.cola.domain;


import com.alibaba.cola.dto.Response;
import com.alibaba.cola.event.DomainEventI;

/**
 * DomainEventServiceI 领域事件服务
 *
 * @author Frank Zhang
 * @date 2018-07-30 11:39 AM
 */
public interface DomainEventServiceI {
    /**
     * 发布领域事件
     *
     * 具体的事件处理机制，由应用自己实现，在sofa-extension中，我们提供了MetaQ的实现
     * @param domainEvent
     */
    Response publish(DomainEventI domainEvent);


    /**
     *
     * @param domainEvent
     */
    void publishAll(DomainEventI domainEvent);


    /**
     * Async fire all handlers
     * @param event
     */
    void asyncPublish(DomainEventI event);
}
