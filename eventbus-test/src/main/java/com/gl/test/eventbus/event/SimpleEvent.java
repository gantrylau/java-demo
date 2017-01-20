package com.gl.test.eventbus.event;

/**
 * @author gantrylau
 * @since 2017/1/20
 */
public class SimpleEvent {

    private int count;

    public SimpleEvent(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "count:" + count;
    }
}
