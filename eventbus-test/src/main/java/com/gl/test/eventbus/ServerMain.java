package com.gl.test.eventbus;

import com.gl.test.eventbus.event.SimpleEvent;
import com.gl.test.eventbus.handler.EventBusManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gantrylau
 * @since 2017/1/20
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@RestController
@RequestMapping("/event-bus")
public class ServerMain {

    private static int count = 0;

    @Autowired
    private EventBusManager manager;

    /**
     * 同步的eventbus需要等post的event处理完才可以继续执行
     */
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@RequestParam int count) {
        manager.getEventBus().post(new SimpleEvent(count));
        return "success";
    }

    /**
     * 异步的eventbus不需要等post的event处理完，它会将所有的event放到一个队列中执行
     */
    @RequestMapping(value = "/test-async", method = RequestMethod.GET)
    public String asyncTest() {
        manager.getAsyncEventBus().post(new SimpleEvent(++count));
        return "success";
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerMain.class, args);
    }
}
