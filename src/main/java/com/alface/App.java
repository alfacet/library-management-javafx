package com.alface;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private static String user;
    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"));
        //..\..\..\images\book_login_page.png
        stage.getIcons().add(new Image("https://static.wikia.nocookie.net/minecraft_gamepedia/images/7/77/Enchanting_Table_JE4_BE2.png/revision/latest?cb=20200315175031"));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("Gerenciador de livros épico com doge");
        stage.show();
    }
    static public void setUser(String u)
    {
        user = u;
    }
    static public String getUser()
    {
        return user;
    }
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}