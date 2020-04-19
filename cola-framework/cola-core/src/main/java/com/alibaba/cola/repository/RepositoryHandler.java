package com.alibaba.cola.repository;

import org.springframework.stereotype.Repository;

import java.lang.annotation.*;

/**
 * @author lorne
 * @date 2020/1/27
 * 资源层的对象需实现{@link RepositoryHandlerI }接口
 * 资源层的操作只允许在Executor层处理.
 * Executor层处理可直接依赖DB层操作,例如Mapper，若存在对领域数据或批量资源层操作时使用{@link RepositoryHandlerI}资源对象
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repository
@Deprecated
public @interface RepositoryHandler {

}
