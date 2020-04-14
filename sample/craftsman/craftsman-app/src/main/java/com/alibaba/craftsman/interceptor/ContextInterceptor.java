package com.alibaba.craftsman.interceptor;

import com.alibaba.cola.dto.Command;
import com.alibaba.cola.executor.ExecutorInterceptorI;
import com.alibaba.cola.executor.PreInterceptor;
import com.alibaba.craftsman.context.UserContext;

@PreInterceptor
public class ContextInterceptor implements ExecutorInterceptorI {

    public final static String SYS_USER = "System";

    public void preIntercept(Command command) {
        UserContext content = new UserContext();
        content.setOperator(ContextInterceptor.SYS_USER);
    }

}
