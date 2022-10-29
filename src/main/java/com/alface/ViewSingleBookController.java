package com.alface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSingleBookController extends BigController {
    @FXML Label descriptionLabel;
    @FXML Label bookTitle;
    @FXML ImageView coverImage;

    public ViewSingleBookController() {
        super();
    }
    
    @FXML
    public void initialize() {
        display();
    }

    @FXML
    public void display() {
        Book actualBook = App.getBooksList().get(App.getBookIndex());
        String oldThumb = actualBook.thumbnail;
        String newThumb = "";

        for (int i = 1; i < oldThumb.length() - 1; i++) 
            newThumb += oldThumb.charAt(i);
        
        try {
            coverImage.setImage(new Image(newThumb));
        } catch(Exception e) {
            System.out.println(newThumb);
        }
        
        bookTitle.setText(actualBook.getTitle());
        descriptionLabel.setText(actualBook.getDescription());
    }
}
