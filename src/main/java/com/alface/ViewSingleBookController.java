package com.alface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ViewSingleBookController extends BigController {
    Dotenv dotenv = Dotenv.configure()
            .directory("./.env")
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    final MongoClient cliente = new MongoClient(new MongoClientURI(dotenv.get("MONGO_URI")));
    final MongoDatabase banco = cliente.getDatabase("user_info");
    final Document dados = new Document();
    final MongoCollection<Document> colecao = banco.getCollection("data");
    final FindIterable<Document> it = colecao.find();
    final MongoCursor<Document> mongoCursor = it.iterator();
    final Gson gson = new Gson();
    @FXML TextArea descriptionLabel;
    @FXML Label bookTitle;
    @FXML ImageView coverImage;
    @FXML Label bookPages;
    @FXML Label pagesLabel;
    @FXML Label messageLabel;
    @FXML Button addBookButton;
    @FXML Label authorsLabel;
    @FXML Button removeBookButton;
    @FXML Button rateButton;
    @FXML VBox modal;
    @FXML Button submitRateButton;
    @FXML TextArea ratingCommentaryInput;
    @FXML Label descriptionTitle;
    @FXML ComboBox<String> selectRatingComboBox;
    @FXML Button backButton;
    @FXML Button viewRatingButton;
    @FXML Label errorLabel;
    @FXML ImageView h_frame;
    @FXML ImageView h_frame2;
    @FXML ImageView wow;
    @FXML ImageView formal_doge;
    
    static Book actualBook;

    public ViewSingleBookController() {
        super();
    }

    final Image ERROR_IMG = new Image(super.getPathImages() + "error_icon.png");

    public String tiraAspas(String x) {
        String x2 = "";

        for (int index = 1; index < x.length() - 1; index++)
            x2 += x.charAt(index);

        return x2;
    }

    @FXML
    public void initialize() {
        actualBook = App.getWhatList() == App.SEARCHED_BOOKS_LIST ? App.getBooksList().get(App.getBookIndex())
                : App.getAddedBooksList().get(App.getAddedBookIndex());
        display();
    }

    @FXML
    public void display() {
        h_frame.setImage(new Image(super.getPathImages() + "half_frame.png"));
        h_frame2.setImage(new Image(super.getPathImages() + "half_frame2.png"));
        wow.setImage(new Image(super.getPathImages() + "wow.png"));
        formal_doge.setImage(new Image(super.getPathImages() + "formal_doge.png"));
        boolean b = App.getWhatList() == App.SEARCHED_BOOKS_LIST ? true : false;
        addBookButton.setVisible(b);
        removeBookButton.setVisible(!b);
        viewRatingButton.setVisible(b);
        rateButton.setVisible(!b);
        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event)
            {
                if(b)
                    changePage("add_books");
                else
                    changePage("view_books");
            }
            });
        String oldThumb = actualBook.thumbnail;
        if (oldThumb != null)
            coverImage.setImage(new Image(tiraAspas(oldThumb)));
        else
            coverImage.setImage(ERROR_IMG);
        bookTitle.setText(actualBook.getTitle());

        Integer a = actualBook.getPageCount();
        bookPages.setText(a.toString());

        if (actualBook.getDescription() != null)
            descriptionLabel.setText(actualBook.getDescription());
        else
            descriptionLabel.setText("Description not avaliable! :C");

        if(actualBook.authors != null)
        {    
            if (actualBook.authors.isEmpty())
                    authorsLabel.setText("Authors not avaliable! :C");
            
            else {
                String text = actualBook.getAuthors().get(0);
                for (int i = 1; i < actualBook.getAuthors().size(); i++) {
                    text += ", " + actualBook.getAuthors().get(i);
                }
                authorsLabel.setText(text);
            }
        }
        else
            authorsLabel.setText("Authors not avaliable! :C");

        selectRatingComboBox.setStyle("-fx-font: 16px \"Open Sans Semibold\";");
        ObservableList<String> coisas = FXCollections.observableArrayList();
        for (Integer i = 1; i <= 10; i++) 
            coisas.add(i.toString());
        
        selectRatingComboBox.setItems(coisas);
    }

    @FXML
    public void addBook() {
        ArrayList<Book> l = App.getAddedBooksList();
        l.add(actualBook);
        App.setAddedBooksList(l);

        messageLabel.setVisible(true);
        try {
            App.setRoot("home_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Book> livrosAdicionados = App.getAddedBooksList();
        try{
        
        colecao.updateOne(eq("username", App.getUser()), combine(set("books", gson.toJson(livrosAdicionados))));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void removeBook()
    {
        ArrayList<Book> newList = App.getAddedBooksList();
        newList.remove(App.getAddedBookIndex());
        App.setAddedBooksList(newList);
        colecao.updateOne(eq("username", App.getUser()), combine(set("books", gson.toJson(newList))));
        changePage("view_books");
    }
    public void changePage(String s) {
        try {
            App.setRoot(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void changeComponentsVisibility(boolean modalIsVisible)
    {
        modal.setVisible(modalIsVisible);
        bookTitle.setVisible(!modalIsVisible);
        authorsLabel.setVisible(!modalIsVisible);
        descriptionLabel.setVisible(!modalIsVisible);
        descriptionTitle.setVisible(!modalIsVisible);
        removeBookButton.setVisible(!modalIsVisible);
        addBookButton.setVisible(!modalIsVisible);
        removeBookButton.setVisible(!modalIsVisible);
        rateButton.setVisible(!modalIsVisible);
        backButton.setVisible(!modalIsVisible);
        coverImage.setVisible(!modalIsVisible);
        formal_doge.setVisible(!modalIsVisible);
        h_frame.setVisible(!modalIsVisible);
        h_frame2.setVisible(!modalIsVisible);
        wow.setVisible(!modalIsVisible);
        pagesLabel.setVisible(!modalIsVisible);
        bookPages.setVisible(!modalIsVisible);
    }
    @FXML
    public void openModal()
    {
        changeComponentsVisibility(true);
    }
    @FXML
    public void closeModal()
    {
        changeComponentsVisibility(false);
    }
    @FXML
    public void submitRating()
    {
        if(selectRatingComboBox.getValue() != null && !ratingCommentaryInput.getText().equals(""))
        {
            MongoCollection<Document> colecao2 = banco.getCollection("ratings");
            Rating novo = new Rating(App.getUser(), actualBook.getTitle(), ratingCommentaryInput.getText(), Double.parseDouble(selectRatingComboBox.getValue()));
            ArrayList<Rating> DESGRAÇA = App.getRatingsList();
            DESGRAÇA.add(novo);
            App.setRatingsList(DESGRAÇA);
            try {
                colecao2.updateOne(eq(new ObjectId("636e49dba38bc508948b2e41")), combine(set("ratings", gson.toJson(DESGRAÇA))));
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            ratingCommentaryInput.clear();
            closeModal();
        }
        else
            errorLabel.setVisible(true);
    }
    public void openRatingsPage()
    {
        try {
        App.setRoot("view-ratings");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
