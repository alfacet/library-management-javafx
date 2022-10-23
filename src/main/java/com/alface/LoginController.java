package com.alface;

import java.io.File;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import com.mongodb.DB;
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

public class LoginController {
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
    @FXML ImageView alertImg;

    static MongoClient cliente = new MongoClient(
        new MongoClientURI("mongodb+srv://root:NO@userdata.fhh1quh.mongodb.net/test"));

    static MongoDatabase banco = cliente.getDatabase("user_info");
    static Document dados = new Document();
    static MongoCollection colecao = banco.getCollection("data");
    static FindIterable<Document> it = colecao.find();
    static MongoCursor m = it.iterator();
    

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

    public boolean naoExisteNome() {    
        MongoCursor m = it.iterator();
        Document a;     

        while (m.hasNext()) {
            a = (Document) m.next();

            if (a.get("username").toString().equals(inputNome.getText()));
                return false;
        }

        return true;
    }

    @FXML
    public void registrar() {
        try {
            String senha = (inputSenha.isVisible()) ? inputSenha.getText() : inputShownPassword.getText();
            if (naoExisteNome()) {
                dados.append("username", inputNome.getText());
                dados.append("password", senha);
                banco.getCollection("data").insertOne(dados);
                messageLogin.setText("Signed up!");
                alertImg.setVisible(false);
                System.out.println("deu bom");
            }

            else {
                messageLogin.setText("This username already exists!");
                alertImg.setVisible(true);
            }
            

        } catch (Exception e) {
            System.out.println("fudeu");
        }
    }

    @FXML
    public void loginAction() {
        try {
            Document a;     

            while(m.hasNext()) {
                a = (Document) m.next();
                System.out.println(a.get("username"));
            }

        } catch (Exception e) {
            System.out.println("Impossible to connect!");
        } finally {
            m.close();
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
}
