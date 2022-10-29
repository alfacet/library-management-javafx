package com.alface;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.books.model.Review;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import io.github.cdimascio.dotenv.Dotenv;

public class AddBooksController extends BigController {

    Dotenv dotenv = Dotenv.configure()
        .directory("./.env")
        .ignoreIfMalformed() //
        .ignoreIfMissing()
        .load();

    public void initialize() {
        JsonReader reader;
        Gson gson = new Gson();
        String line;
        StringBuilder responseContent = new StringBuilder();
        try {
            URL link = new URL(dotenv.get("GOOGLE_API"));
            HttpURLConnection conexao = (HttpURLConnection) link.openConnection();

            conexao.setRequestMethod("GET");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("deu bom.");
            reader = new JsonReader(new InputStreamReader(conexao.getInputStream()));
            JsonElement dividido = JsonParser.parseReader(reader);
            JsonObject obj = dividido.getAsJsonObject();
            JsonArray vetor = obj.get("items").getAsJsonArray();
            ArrayList<Book> lista = new ArrayList<Book>();
            for (int i = 0; i < vetor.size(); i++) {
                JsonObject temp = vetor.get(i).getAsJsonObject();
                JsonObject info = temp.get("volumeInfo").getAsJsonObject();
                 Book livro = new Book(info.get("title").toString(), 23);

                System.out.println(livro.title);
            }
            
        
        } catch (Exception e) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(e);
        }
    }
}