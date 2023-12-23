/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.service;

import com.btv.User.ClientSocket;
import com.btv.User.helper.MessageStatus;
import com.btv.User.helper.MessageType;
import java.io.IOException;

/**
 *
 * @author tvan
 */
public class FriendService {  
    public void getListFriends() {
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
    
    public boolean unfriend(int friendId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view list friends
            clientSocket.dataOut.write(MessageType.UNFRIEND.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(friendId);
            clientSocket.dataOut.flush();
            
            MessageStatus res = MessageStatus.valueOf(clientSocket.dataIn.readLine());
            if(res == MessageStatus.SUCCESS) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
