package com.example.demo.model;

import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Based on https://stackoverflow.com/questions/36861056/javafx-textfield-auto-suggestions
public class AutocompletionTextField extends TextField {

    private SoftReference<List<String>> cache;

    private final ContextMenu autocompleteWindow;

    public AutocompletionTextField() {
        super();
        cache = new SoftReference<>(new ArrayList<>());
        autocompleteWindow = new ContextMenu();
        setListener();
    }

    private void setListener() {
        //Add "suggestions" by changing text
        textProperty().addListener((observable, oldValue, newValue) -> {
            String enteredText = getText();
            //always hide suggestion if nothing has been entered (only "spacebars" are dissalowed in TextFieldWithLengthLimit)
            if (enteredText == null || enteredText.isEmpty()) {
                autocompleteWindow.hide();
            } else {

                //filter all possible suggestions depends on "Text", case insensitive
                List<String> filteredCache = safeStreamOf(cache.get())
                                             .filter(e -> e.toLowerCase().contains(enteredText.toLowerCase()))
                                             .collect(Collectors.toList());
                //some suggestions are found
                if (!filteredCache.isEmpty()) {
                    //build popup - list of "CustomMenuItem"
                    populatePopup(filteredCache, enteredText);
                    if (!autocompleteWindow.isShowing()) { //optional
                        autocompleteWindow.show(AutocompletionTextField.this, Side.BOTTOM, 0, 0); //position of popup
                    }
                    //no suggestions -> hide
                } else {
                    autocompleteWindow.hide();
                }
            }
        });

        //Hide always by focus-in (optional) and out
        focusedProperty().addListener((observableValue, oldValue, newValue) -> {
            autocompleteWindow.hide();
        });
    }

    /**
     * Populate the entry set with the given search results. Display is limited to 10 entries, for performance.
     *
     * @param searchResult The set of matching strings.
     */
    private void populatePopup(List<String> searchResult, String searchRequest) {
        //List of "suggestions"
        List<CustomMenuItem> menuItems = new LinkedList<>();
        //List size - 10 or founded suggestions count
        int maxEntries = 10;
        int count = Math.min(searchResult.size(), maxEntries);
        //Build list as set of labels
        for (int i = 0; i < count; i++) {
            final String result = searchResult.get(i);
            //label with graphic (text flow) to highlight founded subtext in suggestions
            Label entryLabel = new Label();
            entryLabel.setGraphic(buildTextFlow(result, searchRequest));
            entryLabel.setPrefHeight(10);
            CustomMenuItem item = new CustomMenuItem(entryLabel, true);
            menuItems.add(item);

            //if any suggestion is select set it into text and close popup
            item.setOnAction(actionEvent -> {
                setText(result);
                positionCaret(result.length());
                autocompleteWindow.hide();
            });
        }

        //"Refresh" context menu
        autocompleteWindow.getItems().clear();
        autocompleteWindow.getItems().addAll(menuItems);
    }

    public SoftReference<List<String>> getCache() {
        return cache;
    }

    public void setCache() {
        cache = new SoftReference<>(new ArrayList<>());
    }

    private <T> Stream<T> safeStreamOf(Collection<T> value) {
        return value == null || value.isEmpty() ? Stream.empty() : value.stream();
    }

    public TextFlow buildTextFlow(String text, String filter) {
        int filterIndex = text.toLowerCase().indexOf(filter.toLowerCase());
        Text textBefore = new Text(text.substring(0, filterIndex));
        Text textAfter = new Text(text.substring(filterIndex + filter.length()));
        Text textFilter = new Text(text.substring(filterIndex,  filterIndex + filter.length())); //instead of "filter" to keep all "case sensitive"
        textFilter.setFill(Color.ORANGE);
        textFilter.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
        return new TextFlow(textBefore, textFilter, textAfter);
    }


}
