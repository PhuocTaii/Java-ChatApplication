/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.database;

import com.btv.Server.model.User;
import com.btv.Server.service.MailService;
import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Random;

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
            sql = "USE chatchat_db";
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
    
    public ArrayList<User> getAllUsers() {
        ArrayList<User> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM User";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User tempUser = new User();
                tempUser.setId(rs.getInt("u_id"));
                tempUser.setUsername(rs.getString("username"));
                tempUser.setName(rs.getString("u_name"));
                tempUser.setAddress(rs.getString("address"));
                tempUser.setBirthday(rs.getDate("birthday"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setGender(rs.getBoolean("gender"));
                tempUser.setStatus(rs.getString("u_status"));
                tempUser.setPassword(rs.getString("u_password"));
                resList.add(tempUser);
            }

            stmt.close();
        
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return resList;
    }
    
    public int checkIfExistsUsername(String username) {
        try {
            String sql = "SELECT * FROM User WHERE username = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                stmt.close();
                return 1; // exists
            }
            stmt.close();
            return 0; // not exist
            
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public int signUp(User user) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO User(address, birthday, email, gender, u_name, u_password, u_status, username, time_create) VALUES (?, ?, ?, ?, ?, ?, 'ONLINE', ?,  ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, user.getAddress());
            stmt.setDate(2, user.getBirthday());
            stmt.setString(3, user.getEmail());
            stmt.setBoolean(4, user.getGender());
            stmt.setString(5, user.getName());
            stmt.setString(6, user.getPassword());
            stmt.setString(7, user.getUsername());
            stmt.setDate(8, new Date(new java.util.Date().getTime()));
            stmt.executeUpdate();
            
            sql = "SELECT LAST_INSERT_ID()";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery(sql);
            int uid;
            if(rs.next())
                uid = rs.getInt(1);
            else {
                stmt.close();
                return -1;
            }
            
            // update login history
            if(updateLoginTime(uid) != 1) {
                stmt.close();
                return -1;
            }
            
            connection.commit();
            connection.setAutoCommit(true);
            stmt.close();
            return uid;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
            return -1;
        }
    }
}
