package com.alibaba.cola.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author lorne
 * @date 2020/1/27
 */
@Component
@AllArgsConstructor
public class RepositoryBus {

    private RepositoryHub repositoryHub;

    public <T extends RepositoryHandlerI> T getHandler(Class<T> clazz){
        return repositoryHub.getRepositoryHandler(clazz);
    }


}
