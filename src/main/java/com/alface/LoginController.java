package com.alface;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.sql.DriverManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;

public class LoginController {
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

    // Quando for colocar imagens, usa o path + o nome da imagem pra ficar mais
    // fácil c:
    String path = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\";

    final Image IMG_MINE = new Image(path + "bookcase.png");
    final Image IMG_ALERT = new Image(path + "alert_icon.png");

    @FXML
    public void initialize() {
        display();
    }

    @FXML
    public void display() {

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

    @FXML
    public void registrar() {
        try {
            MongoClient cliente = new MongoClient(new MongoClientURI("mongodb+srv://root:NO@userdata.fhh1quh.mongodb.net/test"));

            MongoDatabase banco = cliente.getDatabase("user_info");
            Document dados = new Document();
            dados.append("username", inputNome.getText());
            dados.append("password", inputSenha.getText());  
            banco.getCollection("data").insertOne(dados);

            System.out.println("deu bom");
            cliente.close();
        
        } catch (Exception e) {
            System.out.println("fudeu");
        }
    }

    @FXML
    public void loginAction() {
        System.out.println("aaaa");
    }
    // @FXML
    // public void registrar() {
    // String myDriver = "com.mysql.cj.jdbc.Driver";
    // String myUrl = "jdbc:mysql://localhost:3306/bomba";

    // try {
    // Class.forName(myDriver);
    // } catch (Exception e2) {
    // System.out.println(e2);
    // }

    // String sql = "insert into user_data(username, password) values(?, ?)";

    // try {
    // Connection conn = DriverManager.getConnection(myUrl, "root", "NO");
    // PreparedStatement preparedStmt = conn.prepareStatement(sql);
    // preparedStmt.setString(1, inputNome.getText());
    // preparedStmt.setString(2, inputSenha.getText());
    // preparedStmt.execute();
    // messageLogin.setText("Signed up!");
    // conn.close();
    // } catch(Exception e) {
    // messageLogin.setText("This username already exists!");
    // alertImg.setVisible(true);
    // // System.out.println(e);
    // }
    // }

    // @FXML
    // void loginAction(ActionEvent event) {
    // if (inputNome.getText().isBlank() == false && inputSenha.getText().isBlank()
    // == false)
    // login();

    // else {
    // alertImg.setVisible(true);

    // if (inputNome.getText().isBlank() && inputSenha.getText().isBlank())
    // messageLogin.setText("The name and password fields cannot be empty!");

    // else if (inputSenha.getText().isBlank())
    // messageLogin.setText("The password field cannot be empty!");

    // else
    // messageLogin.setText("The name field cannot be empty!");
    // }
    // }

    // @FXML
    // void login() {
    // String nome = inputNome.getText();
    // String senha = (!inputShownPassword.isVisible()) ? inputSenha.getText() :
    // inputShownPassword.getText();

    // Database connect = new Database();
    // Connection connection = connect.getConnection();

    // String verify = "select count(1) from user_data where username = '" + nome +
    // "' and password = '" + senha + "'";

    // try {
    // Statement statement = connection.createStatement();
    // ResultSet result = statement.executeQuery(verify);

    // while (result.next()) {
    // if (result.getInt(1) == 1) {
    // App.setUser(inputNome.getText());
    // App.setRoot("home_page");
    // } else {
    // messageLogin.setText("bruh");
    // messageLogin.setVisible(true);
    // }
    // }
    // } catch (Exception e) {
    // System.out.println(e);
    // System.out.println("Impossível conectar");
    // }
    // }

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
}
