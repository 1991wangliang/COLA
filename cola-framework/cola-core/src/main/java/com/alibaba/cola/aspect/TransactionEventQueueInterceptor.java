package com.alibaba.cola.aspect;

import com.alibaba.cola.domain.DomainEventServiceI;
import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@AllArgsConstructor
public class TransactionEventQueueInterceptor implements MethodInterceptor{

    private DomainEventServiceI domainEventService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TransactionEventQueue.clear();
        Object res =  invocation.proceed();
        TransactionEventQueue.send(domainEventService);
        return res;
    }
}
