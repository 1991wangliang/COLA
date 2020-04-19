package com.alibaba.cola;

import com.alibaba.cola.boot.Bootstrap;
import com.alibaba.cola.domain.DefaultDomainEventService;
import com.alibaba.cola.domain.DomainEventServiceI;
import com.alibaba.cola.event.DefaultEventBus;
import com.alibaba.cola.event.EventBusI;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author lorne
 * @date 2020/1/27
 * @description
 */
@ComponentScan
@Configuration
@Slf4j
public class ColaConfiguration {

    @Bean(initMethod = "init")
    public Bootstrap bootstrap(PackagesToScan packagesToScan) {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.setPackages(packagesToScan.getList());
        log.info("cola init finish.");
        return bootstrap;
    }

    @Bean
    @ConditionalOnMissingBean
    public DomainEventServiceI domainEventService(EventBusI eventBus){
        return new DefaultDomainEventService(eventBus);
    }

    @Bean
    @ConditionalOnMissingBean
    public EventBusI eventBus(){
        return new DefaultEventBus();
    }

    @Bean
    @ConfigurationProperties(prefix = "alibaba.cola.packages")
    public PackagesToScan packagesToScan(){
        return new PackagesToScan();
    }

    public class PackagesToScan{

        @Getter
        @Setter
        List<String> list;

    }

}
