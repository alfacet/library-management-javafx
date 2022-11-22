package com.alface;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class AddBooksController extends BigController {
    static ArrayList<Book> lista = new ArrayList<Book>();
    static Gson gson = new Gson();
    Dotenv dotenv = Dotenv.configure()
            .directory("./.env")
            .ignoreIfMalformed() //
            .ignoreIfMissing()
            .load();

    @FXML
    Button searchButton;
    @FXML
    ListView<Label> booksList;
    @FXML
    TextField inputBookName;
    @FXML
    Label errorLabel;
    @FXML 
    ImageView loadingGif;
    @FXML
    VBox tela;
    @FXML
    ImageView filBooks1;
    @FXML
    ImageView filBooks2;
    @FXML
    ImageView bookshelf;
    @FXML
    ImageView bookshelf2;
    @FXML
    ImageView plant;
    @FXML
    ImageView plant2;

    public void initialize() {
        display();
    }

    @FXML
    public void display() {
        filBooks1.setImage(new Image(super.getPathImages() + "add_books.png"));
        filBooks2.setImage(new Image(super.getPathImages() + "add_books2.png"));
        bookshelf.setImage(new Image(super.getPathImages() + "bookshelf.png"));
        bookshelf2.setImage(new Image(super.getPathImages() + "bookshelf.png"));
        plant.setImage(new Image(super.getPathImages() + "plant_pot.png"));
        plant2.setImage(new Image(super.getPathImages() + "plant_pot.png"));
    }

    public String tiraAspas(String x) {
        String x2 = "";

        for (int index = 1; index < x.length() - 1; index++)
            x2 += x.charAt(index);

        return x2;
    }

    public void searchBooks() {
        JsonReader reader;

        try {

            if (!inputBookName.getText().equals("")) {
                String pesquisa = inputBookName.getText();
                System.out.println(pesquisa);
                loadingGif.setImage(new Image(super.getPathImages() + "loading-gif.gif"));
                loadingGif.setVisible(true);
                pesquisa = pesquisa.replaceAll(" ", "%20");

                URL link = new URL(
                        "https://www.googleapis.com/books/v1/volumes?q=" + pesquisa + "&key=AIzaSyDYoN4hV1uZKECKr5J0Lva1nWbp8cZtg1c");
                System.out.println(link);
                HttpURLConnection conexao = (HttpURLConnection) link.openConnection();
                conexao.setRequestMethod("GET");
                reader = new JsonReader(new InputStreamReader(conexao.getInputStream()));

                JsonElement dividido = JsonParser.parseReader(reader);
                JsonObject obj = dividido.getAsJsonObject();
                JsonArray vetor = obj.get("items").getAsJsonArray();

                ObservableList<Label> lista2 = FXCollections.observableArrayList();
                lista = new ArrayList<Book>();
                for (int i = 0; i < vetor.size(); i++) {

                    int atual = i;

                    JsonObject temp = vetor.get(i).getAsJsonObject();
                
                    JsonObject info = temp.get("volumeInfo").getAsJsonObject();
                    Book added = gson.fromJson(info, Book.class);
                    
                    if(info.get("imageLinks") != null)
                        added.setThumbnail(info.get("imageLinks").getAsJsonObject().get("smallThumbnail").toString());
                    
                    lista.add(added);

                    Label adicionado = new Label(lista.get(i).getTitle());

                    adicionado.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            mudarPagina(atual);
                        }
                    });

                    adicionado.setOnMouseEntered(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            lista2.get(atual).setTextFill(Color.RED);
                            tela.setCursor(Cursor.HAND);
                        }
                    });
                    adicionado.setOnMouseExited(new EventHandler<MouseEvent>() {
                        public void handle(MouseEvent event) {
                            lista2.get(atual).setTextFill(Color.BLACK);
                            tela.setCursor(Cursor.DEFAULT);
                        }
                    });
                    lista2.add(adicionado);
                    App.setBooksList(lista);
                }

                booksList.setItems(lista2);
                loadingGif.setVisible(false);
                conexao.disconnect();
            } else
                errorLabel.setText("This field cannot be empty!");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    public void mudarPagina(int posicao) {

        App.setBookIndex(posicao);
        App.setWhatList(App.SEARCHED_BOOKS_LIST);
        try {
            App.setRoot("view_single_book");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void switchToHome() {
        try {
            App.setRoot("home_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
