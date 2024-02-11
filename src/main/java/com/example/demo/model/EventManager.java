package com.example.demo.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * To show usual subscriber implementation, just substitute weak references to strong
 * and uncomment "unsubscribe" method
 */
public class EventManager {
    private List<WeakReference<EventListener>> listeners;

    public EventManager() {
        listeners = new ArrayList<>();
    }

    public void subscribe(EventListener listener) {
        listeners.add(new WeakReference<>(listener));
    }

//    public void unsubscribe(EventListener listener) {
//        listeners.remove(listener);
//    }

    public void notify(Map<Number, Number> data) {
        for (var listener : listeners) {
            if (listener.get() != null) {
                listener.get().update(data);
            }
        }
    }
}
