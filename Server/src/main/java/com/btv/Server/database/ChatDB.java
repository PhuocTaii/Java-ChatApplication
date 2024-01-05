/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.database;

import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import io.github.cdimascio.dotenv.Dotenv;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author tvan
 */
public class ChatDB { // Singleton

    private static ChatDB dbInstance = null;
    
    protected static Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Database close connection!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    protected ChatDB() {
        connection = null;

        try {
            // register driver
            Driver myDriver = new Driver();
            DriverManager.registerDriver(myDriver);
            System.out.println("Connecting to database...");

            Dotenv dotenv = Dotenv.load();
            // Establish connection
            connection = DriverManager.getConnection(dotenv.get("URL_DB"), dotenv.get("USERNAME_DB"), dotenv.get("PASSWORD_DB"));
            System.out.println("Database connected");
            // Perform database operations here
            Statement stmt = connection.createStatement();
            String sql;
            sql = "USE chatchat_db";
            stmt.execute(sql);
            stmt.close();

        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static ChatDB getDBInstance() {
        if (dbInstance == null) {
            dbInstance = new ChatDB();
        }
        return dbInstance;
    }
    
}
