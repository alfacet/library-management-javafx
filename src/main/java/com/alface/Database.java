package com.alface;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.MongoClient;

public class Database {

    // public Connection database;

    // public Connection getConnection() {
    //     String databaseName = "bomba";
    //     String databaseUser = "root";
    //     String databasePassword = "NO";

    //     String url = "jdbc:mysql://localhost:3306/bomba";

    //     try {
    //         Class.forName("com.mysql.cj.jdbc.Driver");
    //         database = DriverManager.getConnection(url, databaseUser, databasePassword);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return database;

    // }
}
