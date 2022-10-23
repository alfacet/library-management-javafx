module com.alface {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.alface to javafx.fxml;
    exports com.alface;
}
