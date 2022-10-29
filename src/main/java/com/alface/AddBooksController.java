package com.alface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.books.model.Review;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class AddBooksController extends BigController {

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

    public void searchBooks() {
        JsonReader reader;
        try {
            if (inputBookName.getText().equals(""))
                errorLabel.setText("This field cannot be empty!");
            else {
                String pesquisa = inputBookName.getText();
                pesquisa = pesquisa.replaceAll(" ", "%20");
                URL link = new URL(
                        "https://www.googleapis.com/books/v1/volumes?q=" + pesquisa + ":keyes" + dotenv.get("GOOGLE_API"));
                HttpURLConnection conexao = (HttpURLConnection) link.openConnection();
                conexao.setRequestMethod("GET");
                reader = new JsonReader(new InputStreamReader(conexao.getInputStream()));
                JsonElement dividido = JsonParser.parseReader(reader);
                JsonObject obj = dividido.getAsJsonObject();
                JsonArray vetor = obj.get("items").getAsJsonArray();

                ObservableList<Label> lista2 = FXCollections.observableArrayList();
                for (int i = 0; i < vetor.size(); i++) {
                    JsonObject temp = vetor.get(i).getAsJsonObject();
                    JsonObject info = temp.get("volumeInfo").getAsJsonObject();
                    Book livro = new Book(info.get("title").toString(), 23);
                    lista2.add(new Label(livro.getTitle()));
                }
                booksList.setItems(lista2);
            }
        } catch (Exception e) {

            System.out.println(e);
        }
    }
}
