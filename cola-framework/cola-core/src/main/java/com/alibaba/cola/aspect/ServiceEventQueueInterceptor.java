package com.alibaba.cola.aspect;

import com.alibaba.cola.domain.DomainEventServiceI;
import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

@AllArgsConstructor
public class ServiceEventQueueInterceptor implements MethodInterceptor{

    private DomainEventServiceI domainEventService;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ServiceEventQueue.clear();
        Object res =  invocation.proceed();
        ServiceEventQueue.send(domainEventService);
        return res;
    }
}
