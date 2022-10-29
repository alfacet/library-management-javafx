package com.alface;


import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ViewBooksController extends BigController {
    @FXML ListView<Label> listaLivros;
    @FXML Label labelErro;
    @FXML ImageView template;
    @FXML ImageView avatar;

    public void mudarPagina(String nome) {
        try {
            App.setRoot("home_page");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public ViewBooksController() {
        super();
    }

    public void initialize() {
        
        display();
        ObservableList<Label> lista2 = FXCollections.observableArrayList();
        Label teste = new Label("Hobbit");
        teste.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mudarPagina(teste.getText());
            }
        });
        
        Label livro2 = new Label("Duna");
        for (Integer i = 0; i < 128; i++) {
            Label a = new Label("Livro " + i.toString());
            a.setFont(new Font("Open Sans Semibold", 15));
            lista2.add(a);
        }

        listaLivros.setItems(lista2);

        verificarLista();
        // for (int index = 0; index < listaLivros.getFixedCellSize(); index++) {
        //    listaLivros.getItems().get(index).setStyle("-fx-background-color: black;");
        // } 
    }

    @FXML
    public void display() {
        avatar.setImage(new Image(super.getPathImages() + "avatar_doge.png"));
        template.setImage(new Image(super.getPathImages() + "templ_inventory.png"));
    }

    public void verificarLista() {
        if (listaLivros.getItems().isEmpty()) {
            labelErro.setText("Your list of books is empty!");
            labelErro.setVisible(true);
            listaLivros.setVisible(true);
        }

        else labelErro.setVisible(false);
    }
    public void voltarPraHome()
    {
        try {
        App.setRoot("home_page");
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
}