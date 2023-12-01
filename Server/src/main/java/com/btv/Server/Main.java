/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btv.Server;
import com.btv.Server.socket.ClientHandler;
import com.btv.Server.database.ChatDB;
import com.btv.Server.socket.AdminHandler;
import com.btv.Server.socket.UserHandler;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author taing
 */
public class Main {
    private static ServerSocket serverSocket;
    
    public static void main(String[] args) {
        ChatDB db = ChatDB.getDBInstance();
        
        try {
            serverSocket = new ServerSocket(6868);
            while(true) {
                System.out.println("Waiting for client connection...");
                Socket client = serverSocket.accept();
                System.out.println("New client connected!");
                
                // determine role
                BufferedReader dataIn = new BufferedReader(new InputStreamReader(client.getInputStream()));
                // read role
                String role = dataIn.readLine();
                ClientHandler clientHandler;
                if(role.equalsIgnoreCase("admin")) {
                    System.out.println("Role: admin");
                    clientHandler = new AdminHandler(client);
                }
                else {
                    System.out.println("Role: user");
                    clientHandler = new UserHandler(client);
                }
                
                // create and start thread
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            db.closeConnection();
        }
    }
}