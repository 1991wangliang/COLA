package com.alibaba.cola.executor;

import com.alibaba.cola.dto.Query;
import com.alibaba.cola.dto.Response;

public interface QueryExecutorI<R extends Response, C extends Query> extends ExecutorI<R,C> {

}
