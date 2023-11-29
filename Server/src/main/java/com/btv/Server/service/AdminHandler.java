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
                System.out.println(messageFromClient);
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
                break;
            default:
                System.out.println("Invalid message");
        }
    }
}
