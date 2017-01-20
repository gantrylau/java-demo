package com.gl.test.eventbus.handler;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;

/**
 * @author gantrylau
 * @since 2017/1/20
 */
@Scope("singleton")
@Component
@Lazy(false)
public class EventBusManager {

    private EventBus eventBus;

    private AsyncEventBus asyncEventBus;

    @PostConstruct
    public void init() {
        eventBus = new EventBus();
        eventBus.register(new SimpleEventActionHandler());
        System.out.println("同步eventbus初始化成功!");
        asyncEventBus = new AsyncEventBus(Executors.newFixedThreadPool(1));
        asyncEventBus.register(new SimpleEventActionHandler());
        System.out.println("异步eventbus初始化成功!");

    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public AsyncEventBus getAsyncEventBus() {
        return asyncEventBus;
    }

}
