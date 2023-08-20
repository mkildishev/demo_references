package com.example.demo.model;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.Map;

public class Monitor extends Stage implements EventListener {

    private LineChart<Number, Number> chart;

    public Monitor() {
        this.setTitle("Random Monitor");
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Случайный график");

        XYChart.Series<Number, Number> dataSeries = new XYChart.Series<>();
        dataSeries.setName("Данные");

        dataSeries.getData().add(new XYChart.Data<>(1, 2));
        dataSeries.getData().add(new XYChart.Data<>(2, 5));
        dataSeries.getData().add(new XYChart.Data<>(3, 8));
        dataSeries.getData().add(new XYChart.Data<>(4, 3));

        lineChart.getData().add(dataSeries);

        Scene scene = new Scene(lineChart, 800, 600);

        this.setScene(scene);
        chart = lineChart;
    }
    @Override
    public void update(Map<Number, Number> data) {
        for (var e : data.entrySet()) {
            chart.getData().get(0).getData().add(new XYChart.Data<>(e.getKey(), e.getValue()));
        }
    }
}
