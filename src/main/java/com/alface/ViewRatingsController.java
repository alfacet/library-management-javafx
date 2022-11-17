package com.alface;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.bson.Document;

import com.google.api.client.json.Json;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import io.github.cdimascio.dotenv.Dotenv;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewRatingsController extends BigController {
    Dotenv dotenv = Dotenv.configure()
            .directory("./.env")
            .ignoreIfMalformed() //
            .ignoreIfMissing()
            .load();

    final MongoClient cliente = new MongoClient(
            new MongoClientURI(dotenv.get("MONGO_URI")));
    final MongoDatabase banco = cliente.getDatabase("user_info");
    final MongoCollection<Document> colecao = banco.getCollection("ratings");
    final FindIterable<Document> it = colecao.find();
    final MongoCursor<Document> mongoCursor = it.iterator();
    final Gson gson = new Gson();
    @FXML
    Label titleLabel;
    @FXML
    Label mediaLabel;
    @FXML
    ListView<Node> ratingsList;
    @FXML
    ImageView happy;
    @FXML
    ImageView sad;
    @FXML
    ImageView angry;

    public void initialize() {
        System.out.println("asdasd");
        display();
    }

    public void display()
    {
        happy.setImage(new Image(super.getPathImages() + "happy_doge.png"));
        sad.setImage(new Image(super.getPathImages() + "sad_doge.png"));
        angry.setImage(new Image(super.getPathImages() + "angry_doge.png"));

        Book actualBook = App.getBooksList().get(App.getBookIndex());
        ArrayList<Double> avaliacoes = new ArrayList<Double>();
        titleLabel.setText(actualBook.getTitle());
        ObservableList<Node> lista = FXCollections.observableArrayList();
        Document a = mongoCursor.next();
        JsonElement dividido = JsonParser.parseString(a.get("ratings").toString());
        JsonArray vetor = dividido.getAsJsonArray();

        for (int i = 0; i < vetor.size(); i++) {
            Rating rating = gson.fromJson(vetor.get(i), Rating.class);
            if(rating.getTitle().equals(actualBook.getTitle()))
            {
                Label nome = new Label(rating.getAuthor() + " - " + rating.getRating() + " /10");
                nome.setFont(new Font("Open Sans Semibold", 20));
                Text comm = new Text(rating.getCommentary());
                comm.setWrappingWidth(431.11561584472656);
                comm.setFont(new Font("Open Sans", 16));
                lista.add(nome);
                lista.add(comm);
                avaliacoes.add(rating.getRating());
            }
           
        }
        ratingsList.setItems(lista);
        if (lista.isEmpty())
           mediaLabel.setText("This book is not rated yet"); 
        else
            mediaLabel.setText("This book has a average rating of " + media(avaliacoes) + " / 10");
    }
    public String media(ArrayList<Double> list) {
        DecimalFormat format = new DecimalFormat("0.0");

        double soma = 0;
        Double media;

        for (int i = 0; i < list.size(); i++) 
            soma += list.get(i);

        media = soma / list.size();
        
        return format.format(media).toString();
    }

    public void backToHome() {
        try {
            App.setRoot("home_page");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}