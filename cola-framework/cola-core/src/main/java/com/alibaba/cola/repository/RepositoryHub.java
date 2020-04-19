package com.alibaba.cola.repository;

import com.alibaba.cola.common.ApplicationContextHelper;
import org.springframework.stereotype.Component;

/**
 * @author lorne
 * @date 2020/1/27
 * 资源层的操作只允许在Executor层处理.
 * Executor层处理可直接依赖DB层操作,例如Mapper，若存在对领域数据或批量资源层操作时使用{@link RepositoryHandlerI}资源对象
 */
@Component
@Deprecated
public class RepositoryHub {

    public <T extends RepositoryHandlerI> T getRepositoryHandler(Class<T> clazz){
        return ApplicationContextHelper.getBean(clazz);
    }

}
