module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    opens com.example.demo.model to javafx.fxml;
    exports com.example.demo.model;
}