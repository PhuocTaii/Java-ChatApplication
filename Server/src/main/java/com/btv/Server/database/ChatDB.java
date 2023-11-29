/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.database;

import com.btv.Server.model.User;
import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public class ChatDB { // Singleton
    private static ChatDB dbInstance = null;
    
    private static final String URL = "jdbc:mysql://chatchat-db.c0kjxptbsciv.ap-southeast-1.rds.amazonaws.com:3306/?user=admin";
    private static final String USERNAME = System.getenv("USERNAME_DB");;
    private static final String PASSWORD = System.getenv("PASS_DB");
    private static Connection connection;
    
    public Connection getConnection() {
        return connection;
    }
    
    public void closeConnection() {
        try {
            if(connection != null) {
                connection.close();
                System.out.println("Database close connection!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    private ChatDB() {
        connection = null;

        try {
            // register driver
            Driver myDriver = new Driver();
            DriverManager.registerDriver(myDriver);
            System.out.println("Connecting to database...");
            
            // Establish connection
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connected");
            // Perform database operations here
            Statement stmt = connection.createStatement();
            String sql;
            sql = "USE chatchat";
            stmt.execute(sql);

            stmt.close();
            
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
    
    public static ChatDB getDBInstance() {
        if(dbInstance == null) {
            dbInstance = new ChatDB();
        }
        return dbInstance;
    }
    
}
