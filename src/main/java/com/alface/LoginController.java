package com.alface;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import java.util.logging.Logger;

public class LoginController extends BigController {
    @FXML
    VBox tela;
    @FXML
    CheckBox mostrar;
    @FXML
    PasswordField inputSenha;
    @FXML
    TextField senhaMostrada;
    @FXML
    TextField inputNome;
    @FXML
    Label messageLogin;
    @FXML
    ImageView imagemTela;
    @FXML
    Button loginButton;
    @FXML
    Label showPassword;
    @FXML
    TextField inputShownPassword;
    @FXML
    ImageView alertImg;
    @FXML
    Button signinButton;

    Dotenv dotenv = Dotenv.configure()
            .directory("./.env")
            .ignoreIfMalformed() //
            .ignoreIfMissing()
            .load();

    final MongoClient cliente = new MongoClient(
            new MongoClientURI(dotenv.get("MONGO_URI")));

    final MongoDatabase banco = cliente.getDatabase("user_info");
    final Document dados = new Document();
    final MongoCollection colecao = banco.getCollection("data");
    final FindIterable<Document> it = colecao.find();
    final MongoCursor<Document> mongoCursor = it.iterator();

    public LoginController() {
        super();
    }

    final Image IMG_MINE = new Image(super.getPathImages() + "bookcase.png");
    final Image IMG_ALERT = new Image(super.getPathImages() + "alert_icon.png");

    public enum Operations {

    }

    @FXML
    public void initialize() {
        display();
        MongoCollection<Document> colecao2 = banco.getCollection("ratings");
        Document a = new Document();
        a.append("teste", "filipe eh gay");
        colecao2.insertOne(a);
        System.setProperty("DEBUG.MONGO", "true");
        System.setProperty("DB.TRACE", "true");
    }

    @FXML
    public void display() {
        super.setPath();
        imagemTela.setImage(IMG_MINE);
        alertImg.setImage(IMG_ALERT);
    }

    @FXML
    public void mudarCursor() {
        tela.setCursor(Cursor.HAND);
    }

    @FXML
    public void voltarCursor() {
        tela.setCursor(Cursor.DEFAULT);
    }

    public boolean nomeEstaEmUso() {
        MongoCollection colec = banco.getCollection("data");
        FindIterable<Document> it = colec.find();
        MongoCursor<Document> mc = it.iterator();

        Document a;
        
        while (mc.hasNext()) {
            a = mc.next();

            if (a.get("username").toString().equals(inputNome.getText()))
                return true;
        }

        return false;
    }

    @FXML
    public void registrar() {
        boolean err = true;

        try {
            String senha = (inputSenha.isVisible()) ? inputSenha.getText() : inputShownPassword.getText();

            if (senha.equals("") && inputNome.getText().equals("")) {
                messageLogin.setText("The name and password fields cannot be empty!");
                alertImg.setVisible(true);
            }

            else if (senha.equals("")) {
                messageLogin.setText("The password field cannot be empty!");
                alertImg.setVisible(true);
            }

            else if (inputNome.getText().equals("")) {
                messageLogin.setText("The name field cannot be empty!");
                alertImg.setVisible(true);
            }

            else if (nomeEstaEmUso()) {
                messageLogin.setText("This username already exists!");
                alertImg.setVisible(true);
            }

            else
                err = false;

            if (!err) {
                dados.append("username", inputNome.getText());
                dados.append("password", senha);
                banco.getCollection("data").insertOne(dados);

                App.setUser(inputNome.getText());

                alertImg.setVisible(false);

                System.out.println("deu bom");

                App.setRoot("home_page");
            }

        } catch (Exception e) {
            messageLogin.setText("This username already exists!");
            alertImg.setVisible(true);
        }
    }

    @FXML
    public void loginAction() {
        if (inputNome.getText().isBlank() && inputSenha.getText().isBlank()) {
            messageLogin.setText("The name and password fields cannot be empty!");
            alertImg.setVisible(true);
        }

        else if (inputSenha.getText().isBlank()) {
            messageLogin.setText("The password field cannot be empty!");
            alertImg.setVisible(true);
        }

        else if (inputNome.getText().isBlank()) {
            messageLogin.setText("The name field cannot be empty!");
            alertImg.setVisible(true);
        }

        else {
            try {
                FindIterable<Document> iterator = colecao.find();
                MongoCursor<Document> mongoCursor = iterator.iterator();
                Gson gson = new Gson();
                String senha = (inputSenha.isVisible()) ? inputSenha.getText() : inputShownPassword.getText();
                Document a;

                if (!nomeEstaEmUso()) {
                    messageLogin.setText("This username does not exists!");
                    System.out.println(senha);
                    alertImg.setVisible(true);
                } else {
                    while (mongoCursor.hasNext()) {
                        a = mongoCursor.next();

                        if (a.get("username").toString().equals(inputNome.getText())) {

                            if (a.get("password").toString().equals(senha)) {
                                ArrayList<Book> lista = new ArrayList<Book>();

                                JsonArray testezinho = JsonParser.parseString(a.get("books").toString())
                                        .getAsJsonArray();

                                for (int i = 0; i < testezinho.size(); i++)
                                    lista.add(gson.fromJson(testezinho.get(i), Book.class));

                                App.setAddedBooksList(lista);
                                App.setUser(inputNome.getText());
                                App.setRoot("home_page");

                            } else {
                                messageLogin.setText("Invalid password!");
                                System.out.println(senha);
                                alertImg.setVisible(true);
                            }
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Impossible to connect!");
            }
        }
    }

    @FXML
    public void showPasswordAction() {
        if (showPassword.getText().equals("Show password")) {
            showPassword.setText("Hide password");
            inputShownPassword.setText(inputSenha.getText());
            inputShownPassword.setVisible(true);
            inputSenha.setVisible(false);
        }

        else {
            showPassword.setText("Show password");
            inputSenha.setText(inputShownPassword.getText());
            inputShownPassword.setVisible(false);
            inputSenha.setVisible(true);
        }
    }

    @FXML
    public void changeCursor() {
        tela.setCursor(Cursor.HAND);
    }

    @FXML
    public void backToCursor() {
        tela.setCursor(Cursor.DEFAULT);
    }

    @FXML
    public void changeLogin() {
        tela.setCursor(Cursor.HAND);
        loginButton.setStyle("-fx-background-color: #3F508B;-fx-background-radius: 8px;");
    }

    // #2F4978
    @FXML
    public void changeSignIn() {
        tela.setCursor(Cursor.HAND);
        signinButton.setStyle("-fx-background-color: gray; -fx-background-radius: 8px;");
    }

    @FXML
    public void backLogin() {
        tela.setCursor(Cursor.DEFAULT);
        loginButton.setStyle("-fx-background-color: #2F4978;-fx-background-radius: 8px;");
    }

    @FXML
    public void backSignIn() {
        tela.setCursor(Cursor.DEFAULT);
        signinButton.setStyle("-fx-background-color: #b5b5b5;-fx-background-radius: 8px;");
    }
}
