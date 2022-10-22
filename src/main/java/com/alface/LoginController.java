package com.alface;

import java.io.File;
import java.io.IOException;

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

    String path = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\bookcase.png";

    final Image IMG_MINE = new Image(path);

    @FXML 
    public void initialize() {
        System.out.println(System.getProperty("user.dir") + "\\src\\main\\images\\bookcase.png");

        display();
    }

    @FXML
    public void display() {
        imagemTela.setImage(IMG_MINE);
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setUser(inputNome.getText());
        App.setRoot("secondary");
    }

    @FXML
    public void mudarCursor() {
        tela.setCursor(Cursor.HAND);
    }

    @FXML
    public void voltarCursor() {
        tela.setCursor(Cursor.DEFAULT);
    }
}
