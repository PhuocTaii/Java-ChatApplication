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
public class GroupService {
    public static void getListGroups() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.VIEW_ALL_GROUPS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void getMembers(int groupId) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            clientSocket.dataOut.write(MessageType.VIEW_MEMBERS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            clientSocket.dataOut.write(groupId);
            clientSocket.dataOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void renameChatGroup(int groupId, String newName){
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try{
            clientSocket.dataOut.write(MessageType.RENAME_GROUP.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject mess = new JSONObject();
            mess.put("groupId", groupId);
            mess.put("newName", newName);
            
            clientSocket.dataOut.write(mess.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void addMember(int groupId, String username){
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try{
            clientSocket.dataOut.write(MessageType.ADD_MEMBER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject mess = new JSONObject();
            mess.put("groupId", groupId);
            mess.put("username", username);
            
            clientSocket.dataOut.write(mess.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void setAdmin(int groupId, int u_id, Boolean admin){
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try{
            clientSocket.dataOut.write(MessageType.ASSIGN_ADMIN_TO_MEMBER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject mess = new JSONObject();
            mess.put("groupId", groupId);
            mess.put("u_id", u_id);
            mess.put("is_admin", admin);
            
            clientSocket.dataOut.write(mess.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void removeMember(int groupId, int userId){
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try{
            clientSocket.dataOut.write(MessageType.DELETE_MEMBER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            JSONObject mess = new JSONObject();
            mess.put("groupId", groupId);
            mess.put("u_id", userId);
            
            clientSocket.dataOut.write(mess.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
