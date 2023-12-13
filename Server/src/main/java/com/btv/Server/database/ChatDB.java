/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.database;

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

            String sql = "SELECT * FROM User";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
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

    public void addUser(String[] split) throws SQLException {
        String query = """
                       INSERT INTO User (username, u_name, address, birthday, email, gender, time_create, u_status, u_password)
                       VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);""";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        //username
        preparedStatement.setString(1, split[0]);
        //name
        preparedStatement.setString(2, split[1]);
        //address
        preparedStatement.setString(3, split[2]);
        //birthday
        preparedStatement.setString(4, split[3]);
        //email,
        preparedStatement.setString(5, split[4]);
        //gender
        preparedStatement.setString(6, split[5]);
        //time_create
        preparedStatement.setString(7, split[6]);
        //u_status
        preparedStatement.setString(8, split[7]);
        //u_password
        preparedStatement.setString(9, split[8]);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void modifyUser(String[] split) throws SQLException {
        String query = "UPDATE User SET username = ?, u_name = ?, address = ?, birthday = ?, email = ?, gender = ?, u_status = ?, u_password = ? WHERE u_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        //username
        preparedStatement.setString(1, split[0]);
        //name
        preparedStatement.setString(2, split[1]);
        //address
        preparedStatement.setString(3, split[2]);
        //birthday
        preparedStatement.setString(4, split[3]);
        //email,
        preparedStatement.setString(5, split[4]);
        //gender
        preparedStatement.setString(6, split[5]);
        //u_status
        preparedStatement.setString(7, split[7]);
        //u_password
        preparedStatement.setString(8, split[8]);
        //u_id
        preparedStatement.setString(9, split[9]);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(String u_id) throws SQLException {
        String query = "DELETE FROM User WHERE u_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //u_id
        preparedStatement.setString(1, u_id);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<Date> getLoginTime(String userId) throws SQLException {
        String query = "SELECT login_time FROM Logins WHERE u_id = ?";
        ArrayList<Date> loginTimeList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Date date = rs.getDate("login_time");
                    loginTimeList.add(date);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or throw it to the calling method
            System.err.println(e);
            throw e; // or handle it according to your application's logic
        }

        return loginTimeList;
    }

    public ArrayList<String> getFriendName(String userId) throws SQLException {
        String query = """
                       SELECT u2.u_name AS friend_name
                       FROM Friends f
                       INNER JOIN User u1 ON f.u_id1 = u1.u_id
                       INNER JOIN User u2 ON f.u_id2 = u2.u_id
                       WHERE u1.u_id = ?
                       """;
        ArrayList<String> nameList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("friend_name");
                    nameList.add(name);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or throw it to the calling method
            System.err.println(e);
            throw e; // or handle it according to your application's logic
        }

        return nameList;
    }

}
