/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.service;

import com.btv.User.ClientSocket;
import com.btv.User.helper.MessageType;
import com.btv.User.model.Member;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import org.json.JSONObject;

/**
 *
 * @author tvan
 */
public class ChatService {
    public static void getChatUserHistory(int userId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.VIEW_CHAT_HISTORY.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(userId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    public static void getChatGroupHistory(int groupId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.VIEW_GROUP_CHAT_HISTORY.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(groupId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void getEncryptedChatGroupHistory(int groupId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.VIEW_ENCRYPTED_GROUP_CHAT_HISTORY.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(groupId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void chatUser(int receiverId, String mess) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.CHAT_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject messObj = new JSONObject();
            messObj.put("id", receiverId);
            messObj.put("content", mess);
            clientSocket.dataOut.write(messObj.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void chatGroup(int groupId, String mess) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.CHAT_GROUP.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject messObj = new JSONObject();
            messObj.put("id", groupId);
            messObj.put("content", mess);
            clientSocket.dataOut.write(messObj.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
//    public static void getMembersPublicKeys(int groupId) {
//        ClientSocket clientSocket = ClientSocket.getInstance();
//        
//        try {
//            clientSocket.dataOut.write(MessageType.GET_PUBLIC_KEYS_MEMBERS.toString());
//            clientSocket.dataOut.newLine();
//            clientSocket.dataOut.flush();
//            
//            clientSocket.dataOut.write(groupId);
//            clientSocket.dataOut.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    
    public static void chatGroupEncrypted(int groupId, String mess, ArrayList<Member> members) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        for(Member mem : members) {
            for(Byte[] keyBytes : mem.getPublicKeys()) {
                try {
                    byte[] messEncrypted = SecurityService.encrypt(mess, SecurityService.readPublicKey(keyBytes));

                    clientSocket.dataOut.write(MessageType.CHAT_GROUP_ENCRYPTED.toString());
                    clientSocket.dataOut.newLine();
                    clientSocket.dataOut.flush();

                    JSONObject messObj = new JSONObject();
                    messObj.put("groupId", groupId);
                    messObj.put("memId", mem.getId());
                    messObj.put("content", Base64.getEncoder().encodeToString(messEncrypted));
                    clientSocket.dataOut.write(messObj.toString());
                    clientSocket.dataOut.newLine();
                    clientSocket.dataOut.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void clearChatUserHistory(int userId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.CLEAR_CHAT_HISTORY.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(userId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void searchMessages(String chatName, String query) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.FIND_MESSAGE.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject mess = new JSONObject();
            mess.put("name", chatName);
            mess.put("query", query);
            clientSocket.dataOut.write(mess.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}