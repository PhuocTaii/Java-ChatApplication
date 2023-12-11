/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.socket;

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
    public static ArrayList<AdminHandler> adminHandlers = new ArrayList<>();

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
        adminHandlers.add(this);
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
            case VIEW_NEW_USERS:
                ArrayList<User> allNewUsers = db.GetAllNewUsers();
                try {
                    // send number of users
                    dataOut.write(allNewUsers.size());
                    
                    // send data of all users
                    for(User user : allNewUsers) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getUsername() + "|");
                        dataOut.write(user.getName() + "|");
                        dataOut.write(user.getTimeCreate()+ "|");
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
    
    protected void removeClientFromList() {
        adminHandlers.remove(this);
    }
}
