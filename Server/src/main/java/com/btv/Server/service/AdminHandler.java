/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.service;

import com.btv.Server.database.ChatDB;
import com.btv.Server.helpers.AdminMessage;
import com.btv.Server.model.User;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public class AdminHandler extends ClientHandler{

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
    }

    @Override
    public void run() {
        String messageFromClient;

        while(clientSocket.isConnected()){
            try{
                messageFromClient = dataIn.readLine();
                handleMessage(messageFromClient);
            } catch(IOException e){
                System.err.println(e);
                break;
            }
        }

        closeClientSocket();
    }

    public void handleMessage(String messStr) {
        AdminMessage mess =  AdminMessage.valueOf(messStr);
        ChatDB db = ChatDB.getDBInstance();
        switch (mess) {
            case VIEW_USERS:
                ArrayList<User> allUsers = db.getAllUsers();
                try {
                    // send number of users
                    dataOut.write(allUsers.size());
                    
                    // send data of all users
                    for(User user : allUsers) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getUsername() + "|");
                        dataOut.write(user.getName() + "|");
                        dataOut.write(user.getAddress() + "|");
                        dataOut.write(user.getBirthday().toString() + "|");
                        dataOut.write(user.getGender() + "|");
                        dataOut.write(user.getEmail() + "|");
                        dataOut.write(user.getTimeCreate()+ "|");
                        dataOut.write(user.getStatus() + "|");
                        dataOut.write(user.getPassword() + "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Invalid message");
        }
    }
}
