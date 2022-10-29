package com.alface;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSingleBookController extends BigController {
    @FXML Label descriptionLabel;
    @FXML Label bookTitle;
    @FXML ImageView coverImage;
    @FXML Label bookPages;

    public ViewSingleBookController() {
        super();
    }
    
    public String tiraAspas(String x) {
        String x2 = "";

        for (int index = 1; index < x.length() - 1; index++) 
            x2 += x.charAt(index);
        
        return x2;
    }
    @FXML
    public void initialize() {
        display();
    }

    @FXML
    public void display() {
        Book actualBook = App.getBooksList().get(App.getBookIndex());
        String oldThumb = actualBook.thumbnail;
        coverImage.setImage(new Image(tiraAspas(oldThumb)));
        
        bookTitle.setText(actualBook.getTitle());

        Integer a = actualBook.getPageCount();
        bookPages.setText(a.toString());

        descriptionLabel.setText(tiraAspas(actualBook.getDescription()));
    }
}
