package com.example.demo;

import com.example.demo.model.AutocompletionTextField;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class LogicController {

    @FXML
    private AutocompletionTextField textField;

    @FXML
    private Text heapSize;

    @FXML
    private Text heapLimit;

    private final MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();

    @FXML
    public void addText() {
        if (textField.getCache() == null) {
            textField.setCache();
        }
        textField.getCache().get().add(textField.textProperty().get());
        textField.clear();
    }

    @FXML
    public void addGarbage() {
        for (int i =  0; i < 1000000; ++i) {
            if (textField.getCache() == null) {
                textField.setCache();
            }
            textField.getCache().get().add(new String("Garbage" + i));
        }
    }

    @FXML
    public void initialize() {
        heapLimit.setText(memoryMXBean.getHeapMemoryUsage().getMax() / (1024 * 1024) + "MB");

        Thread checker = new Thread(() -> {
            while (true) {
                setHeapSize();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        checker.start();
    }

    public void setHeapSize() {
        heapSize.setText(memoryMXBean.getHeapMemoryUsage().getUsed() / (1024 * 1024) + "MB");
    }
}