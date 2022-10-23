package com.alface;


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

public class AddBooksController {
    @FXML
    ListView<Node> lista;
    public void mudarPagina(String nome)
    {
        try {
            App.setRoot("home_page");
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
    public void initialize()
    {
        String path = "file:\\\\\\" + System.getProperty("user.dir") + "\\src\\main\\images\\";
        ObservableList<Node> lista2 = FXCollections.observableArrayList();
        Label teste = new Label("Hobbit");
        teste.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mudarPagina(teste.getText());
            }
       });
        Label livro2 = new Label("Duna");
        lista2.add(teste);
        lista2.add(livro2);
        lista.setItems(lista2);
    }
}
