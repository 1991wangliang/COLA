package com.alibaba.cola.event;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.framework.ColaException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 事件控制中枢
 * @author shawnzhan.zxy
 * @date 2017/11/20
 */
@SuppressWarnings("rawtypes")
@Component
public class EventHub {

    @Getter
    private ListMultimap<Class, EventHandlerI> eventRepository = ArrayListMultimap.create();

    @Getter
    private Map<Class<? extends EventHandlerI>, Class<? extends Response>> responseRepository = new HashMap<>();

    public List<EventHandlerI> getEventHandler(Class eventClass) {
        List<EventHandlerI> eventHandlerIList = findHandler(eventClass);
        if (eventHandlerIList == null || eventHandlerIList.size() == 0) {
            throw new ColaException(eventClass + "is not registered in eventHub, please register first");
        }
        return eventHandlerIList;
    }

    /**
     * 注册事件
     * @param eventClz
     * @param executor
     */
    public void register(Class<? extends EventI> eventClz, EventHandlerI executor){
        eventRepository.put(eventClz, executor);
        try {
            Class responseClazz = executor.getClass().getMethod("execute", EventI.class).getReturnType();
            if (Response.class.isAssignableFrom(responseClazz)) {
                responseRepository.put(executor.getClass(), responseClazz);
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

    private List<EventHandlerI> findHandler(Class<? extends EventI> eventClass){
        List<EventHandlerI> eventHandlerIList = null;
        Class cls = eventClass;
        eventHandlerIList = eventRepository.get(cls);
        return eventHandlerIList;
    }

}
