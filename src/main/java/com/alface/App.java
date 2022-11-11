package com.alface;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.stage.Stage;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

public class App extends Application {
    private static String user;
    private static String userId;
    private static Scene scene;
    private static ArrayList<Book> booksList;
    private static ArrayList<Book> addedBooksList = new ArrayList<Book>();
    private static int bookIndex;
    private static int addedBookIndex;
    private static int whatList;
    private static ArrayList<Rating> ratingsList = new ArrayList<Rating>();
    public static final int SEARCHED_BOOKS_LIST = 1;
    public static final int ADDED_BOOKS_LIST = 2;
    @Override
    public void start(Stage stage) throws IOException {
        try {
            scene = new Scene(loadFXML("login"));
            // scene.getStylesheets().add("index.css");
            //..\..\..\images\book_login_page.png
            stage.getIcons().add(new Image("https://static.wikia.nocookie.net/minecraft_gamepedia/images/7/77/Enchanting_Table_JE4_BE2.png/revision/latest?cb=20200315175031"));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.setTitle("Epic book manager with doge :)");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error to initialize :(");
            e.printStackTrace();
        }
    }

    public static ArrayList<Rating> getRatingsList() {
        return ratingsList;
    }

    public static void setRatingsList(ArrayList<Rating> ratingsList) {
        App.ratingsList = ratingsList;
    }

    public static ArrayList<Book> getAddedBooksList() {
        return addedBooksList;
    }

    public static void setAddedBooksList(ArrayList<Book> addedBooksList) {
        App.addedBooksList = addedBooksList;
    }

    public static int getAddedBookIndex() {
        return addedBookIndex;
    }

    public static void setAddedBookIndex(int addedBookIndex) {
        App.addedBookIndex = addedBookIndex;
    }
    
    static public ArrayList<Book> getBooksList() {
        return booksList;
    }

    static public void setBooksList(ArrayList<Book> bl) {
        booksList = bl;
    }

    static public void setUser(String u)  {
        user = u;
    }
    public static int getBookIndex() {
        return bookIndex;
    }

    public static void setBookIndex(int bookIndex) {
        App.bookIndex = bookIndex;
    }

    static public String getUser() {
        return user;
    }
    public static int getWhatList() {
        return whatList;
    }

    public static void setWhatList(int whatList) {
        App.whatList = whatList;
    }

    static void setRoot(String fxml) throws IOException {
        // scene.getStylesheets().add(App.class.getClass().getResource(path + "AddBook.css").toExternalForm());
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