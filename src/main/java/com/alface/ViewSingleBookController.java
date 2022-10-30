package com.alface;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewSingleBookController extends BigController {
    @FXML TextArea descriptionLabel;
    @FXML Label bookTitle;
    @FXML ImageView coverImage;
    @FXML Label bookPages;
    @FXML Label messageLabel;
    @FXML Button addBookButton;
    static Book actualBook;
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
        actualBook  = App.getWhatList() == App.SEARCHED_BOOKS_LIST ? App.getBooksList().get(App.getBookIndex()) : App.getAddedBooksList().get(App.getAddedBookIndex());
        if(App.getWhatList() == App.SEARCHED_BOOKS_LIST)
            System.out.println("lista de pesquisados");
        else
            System.out.println("lista de adicionados");
        // System.out.println("added books list:");
        // for (int i = 0; i < App.getAddedBooksList().size(); i++) {
        //     System.out.println(App.getAddedBooksList().get(i).getTitle());
        // }
        // System.out.println("searched books list:");
        // for (int i = 0; i < App.getBooksList().size(); i++) {
        //     System.out.println(App.getBooksList().get(i).getTitle());
        // }
        display();

    }

    @FXML
    public void display() {
        boolean b = App.getWhatList() == App.SEARCHED_BOOKS_LIST ? true : false;
        addBookButton.setVisible(b);

    
        String oldThumb = actualBook.thumbnail;
        if (oldThumb != null) 
            coverImage.setImage(new Image(tiraAspas(oldThumb)));
        
        bookTitle.setText(actualBook.getTitle());

        Integer a = actualBook.getPageCount();
        bookPages.setText(a.toString());

        if(actualBook.getDescription() != null)
            descriptionLabel.setText(tiraAspas(actualBook.getDescription()));
    }
    @FXML
    public void addBook()
    {
        ArrayList<Book> l = App.getAddedBooksList();
        l.add(actualBook);
        App.setAddedBooksList(l);
        messageLabel.setVisible(true);
        try {
        App.setRoot("home_page");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public void backToHome()
    {
        try{
        App.setRoot("home_page");
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
