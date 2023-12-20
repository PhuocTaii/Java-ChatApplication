/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.database;

import com.btv.Server.model.Friends;
import com.btv.Server.model.Login;
import com.btv.Server.model.OnlineUser;
import com.btv.Server.model.User;
import java.sql.Connection;
import com.mysql.cj.jdbc.Driver;
import java.sql.Array;
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
    
    public ArrayList<OnlineUser> GetAllOnlineUsers(String[] split){
        String query = """
                        SELECT
                           U.u_id,
                           U.u_name,
                           U.username,
                           COUNT(DISTINCT L.id) AS app_open_count,
                           COUNT(DISTINCT C.receive_id) AS personal_chat_count,
                           COUNT(DISTINCT CH.group_id) AS group_chat_count
                        FROM
                           User U
                        LEFT JOIN
                           Logins L ON U.u_id = L.u_id AND L.login_time BETWEEN ? AND ?
                        LEFT JOIN
                           ChatHistory C ON U.u_id = C.receive_id AND C.sendtime BETWEEN ? AND ?
                        LEFT JOIN
                           ChatHistory CH ON U.u_id = CH.send_id AND CH.sendtime BETWEEN ? AND ?
                        GROUP BY
                           U.u_id, U.u_name, U.username;
                       """;
        
        ArrayList<OnlineUser> resList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, split[0]);
            preparedStatement.setString(3, split[0]);
            preparedStatement.setString(5, split[0]);

            preparedStatement.setString(2, split[1]);
            preparedStatement.setString(4, split[1]);
            preparedStatement.setString(6, split[1]);
            
            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    OnlineUser tempOnlUser = new OnlineUser();
                    tempOnlUser.setId(rs.getInt("u_id"));
                    tempOnlUser.setName(rs.getString("u_name"));
                    tempOnlUser.setUsername(rs.getString("username"));
                    tempOnlUser.setloginTimes(rs.getInt("app_open_count"));
                    tempOnlUser.setuserChatWith(rs.getInt("personal_chat_count"));
                    tempOnlUser.setgroupChatWith(rs.getInt("group_chat_count"));
                    resList.add(tempOnlUser);
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return resList;
    }
    
    public ArrayList<Login> GetAllLogins(){
        ArrayList<Login> resList = new ArrayList<>();
        try{
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Logins;";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){   
                Login tempLogin = new Login();
                tempLogin.setLoginDate(rs.getDate("login_time"));
                tempLogin.setId(rs.getInt("u_id"));

                resList.add(tempLogin);
            }
            stmt.close();
        } catch(SQLException e){
            System.err.println(e);
            return null;
        }
        return resList;
    }
}