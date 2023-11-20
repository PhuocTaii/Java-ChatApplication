/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btv.Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author taing
 */
public class Server {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            // Establish connection
            String url = "jdbc:mysql://chatchat-db.c0kjxptbsciv.ap-southeast-1.rds.amazonaws.com:3306/?user=admin";
            String username = "admin";
            String password = "asd12345";
            connection = DriverManager.getConnection(url, username, password);
            // Perform database operations here
            if(connection != null){
                System.out.println("connected");
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            // Close connection in the finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.err.println(e);
                }
            }
        }
    }
}