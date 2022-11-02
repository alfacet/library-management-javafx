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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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

    @FXML
    TextArea descriptionLabel;
    @FXML
    Label bookTitle;
    @FXML
    ImageView coverImage;
    @FXML
    Label bookPages;
    @FXML
    Label messageLabel;
    @FXML
    Button addBookButton;
    @FXML
    Label authorsLabel;
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
        boolean b = App.getWhatList() == App.SEARCHED_BOOKS_LIST ? true : false;
        addBookButton.setVisible(b);

        String oldThumb = actualBook.thumbnail;
        if (oldThumb != null)
            coverImage.setImage(new Image(tiraAspas(oldThumb)));
        else
            coverImage.setImage(ERROR_IMG);
        bookTitle.setText(actualBook.getTitle());

        Integer a = actualBook.getPageCount();
        bookPages.setText(a.toString());

        if (actualBook.getDescription() != null)
            descriptionLabel.setText(tiraAspas(actualBook.getDescription()));
        else
            descriptionLabel.setText("Description not avaliable! :C");

        if (actualBook.authors.isEmpty())
            authorsLabel.setText("Authors not avaliable! :C");
        else {
            String text = tiraAspas(actualBook.getAuthors().get(0));
            for (int i = 1; i < actualBook.getAuthors().size(); i++) {
                text += ", " + tiraAspas(actualBook.getAuthors().get(i));
            }
            authorsLabel.setText(text);
        }
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

        // Document doc = new Document();
        // doc.append("username", "jorge");
        //doc.append("password", "2365317");
        //doc.append("books", "123");
        try{
        Gson gson = new Gson();
        colecao.updateOne(eq("username", App.getUser()), combine(set("books", gson.toJson(livrosAdicionados))));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void backToHome() {
        try {
            App.setRoot("home_page");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
