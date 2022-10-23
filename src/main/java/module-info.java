module com.alface {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;

    opens com.alface to javafx.fxml;
    exports com.alface;
}
