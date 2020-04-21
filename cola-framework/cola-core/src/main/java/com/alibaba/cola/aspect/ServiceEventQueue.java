package com.alibaba.cola.aspect;

import com.alibaba.cola.domain.DomainEventServiceI;
import com.alibaba.cola.event.DomainEventI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lorne
 *
 * 业务消息队列，是用在延伸业务的事件触发上。
 * 该队列中的Event只有在业务正常执行完后才发送消息.
 */
public class ServiceEventQueue {

    private List<DomainEventI> events;

    private ServiceEventQueue() {
        this.events =  new ArrayList<>();
    }

    private static ThreadLocal<ServiceEventQueue> threadLocal = new ThreadLocal<>();

    protected synchronized static ServiceEventQueue current(){
        return threadLocal.get();
    }

    protected synchronized static void clear(){
        threadLocal.set(null);
    }

    public synchronized static void push(DomainEventI event){
        ServiceEventQueue current = current();
        if(current==null){
            current = new ServiceEventQueue();
        }
        current.events.add(event);
        threadLocal.set(current);
    }

    protected static void send(DomainEventServiceI domainEventService) {
        ServiceEventQueue current = current();
        if(current==null){
            return;
        }
        List<DomainEventI> events = current.events;
        if(events!=null) {
            events.parallelStream().forEach(domainEventService::asyncPublish);
        }
    }
}
