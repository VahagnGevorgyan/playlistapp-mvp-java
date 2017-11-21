package com.playlistapp.eventbus;


import com.squareup.otto.Bus;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Singleton class for event bus.
 */
public class SingletonBus {

    private static SingletonBus instance;
    private Bus mBus;

    public static SingletonBus getInstance() {
        return instance;
    }

    public static void initialize() {
        if (instance == null) {
            instance = new SingletonBus();
        }
    }


    private final LinkedList<Object> eventQueueBuffer = new LinkedList<>();

    private boolean paused;

    private SingletonBus() {
        mBus = new Bus();
    }

    public <T> void post(final T event) {
        if (paused) {
            eventQueueBuffer.add(event);
        } else {
            mBus.post(event);
        }
    }

    public <T> void register(T subscriber) {
        mBus.register(subscriber);
    }

    public <T> void unregister(T subscriber) {
        mBus.unregister(subscriber);
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
        if (!paused) {
            Iterator<Object> eventIterator = eventQueueBuffer.iterator();
            while (eventIterator.hasNext()) {
                Object event = eventIterator.next();
                post(event);
                eventIterator.remove();
            }
        }
    }
}
