/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.service;

import com.btv.Admin.ClientSocket;
import com.btv.helper.MessageType;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public class UserService {
    private ClientSocket clientSocket;
    
    public String[][] getAllUsers() {
        clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_USERS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            // read number of users
            int numUsers = clientSocket.dataIn.read();
            
            ArrayList<String[]> users = new ArrayList<>();
            for(int i = 0; i < numUsers; i++) {
                String userData = clientSocket.dataIn.readLine();
                users.add(userData.split("\\|"));
            }
            String[][] usersArray = new String[users.size()][];
            return users.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }
}
