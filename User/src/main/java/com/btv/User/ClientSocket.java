/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User;

import com.btv.User.gui.interfaces.CustomListener;
import com.btv.User.helper.MessageStatus;
import com.btv.User.helper.MessageType;
import com.btv.User.model.ChatMessage;
import com.btv.User.model.User;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tvan
 */
public class ClientSocket implements Runnable {
    private static ClientSocket clientInstance = null;
    
    private Socket socket;
    public BufferedReader dataIn;
    public BufferedWriter dataOut;
    
    private MessageType mess;
    
    private ClientSocket() {
        try {
            socket = new Socket("127.0.0.1", 6868);
            dataIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            dataOut = new BufferedWriter( new OutputStreamWriter(socket.getOutputStream()));
            
            // send role to server
            System.out.println("sending role...");
            dataOut.write("user");
            dataOut.newLine();
            dataOut.flush();
        } catch (IOException e) {
            System.err.println(e);
            closeClientSocket();
        }
    }
    
    public static ClientSocket getInstance() {
        if(clientInstance == null) {
            clientInstance = new ClientSocket();
        }
        return clientInstance;
    }
    
    @Override
    final public void run() {
        String messageFromClient;

        while(socket.isConnected()){
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
        JSONObject messObj = new JSONObject(messStr);
        MessageType messType = MessageType.valueOf(messObj.get("type").toString());
        
        switch (messType) {
            case VIEW_ALL_FRIENDS:
            {
                ArrayList<User> listFriend = new ArrayList<>();
                JSONArray friendArr = messObj.getJSONArray("data");
                for (int i = 0; i < friendArr.length(); i++) {
                    JSONObject friend = friendArr.getJSONObject(i);
                    listFriend.add(new User(friend.getInt("id"), friend.getString("username"), friend.getString("status").equalsIgnoreCase("ONLINE")));
                }
                CustomListener.getInstance().getChatListener().loadListFriend(listFriend);
            }
                break;
                
            case FRIEND_STATUS:
            {
                JSONObject updatedFriend = messObj.getJSONObject("data");
                int updatedFriendId = updatedFriend.getInt("id");
                boolean isOnline = updatedFriend.getBoolean("isOnline");
                CustomListener.getInstance().getChatListener().updateFriendStatus(updatedFriendId, isOnline);
            }
                break;
                
            case UNFRIEND:
            {
                JSONObject friendRel = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(friendRel.getString("status"));
                if(res == MessageStatus.SUCCESS) {
                    CustomListener.getInstance().getChatListener().unfriend(friendRel.getInt("id"));
                }
            }
                break;
                
            case FIND_USER:
            {
                ArrayList<User> listUser = new ArrayList<>();
                JSONArray friendArr = messObj.getJSONArray("data");
                for (int i = 0; i < friendArr.length(); i++) {
                    JSONObject friend = friendArr.getJSONObject(i);
                    listUser.add(new User(friend.getInt("id"), friend.getString("username"), friend.getString("name")));
                }
                CustomListener.getInstance().getSearchListener().showFoundUsers(listUser);
            }
                break;
                
            case ADD_FRIEND:
            {
                JSONObject objData = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(objData.getString("status"));
                res.setMessage(objData.getString("statusDetail"));
                CustomListener.getInstance().getSearchListener().addFriend(res);
            }
                break;
                
            case VIEW_CHAT_HISTORY:
            {
                ArrayList<ChatMessage> listChat = new ArrayList<>();
                JSONArray chatArr = messObj.getJSONArray("data");
                for (int i = 0; i < chatArr.length(); i++) {
                    JSONObject chatObj = chatArr.getJSONObject(i);
                    ChatMessage chat = new ChatMessage();
                    chat.setContent(chatObj.getString("content"));
                    chat.setIsMine(chatObj.getBoolean("mine"));
                    chat.setSendName(chatObj.getString("sendName"));
                    listChat.add(chat);
                }
                CustomListener.getInstance().getChatListener().loadChatData(listChat);
            }
                break;
                
            case REPORT_USER:
            {
                JSONObject reportRes = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(reportRes.getString("status"));
                if(res == MessageStatus.SUCCESS)
                    res.setMessage("Done report!");
                else
                    res.setMessage("Report failed!");
                CustomListener.getInstance().getChatListener().reportNoti(res);
            }
                break;
                
            case BLOCK_USER:
            {
                JSONObject blockRes = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(blockRes.getString("status"));
                res.setMessage(blockRes.getString("statusDetail"));
                int blockedId = blockRes.getInt("id");
                if(blockedId != -1) {
                    CustomListener.getInstance().getChatListener().unfriend(blockedId);
                }
                CustomListener.getInstance().getChatListener().blockNoti(res);
            }
                break;
                
            default:
                System.out.println("Invalid message");
        }
    }
    
    public void closeClientSocket() {
        try {
            if(socket != null) {
                socket.close();
            }
            if(dataIn != null)
                dataIn.close();
            if(dataOut != null)
                dataOut.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
