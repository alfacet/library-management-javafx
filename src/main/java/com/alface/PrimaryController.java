package com.alface;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class PrimaryController {
    final Image IMG_OLHO_ABERTO = new Image("https://cdn-icons-png.flaticon.com/512/159/159465.png");
    final Image IMG_OLHO_FECHADO = new Image("https://icons.veryicon.com/png/o/internet--web/property-2/closed-eyes.png");
    final Image IMG_SENHA = new Image("https://cdn-icons-png.flaticon.com/512/891/891399.png");
    final Image IMG_USUARIO = new Image("https://cdn-icons-png.flaticon.com/512/149/149071.png");
    @FXML
    ImageView imagemSenha;
    @FXML
    ImageView imagemUsuario;
    @FXML
    ImageView mostrarSenha;

    public void initialize() {
        display();
    }

    @FXML
    public void display() {
        imagemSenha.setImage(IMG_SENHA);
        imagemUsuario.setImage(IMG_USUARIO);
        mostrarSenha.setImage(IMG_OLHO_ABERTO);

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setUser(inputNome.getText());
        App.setRoot("secondary");
    }

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
    public void mostrar() {
        if (mostrarSenha.getImage() == IMG_OLHO_ABERTO) {
            senhaMostrada.setText(inputSenha.getText());
            inputSenha.setVisible(false);
            senhaMostrada.setVisible(true);
            mostrarSenha.setImage(IMG_OLHO_FECHADO);
        } else {
            mostrarSenha.setImage(IMG_OLHO_ABERTO);
            inputSenha.setText(senhaMostrada.getText());
            senhaMostrada.setVisible(false);
            inputSenha.setVisible(true);
        }

    }
    @FXML
    public void mudarCursor()
    {
        tela.setCursor(Cursor.HAND);
    }
    @FXML
    public void voltarCursor()
    {
        tela.setCursor(Cursor.DEFAULT);
    }
}
