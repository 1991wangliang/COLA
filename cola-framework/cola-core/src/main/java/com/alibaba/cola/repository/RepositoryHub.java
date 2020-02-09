package com.alibaba.cola.repository;

import com.alibaba.cola.common.ApplicationContextHelper;
import org.springframework.stereotype.Component;

/**
 * @author lorne
 * @date 2020/1/27
 */
@Component
public class RepositoryHub {


    public <T extends RepositoryHandlerI> T getRepositoryHandler(Class<T> clazz){
        return ApplicationContextHelper.getBean(clazz);
    }

}
