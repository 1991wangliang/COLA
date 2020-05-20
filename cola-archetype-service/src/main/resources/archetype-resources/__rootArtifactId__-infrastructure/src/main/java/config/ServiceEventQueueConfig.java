#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.config;


import com.alibaba.cola.aspect.ServiceEventQueueInterceptor;
import com.alibaba.cola.domain.DomainEventServiceI;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuration for COLA framework
 */
@Configuration
public class ServiceEventQueueConfig {

    @Bean
    @ConfigurationProperties(prefix = "alibaba.cola.service")
    public PointcutExpression pointcutExpression(){
        return new PointcutExpression();
    }

    @Bean
    public Advisor transactionEventAdvisor(Advice transactionEventAdvice,PointcutExpression pointcutExpression){
        AspectJExpressionPointcut pointcut=new AspectJExpressionPointcut();
        pointcut.setExpression(pointcutExpression.getPointcut());
        return new DefaultPointcutAdvisor(pointcut, transactionEventAdvice);
    }

    @Bean
    public Advice transactionEventAdvice(DomainEventServiceI domainEventService){
        return new ServiceEventQueueInterceptor(domainEventService);
    }

    @Data
    class PointcutExpression {
        private String pointcut ="@annotation(org.springframework.transaction.annotation.Transactional)";
    }
}