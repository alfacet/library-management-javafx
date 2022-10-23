package com.alface;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

public class HomePageController {

    String path = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\";

    final Image IMG_LIVRO_ICON = new Image(path + "icon_book.png");
    final Image IMG_ADD_ICON = new Image(path + "icon_add.png");
    final Image IMG_STATS_ICON = new Image(path + "icon_stats.png");
    


    static Date dataAtual = new Date();


    @FXML Label titulo;
    @FXML ImageView imagemVerLivros;
    @FXML ImageView imagemAddLivro;
    @FXML ImageView imagemEstatisticas;
    @FXML VBox tela;

    public void initialize() {
        String message = "";
         
        
        switch (dataAtual.getHours()) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12: message += "Good morning, "; break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18: message += "Good afternoon, "; break;
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: message += "Good evening, "; break;
            
        }
        titulo.setText(message + " " + App.getUser() + "!");
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
    @FXML
    public void mudarPagina()
    {
        try {
        App.setRoot("add_books");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}