package com.alface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatisticsController extends BigController {
    @FXML Label bookNumberLabel;
    @FXML Label pageNumberLabel;
    @FXML Label favAuthorLabel;
    @FXML Label booksFromAuthorLabel;
    public void initialize()
    {
        display();
    }
    public void display()
    {
        Integer bookNumber = App.getAddedBooksList().size();
        bookNumberLabel.setText(bookNumber.toString());
        Integer pageNumber = somatorio(App.getAddedBooksList());
        pageNumberLabel.setText(pageNumber.toString());
        Author fav = autorFavorito(App.getAddedBooksList());
        favAuthorLabel.setText(fav.name);
        booksFromAuthorLabel.setText(fav.books.toString());
    }
    public int somatorio(ArrayList<Book> lista)
    {
        int soma = 0;
        for (Book book : lista) {
            soma += book.pageCount;
        }   
        return soma;
    }
    public Author autorFavorito(ArrayList<Book> lista)
    {
        ArrayList<Author> autores = new ArrayList<Author>();
        ArrayList<String> nomes = new ArrayList<String>();
        for (Book b : lista) {
            ArrayList<String> aut = b.getAuthors();
            if(aut != null)
            {
                for (int i = 0; i < b.getAuthors().size(); i++) {
                    int indice = nomes.indexOf(aut.get(i));
                    if(indice != -1)
                        autores.get(indice).books++;
                    else
                    {
                        nomes.add(aut.get(i));
                        autores.add(new Author(aut.get(i), 1));
                    }
                    
                }
            }
        }
        Collections.sort(autores, new Comparator<Author>() {
            @Override
            public int compare(Author b, Author a)
            {
                return a.books.compareTo(b.books);
            }
        });
        return autores.get(0);
    }
    @FXML
    public void backToHome()
    {
        try {
            App.setRoot("home_page");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
