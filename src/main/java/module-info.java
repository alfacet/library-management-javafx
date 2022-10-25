module com.alface {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires java.dotenv;
    requires java.logging;

    opens com.alface to javafx.fxml;
    exports com.alface;
}
