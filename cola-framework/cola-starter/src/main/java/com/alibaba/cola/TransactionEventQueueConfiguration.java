package com.alibaba.cola;

import com.alibaba.cola.aspect.TransactionEventQueueInterceptor;
import com.alibaba.cola.domain.DomainEventServiceI;
import lombok.Data;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionEventQueueConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "alibaba.cola.transaction")
    public TransactionExpression transactionExpression(){
        return new TransactionExpression();
    }

    @Bean
    public Advisor transactionEventAdvisor(Advice transactionEventAdvice,TransactionExpression transactionExpression){
        AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
        pointcut.setExpression(transactionExpression.getExpression());
        return new DefaultPointcutAdvisor(pointcut, transactionEventAdvice);
    }

    @Bean
    public Advice transactionEventAdvice(DomainEventServiceI domainEventService){
        return new TransactionEventQueueInterceptor(domainEventService);
    }

    @Data
    class TransactionExpression {
        private String expression ="@annotation(org.springframework.transaction.annotation.Transactional)";
    }

}
