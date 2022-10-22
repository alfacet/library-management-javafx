package com.alface;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class SecondaryController {

    final Image IMG_LIVRO_ICON = new Image("https://cdn-icons-png.flaticon.com/512/926/926321.png");
    final Image IMG_ADD_ICON = new Image("https://cdn-icons-png.flaticon.com/512/148/148781.png");
    final Image IMG_STATS_ICON = new Image("https://cdn-icons-png.flaticon.com/512/3309/3309960.png");
   

    @FXML Label titulo;
    @FXML ImageView imagemVerLivros;
    @FXML ImageView imagemAddLivro;
    @FXML ImageView imagemEstatisticas;
    @FXML VBox tela;
    @FXML Circle circulo1;
    
    public void initialize() {
        titulo.setText("Olá, " + App.getUser() + "!");
        imagemVerLivros.setImage(IMG_LIVRO_ICON);
        imagemAddLivro.setImage(IMG_ADD_ICON);
        imagemEstatisticas.setImage(IMG_STATS_ICON);
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("login");
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