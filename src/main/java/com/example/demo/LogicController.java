package com.example.demo;

import com.example.demo.model.AutocompletionTextField;
import javafx.fxml.FXML;

public class LogicController {

    @FXML
    private AutocompletionTextField textField;

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
}