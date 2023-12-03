/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.socket;

import com.btv.Server.database.ChatDB;
import com.btv.Server.helpers.UserMessage;
import com.btv.Server.model.User;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public class UserHandler extends ClientHandler{
    public static ArrayList<UserHandler> userHandlers = new ArrayList<>();
    
    private String username;
    public UserHandler(Socket clientSocket) {
        super(clientSocket);
        userHandlers.add(this);
    }

    public void handleMessage(String messStr) {
        UserMessage mess =  UserMessage.valueOf(messStr);
        ChatDB db = ChatDB.getDBInstance();
        switch (mess) {
            case REGISTER:
                
                break;
            default:
                System.out.println("Invalid message");
        }
    }
    
    protected void removeClientFromList() {
        userHandlers.remove(this);
    }
}
