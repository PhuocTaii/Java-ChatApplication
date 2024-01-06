/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User;

import com.btv.User.gui.interfaces.CustomListener;
import com.btv.User.helper.MessageStatus;
import com.btv.User.helper.MessageType;
import static com.btv.User.helper.MessageType.BLOCK_USER;
import com.btv.User.model.ChatMessage;
import com.btv.User.model.Group;
import com.btv.User.model.Member;
import com.btv.User.model.User;
import com.btv.User.service.SecurityService;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    public Socket getSocket() {
        return this.socket;
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
            case VIEW_GROUP_CHAT_HISTORY:
            {
                ArrayList<ChatMessage> listChat = new ArrayList<>();
                JSONArray chatArr = messObj.getJSONArray("data");
                for (int i = 0; i < chatArr.length(); i++) {
                    JSONObject chatObj = chatArr.getJSONObject(i);
                    ChatMessage chat = new ChatMessage();
                    chat.setContent(chatObj.getString("content"));
                    boolean isMine = chatObj.getBoolean("mine");
                    chat.setIsMine(isMine);
                    if(!isMine)
                        chat.setSendName(chatObj.getString("sendName"));
                    else
                        chat.setSendName("You");
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
                
            case CHAT_USER:
            {
                JSONObject chatRes = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(chatRes.getString("status"));
                res.setMessage(chatRes.getString("statusDetail"));
                
                CustomListener.getInstance().getChatListener().messNoti(res);
            }
                break;
                
            case NEW_MESSAGE_USER:
            {
                JSONObject chatObj = messObj.getJSONObject("data");
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setSendName(chatObj.getString("sender"));
                chatMessage.setContent(chatObj.getString("content"));
                chatMessage.setIsMine(false);
                
                CustomListener.getInstance().getChatListener().newMessUserCome(chatMessage, chatObj.getInt("senderId"));
            }
                break;
                
            case CLEAR_CHAT_HISTORY:
            {
                JSONObject resObj = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(resObj.getString("status"));
                res.setMessage(resObj.getString("statusDetail"));
                CustomListener.getInstance().getChatListener().clearChatHistory(res);
            }
                break;
                
            case FIND_MESSAGE:
            {
                ArrayList<ChatMessage> listMess = new ArrayList<>();
                JSONArray messArr = messObj.getJSONArray("data");
                for (int i = 0; i < messArr.length(); i++) {
                    JSONObject chatObj = messArr.getJSONObject(i);
                    ChatMessage chatMessage = new ChatMessage();
                    chatMessage.setChatName(chatObj.getString("chatName"));
                    chatMessage.setContent(chatObj.getString("content"));
                    chatMessage.setSendName(chatObj.getString("sendName"));
                    listMess.add(chatMessage);
                }
                CustomListener.getInstance().getSearchListener().showFoundMess(listMess);
            }
                break;
                
            case VIEW_ALL_GROUPS:
            {
                ArrayList<Group> listGroup = new ArrayList<>();
                JSONArray grArr = messObj.getJSONArray("data");
                for (int i = 0; i < grArr.length(); i++) {
                    JSONObject grObj = grArr.getJSONObject(i);
                    Group gr = new Group();
                    gr.setId(grObj.getInt("id"));
                    gr.setName(grObj.getString("name"));
                    gr.setIsEncrypted(grObj.getBoolean("isEncrypted"));
                    listGroup.add(gr);
                    if(grObj.getBoolean("isEncrypted")) {
                        Group gr2 = new Group(gr);
                        listGroup.add(gr2);
                    }
                }
                CustomListener.getInstance().getChatListener().loadListGroup(listGroup);
            }
                break;
                
            case VIEW_MEMBERS:
            {
                ArrayList<Member> listMem = new ArrayList<>();
                JSONObject memRes = messObj.getJSONObject("data");
                JSONArray memArr = memRes.getJSONArray("list");
                for (int i = 0; i < memArr.length(); i++) {
                    JSONObject memObj = memArr.getJSONObject(i);
                    Member mem = new Member();
                    mem.setId(memObj.getInt("id"));
                    mem.setUsername(memObj.getString("username"));
                    mem.setIsAdmin(memObj.getBoolean("isAdmin"));
                    if(memRes.getBoolean("isEncrypted")) {
                        JSONArray keyArr = memObj.getJSONArray("publicKeys");
                        for(int j = 0; j < keyArr.length(); j++) {
                            mem.addPublicKey(Base64.getDecoder().decode(keyArr.getString(j)));
                        }
                    }
                    listMem.add(mem);
                }
                CustomListener.getInstance().getChatListener().loadListMember(listMem, memRes.getBoolean("isAdmin"));
            }
                break;
                
            case RENAME_GROUP:
            {
                JSONObject memRes = messObj.getJSONObject("data");
                String newName = memRes.getString("newName");
                int groupId = memRes.getInt("groupId");

                CustomListener.getInstance().getChatListener().updateGroupName(groupId, newName);
            }
                break;
                
            case ADD_MEMBER:
            {
                ArrayList<Member> listMem = new ArrayList<>();
                JSONObject memRes = messObj.getJSONObject("data");
                JSONArray memArr = memRes.getJSONArray("list");
                for (int i = 0; i < memArr.length(); i++) {
                    JSONObject memObj = memArr.getJSONObject(i);
                    Member mem = new Member();
                    mem.setId(memObj.getInt("id"));
                    mem.setUsername(memObj.getString("username"));
                    mem.setIsAdmin(memObj.getBoolean("isAdmin"));
                    listMem.add(mem);
                };
                
                CustomListener.getInstance().getChatListener().addGroupMember(listMem, memRes.getBoolean("isAdmin"));
            }
                break;
            case ASSIGN_ADMIN_TO_MEMBER:
            {
                ArrayList<Member> listMem = new ArrayList<>();
                JSONObject memRes = messObj.getJSONObject("data");
                JSONArray memArr = memRes.getJSONArray("list");
                for (int i = 0; i < memArr.length(); i++) {
                    JSONObject memObj = memArr.getJSONObject(i);
                    Member mem = new Member();
                    mem.setId(memObj.getInt("id"));
                    mem.setUsername(memObj.getString("username"));
                    mem.setIsAdmin(memObj.getBoolean("isAdmin"));
                    listMem.add(mem);
                };
                
                CustomListener.getInstance().getChatListener().assignAdmin(listMem, memRes.getBoolean("isAdmin"));
            }
                break;
                
            case DELETE_MEMBER:
            {
                ArrayList<Member> listMem = new ArrayList<>();
                JSONObject memRes = messObj.getJSONObject("data");
                JSONArray memArr = memRes.getJSONArray("list");
                for (int i = 0; i < memArr.length(); i++) {
                    JSONObject memObj = memArr.getJSONObject(i);
                    Member mem = new Member();
                    mem.setId(memObj.getInt("id"));
                    mem.setUsername(memObj.getString("username"));
                    mem.setIsAdmin(memObj.getBoolean("isAdmin"));
                    listMem.add(mem);
                };
                
                CustomListener.getInstance().getChatListener().removeMember(listMem, memRes.getBoolean("isAdmin"));
            }
                break;
                
            case FIND_USER_BY_USERNAME:
            {
                Member mem = new Member();
                JSONObject resObj = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(resObj.getString("status"));
                res.setMessage(resObj.getString("statusDetail"));
                if(res == MessageStatus.SUCCESS) {
                    JSONObject foundUser = resObj.getJSONObject("foundUser");
                    mem.setIsAdmin(false);
                    mem.setId(foundUser.getInt("id"));
                    mem.setUsername(foundUser.getString("username"));
                }
                CustomListener.getInstance().getCreateGroupListener().addFoundMember(res, mem);
            }
                break;
                
            case CREATE_GROUP:
            {
                JSONObject resObj = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(resObj.getString("status"));
                res.setMessage(resObj.getString("statusDetail"));
                Group gr = new Group();
                if(res == MessageStatus.SUCCESS) {
                    gr.setId(resObj.getInt("id"));
                    gr.setName(resObj.getString("name"));
                    CustomListener.getInstance().getChatListener().addNewGroupChat(gr);
                }
                CustomListener.getInstance().getCreateGroupListener().createGroup(res);
            }
                break;
                
            case NEW_MESSAGE_GROUP:
            {
                JSONObject chatObj = messObj.getJSONObject("data");
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setSendName(chatObj.getString("sender"));
                chatMessage.setContent(chatObj.getString("content"));
                chatMessage.setIsMine(false);
                
                CustomListener.getInstance().getChatListener().newMessGroupCome(chatMessage, chatObj.getInt("groupId"), false);
            }
                break;
                
            case ENCRYPT_GROUP:
            {
                JSONObject resObj = messObj.getJSONObject("data");
                MessageStatus res = MessageStatus.valueOf(resObj.getString("status"));
                res.setMessage(resObj.getString("statusDetail"));
                
                CustomListener.getInstance().getChatListener().encryptGroupChat(res, resObj.getInt("groupId"));
            }
                break;
                
            case NEW_ENCRYPTED_MESSAGE_GROUP:
            {
                JSONObject chatObj = messObj.getJSONObject("data");
                byte[] messBytes = Base64.getDecoder().decode(chatObj.getString("content"));
                
                String content;
                try {
                    content = SecurityService.decrypt(messBytes, MainApp.getPrivateKey());
                } catch (Exception ex) {
                    break;
                }
                
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setSendName(chatObj.getString("sender"));
                chatMessage.setContent(content);
                chatMessage.setIsMine(false);
                
                CustomListener.getInstance().getChatListener().newMessGroupCome(chatMessage, chatObj.getInt("groupId"), true);
            }
                break;
                
            case VIEW_ENCRYPTED_GROUP_CHAT_HISTORY:
            {
                ArrayList<ChatMessage> listChat = new ArrayList<>();
                JSONArray chatArr = messObj.getJSONArray("data");
                for (int i = 0; i < chatArr.length(); i++) {
                    JSONObject chatObj = chatArr.getJSONObject(i);
                    ChatMessage chat = new ChatMessage();
                    String content;
                    try {
                        content = SecurityService.decrypt(Base64.getDecoder().decode(chatObj.getString("content")), MainApp.getPrivateKey());
                        chat.setContent(content);
                        boolean isMine = chatObj.getBoolean("mine");
                        chat.setIsMine(isMine);
                        if(!isMine)
                            chat.setSendName(chatObj.getString("sendName"));
                        else
                            chat.setSendName("You");
                        listChat.add(chat);                    
                    } catch (Exception ex) {
                    }
                }
                CustomListener.getInstance().getChatListener().loadChatData(listChat);
            }
                break;
                
            case NEW_USER_DEVICE_ENCRYPT:
            {
                JSONObject data = messObj.getJSONObject("data");
                byte[] keyBytes = Base64.getDecoder().decode(data.getString("key"));
                int newId = data.getInt("newUser");
                
                JSONArray grArr = data.getJSONArray("groups");
                for (int i = 0; i < grArr.length(); i++) {
                    if(CustomListener.getInstance().getChatListener().addNewKeyOfMember(grArr.getInt(i), newId, keyBytes))
                        break;
                }
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
