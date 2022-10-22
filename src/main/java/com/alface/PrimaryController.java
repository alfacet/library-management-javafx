package com.alface;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML VBox tela;
    @FXML CheckBox mostrar;
    @FXML PasswordField inputSenha;
    @FXML TextField senhaMostrada;
    @FXML TextField inputNome;
    @FXML Label messageLogin;

    
}
