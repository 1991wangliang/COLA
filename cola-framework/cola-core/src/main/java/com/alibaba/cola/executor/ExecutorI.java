package com.alibaba.cola.executor;

import com.alibaba.cola.dto.Command;
import com.alibaba.cola.dto.Response;

/**
 * 
 * CommandExecutorI
 * 
 * @author fulan.zjf 2017年10月21日 下午11:01:05
 */
public interface ExecutorI<R extends Response, C extends Command> {

    /**
     *
     * @param cmd
     * @return
     */
    default R execute(C cmd){
        init(cmd);
        return execute();
    }

    /**
     * 初始化参数
     * @param cmd
     */
    default void init(C cmd){}

    /**
     * 执行业务
     * @return
     */
    default R execute(){
        return null;
    }
}
