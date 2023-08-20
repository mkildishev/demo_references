package com.example.demo;

import com.example.demo.model.EventManager;
import com.example.demo.model.Monitor;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LogicController {

    private EventManager eventManager = new EventManager();

    @FXML
    public Menu removeSource;

    @FXML
    public Menu addSource;

    @FXML
    public MenuItem random;

    @FXML
    public MenuItem database;


    @FXML
    public void addRandomSource() {
        random.setOnAction(actionEvent -> {
            random.setOnAction(event -> addRandomSource());
            addSource.getItems().add(random);
        });
        removeSource.getItems().add(random);
    }

    @FXML
    public void addDBSource() {
        database.setOnAction(actionEvent -> {
            database.setOnAction(event -> addDBSource());
            addSource.getItems().add(database);
        });
        removeSource.getItems().add(database);
    }

    @FXML
    public void openRandomMonitor() {
        Monitor monitor = new Monitor();
        eventManager.subscribe(monitor);
        monitor.show();
    }

    @FXML
    public void update() {
        Random random = new Random();
        Map<Number, Number> a = new HashMap<>();
        a.put(random.nextInt(10), random.nextInt(10));
        a.put(random.nextInt(10), random.nextInt(10));
        a.put(random.nextInt(10), random.nextInt(10));
        eventManager.notify(a);
    }

    @FXML
    public void tryGC() {
        System.gc();
    }
}