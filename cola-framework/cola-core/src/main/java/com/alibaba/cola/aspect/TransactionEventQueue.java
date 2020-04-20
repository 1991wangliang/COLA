package com.alibaba.cola.aspect;

import com.alibaba.cola.domain.DomainEventServiceI;
import com.alibaba.cola.event.DomainEventI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lorne
 *
 * 事务消息队列，是用在延伸业务的事件触发上。
 * 该队列中的Event只有在开启事务后且事务可提交时才发送.
 */
public class TransactionEventQueue {

    private List<DomainEventI> events;

    private TransactionEventQueue() {
        this.events =  new ArrayList<>();
    }

    private static ThreadLocal<TransactionEventQueue> threadLocal = new ThreadLocal<>();

    protected synchronized static TransactionEventQueue current(){
        return threadLocal.get();
    }

    protected synchronized static void clear(){
        threadLocal.set(null);
    }

    public synchronized static void push(DomainEventI event){
        TransactionEventQueue current = current();
        if(current==null){
            current = new TransactionEventQueue();
        }
        current.events.add(event);
        threadLocal.set(current);
    }

    protected static void send(DomainEventServiceI domainEventService) {
        TransactionEventQueue current = current();
        if(current==null){
            return;
        }
        List<DomainEventI> events = current.events;
        if(events!=null) {
            events.parallelStream().forEach(domainEventService::asyncPublish);
        }
    }
}
