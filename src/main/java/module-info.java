module com.alface {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.alface to javafx.fxml;
    exports com.alface;
}
