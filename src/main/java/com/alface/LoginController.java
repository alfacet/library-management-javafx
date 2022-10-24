package com.alface;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.bson.Document;
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

public class LoginController extends BigController {
    @FXML VBox tela;
    @FXML CheckBox mostrar;
    @FXML PasswordField inputSenha;
    @FXML TextField senhaMostrada;
    @FXML TextField inputNome; 
    @FXML Label messageLogin; 
    @FXML ImageView imagemTela;
    @FXML Button loginButton;
    @FXML Label showPassword;
    @FXML TextField inputShownPassword;
    @FXML  ImageView alertImg;
    @FXML Button signinButton;
    
    
    
    final MongoClient cliente = new MongoClient(
            new MongoClientURI("mongodb+srv://root:pirugrosso@userdata.fhh1quh.mongodb.net/test"));

    final MongoDatabase banco = cliente.getDatabase("user_info");
    final Document dados = new Document();
    final MongoCollection colecao = banco.getCollection("data");
    final FindIterable<Document> it = colecao.find();
    final MongoCursor<Document> m = it.iterator();
   

    
    final Image IMG_MINE = new Image(super.pathImages + "bookcase.png");
    final Image IMG_ALERT = new Image(super.pathImages + "alert_icon.png");

    public LoginController()
    {
        super();
    }

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

    public boolean nomeEstaEmUso() {
        Document a;
        
        while (m.hasNext()) {
            a = m.next();
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
            if(senha.equals("") && inputNome.getText().equals(""))
            {
                messageLogin.setText("The name and password fields cannot be empty!");
                alertImg.setVisible(true);
            }
            else if(senha.equals(""))
            {
                messageLogin.setText("The password field cannot be empty!");
                alertImg.setVisible(true);
            }

            else if(inputNome.getText().equals(""))
            {
                messageLogin.setText("The name field cannot be empty!");
                alertImg.setVisible(true);  
            }
            else if (nomeEstaEmUso()) {
                messageLogin.setText("This username already exists!");
                alertImg.setVisible(true);
            }
            else 
                err = false;
            
            if(!err)
            {
                dados.append("username", inputNome.getText());
                dados.append("password", senha);
                banco.getCollection("data").insertOne(dados);
                App.setUser(inputNome.getText());
                alertImg.setVisible(false);
                System.out.println("deu bom");
                App.setRoot("home_page");
            }
            
        } catch (Exception e) {
            System.out.println(e);
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
                String senha = (inputSenha.isVisible()) ? inputSenha.getText() : inputShownPassword.getText();
                Document a;
                MongoCollection colecao = banco.getCollection("data");
                FindIterable<Document> it = colecao.find();
                MongoCursor<Document> m = it.iterator();
                while (m.hasNext()) {
                    a = m.next();

                    if (a.get("username").toString().equals(inputNome.getText())) {
                        if (a.get("password").toString().equals(senha)) {
                            App.setUser(inputNome.getText());
                            App.setRoot("home_page");
                        } else {
                            messageLogin.setText("Invalid password!");
                            System.out.println(senha);
                            alertImg.setVisible(true);
                        }
                    } //else {
                    //     for (int index = 0; index < 5; index++) {
                    //         System.out.println();
                    //     }
                    //     System.out.println("nome no banco: " + a.get("username"));
                    //     System.out.println("Nome na input: " + inputNome.getText());
                    //     for (int index = 0; index < 5; index++) {
                    //         System.out.println();
                    //     }
                    // }
                }
            } catch (Exception e) {
                System.out.println(e);
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
//  #2F4978
    @FXML
    public void changeSignIn()
    {
        tela.setCursor(Cursor.HAND);
        signinButton.setStyle("-fx-background-color: gray; -fx-background-radius: 8px;");
        // signinButton.
        // signinButton.setStyle("-fx-background-radius: 8px;");
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
