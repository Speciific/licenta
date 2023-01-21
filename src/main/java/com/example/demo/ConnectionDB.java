package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    String url = "jdbc:mysql://localhost:3305/mysql";
    String username = "root";
    String password = "numaistiuparola";
    //Connection connection = databaseConnection();

    public Connection databaseConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);  // conectare la baza de date
            return conn;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
