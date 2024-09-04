module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.management;

    opens com.example.demo to javafx.fxml;
    opens com.example.demo.model to javafx.fxml;
    exports com.example.demo;
}