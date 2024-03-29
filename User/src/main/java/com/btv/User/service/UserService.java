/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.service;

import com.btv.User.ClientSocket;
import com.btv.User.helper.MessageType;
import java.io.IOException;
import org.json.JSONObject;

/**
 *
 * @author tvan
 */
public class UserService {
    public static void getListFriends() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            // send request to view list friends
            clientSocket.dataOut.write(MessageType.VIEW_ALL_FRIENDS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void unfriend(int friendId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.UNFRIEND.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(friendId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void searchUsers(String option, String query) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.FIND_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject mess = new JSONObject();
            mess.put("option", option);
            mess.put("query", query);
            clientSocket.dataOut.write(mess.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addFriend(int friendId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.ADD_FRIEND.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(friendId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void reportUser(int userId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.REPORT_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(userId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void blockUser(int userId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.BLOCK_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(userId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void findUserByUsername(String username) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.FIND_USER_BY_USERNAME.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(username);
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
