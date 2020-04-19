package com.alibaba.cola.executor;

import com.alibaba.cola.domain.DomainEventServiceI;
import com.alibaba.cola.event.DomainEventI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lorne
 * MQ事务消息，在通过梳理完业务的主线与延伸以后，可通过事务消息机制实现对事务的控制。
 */
public class EventThreadLocal {

    private List<DomainEventI> events;

    private EventThreadLocal() {
        this.events =  new ArrayList<>();
    }

    private static ThreadLocal<EventThreadLocal> threadLocal = new ThreadLocal<>();

    protected synchronized static EventThreadLocal current(){
        return threadLocal.get();
    }

    protected synchronized static void clear(){
        threadLocal.set(null);
    }

    public synchronized static void push(DomainEventI event){
        EventThreadLocal current = current();
        if(current==null){
            current = new EventThreadLocal();
        }
        current.events.add(event);
        threadLocal.set(current);
    }

    protected static void send(DomainEventServiceI domainEventService) {
        EventThreadLocal current = current();
        if(current==null){
            return;
        }
        List<DomainEventI> events = current.events;
        if(events!=null) {
            events.parallelStream().forEach(domainEventService::asyncPublish);
        }
    }
}
