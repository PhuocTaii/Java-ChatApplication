/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.database;

import com.btv.Server.model.Friends;
import com.btv.Server.model.User;
import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    private static final String USERNAME = System.getenv("USERNAME_DB");
    private static final String PASSWORD = System.getenv("PASS_DB");
//    private static final String USERNAME = "admin";
//    private static final String PASSWORD = "asd12345";
    public  AdminHandleDB adminHandleDB = null;
    
    private static Connection connection;

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
            adminHandleDB = new AdminHandleDB(getConnection());
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

    public ArrayList<User> getAllUsers() {
        ArrayList<User> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM User order by id";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                User tempUser = new User();
                tempUser.setId(rs.getInt("u_id"));
                tempUser.setUsername(rs.getString("username"));
                tempUser.setName(rs.getString("u_name"));
                tempUser.setAddress(rs.getString("address"));
                tempUser.setBirthday(rs.getDate("birthday"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setTimeCreate(rs.getDate("time_create"));
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
    
    public ArrayList<User> GetAllNewUsers(){
        ArrayList<User> resList = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            
            String sql = "select u_id, username, u_name, time_create from User order by time_create desc";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                User tempUser = new User();
                tempUser.setId(rs.getInt("u_id"));
                tempUser.setUsername(rs.getString("username"));
                tempUser.setName(rs.getString("u_name"));
                tempUser.setTimeCreate(rs.getDate("time_create"));
                
                resList.add(tempUser);
            }
            
            stmt.close();
            
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
        return resList;
    }
    
    public ArrayList<Friends> GetAllFriends(){
        ArrayList<Friends> resList = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();

            String sql = "select u_id, u_name, time_create, count(distinct(f.u_id2)) as 'direct_friends', count(distinct(f2.u_id2)) as 'indirect_friends'\n" +
                        "from User u \n" +
                        "left join Friends f on u.u_id = f.u_id1 \n" +
                        "left join Friends f2 on u.u_id = f2.u_id1 OR u.u_id = f2.u_id2\n" +
                        "group by u.u_id, u.u_name\n" +
                        "order by u.u_id";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){   
                Friends tempFriends = new Friends();
                tempFriends.setId(rs.getInt("u_id"));
                tempFriends.setName(rs.getString("u_name"));
                tempFriends.setTimeCreate(rs.getDate("time_create"));
                tempFriends.setDirectFriends(rs.getInt("direct_friends"));
                tempFriends.setIndirectFriends(rs.getInt("indirect_friends"));
                
                resList.add(tempFriends);
            }
            
            stmt.close();
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
        return resList;
    }
}