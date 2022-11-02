package com.alface;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ViewBooksController extends BigController {
    @FXML
    ListView<Label> listaLivros;
    @FXML
    Label labelErro;
    @FXML
    ImageView template;
    @FXML
    ImageView avatar;
    @FXML
    VBox tela;
    public void mudarPagina(int pos) {
        App.setAddedBookIndex(pos);
        App.setWhatList(App.ADDED_BOOKS_LIST);
        try {
            App.setRoot("view_single_book");
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public ViewBooksController() {
        super();
    }

    public void initialize() {

        display();
        preencherLista();
        verificarLista();
    }

    @FXML
    public void display() {
        avatar.setImage(new Image(super.getPathImages() + "avatar_doge.png"));
        template.setImage(new Image(super.getPathImages() + "templ_inventory.png"));
    }

    @FXML
    public void verificarLista() {
        if (listaLivros.getItems().isEmpty()) {
            labelErro.setText("Your list of books is empty!");
            labelErro.setVisible(true);
            listaLivros.setVisible(true);
        }

        else
            labelErro.setVisible(false);
    }

    @FXML
    public void preencherLista() {
        ObservableList<Label> lista2 = FXCollections.observableArrayList();

        for (int i = 0; i < App.getAddedBooksList().size(); i++) {
            int atual = i;
            Label a = new Label(App.getAddedBooksList().get(i).title);
            a.setFont(new Font("Open Sans Semibold", 15));
            a.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    mudarPagina(atual);
                }
            });
            a.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    lista2.get(atual).setTextFill(Color.RED);
                    tela.setCursor(Cursor.HAND);
                }
            });
            a.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    lista2.get(atual).setTextFill(Color.BLACK);
                    tela.setCursor(Cursor.DEFAULT);
                }
            });
            lista2.add(a);
        }

        listaLivros.setItems(lista2);
    }

    public void voltarPraHome() {
        try {
            App.setRoot("home_page");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}