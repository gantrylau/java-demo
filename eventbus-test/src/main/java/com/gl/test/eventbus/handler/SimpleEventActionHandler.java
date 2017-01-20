package com.gl.test.eventbus.handler;

import com.gl.test.eventbus.event.SimpleEvent;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author gantrylau
 * @since 2017/1/20
 */
@Component
public class SimpleEventActionHandler {

    @Subscribe
    @AllowConcurrentEvents
    public void handleSimpleEvent(SimpleEvent event) {
        Random random = new Random();
        try {
            Thread.sleep((random.nextInt(10)+1)*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(event.toString());
    }

}
