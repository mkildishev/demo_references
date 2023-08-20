package com.example.demo.model;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventManager {
    private List<EventListener> listeners; // weak it!

    public EventManager() {
        listeners = new ArrayList<>();
    }

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void notify(Map<Number, Number> data) {
        for (var listener : listeners) {
            listener.update(data);
        }
    }
}
