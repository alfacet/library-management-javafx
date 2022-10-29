module com.alface {
    requires javafx.controls;
    requires javafx.fxml;
    requires mongo.java.driver;
    requires java.dotenv;
    requires com.google.api.client;
    requires com.google.gson;
    requires com.google.api.services.books;
    requires java.logging;

    opens com.alface to javafx.fxml;
    exports com.alface;
}
