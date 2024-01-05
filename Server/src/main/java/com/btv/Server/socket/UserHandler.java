/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.socket;

import com.btv.Server.database.UserHandleDB;
import com.btv.Server.helpers.MessageStatus;
import com.btv.Server.helpers.UserMessage;
import static com.btv.Server.helpers.UserMessage.DELETE_MEMBER;
import com.btv.Server.model.ChatMessage;
import com.btv.Server.model.GroupChat;
import com.btv.Server.model.GroupMember;
import com.btv.Server.model.User;
import com.btv.Server.service.MailService;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Member;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tvan
 */
public class UserHandler extends ClientHandler{
    public static ArrayList<UserHandler> userHandlers = new ArrayList<>();
    
    private int userId;
    private String clientUsername;
    private Socket clientSocket;
    public UserHandler(Socket clientSocket) {
        super(clientSocket);
        this.clientSocket = clientSocket;
    }

    public void handleMessage(String messStr) {
        UserMessage mess =  UserMessage.valueOf(messStr);
        UserHandleDB db = UserHandleDB.getDBInstance();
        boolean isSuccess;
        String messFail;
        
        JSONObject messRes = new JSONObject();
        messRes.put("type", messStr);
        
        switch (mess) {
            case REGISTER:
                isSuccess = true;
                messFail = "System error";
                try {
                    User newUser = new User();
                    String userData = dataIn.readLine();
                    String[] userFields = userData.split("\\|");
                    
                    try {
                        newUser.setUsername(userFields[0]);
                        newUser.setEmail(userFields[1]);
                        newUser.setPassword(userFields[2]);
                        newUser.setName(userFields[3]);
                        newUser.setAddress(userFields[4]);
                        newUser.setBirthday(!userFields[5].equals("") ? Date.valueOf(userFields[5]) : null);
                        newUser.setGender(Boolean.valueOf(userFields[6]));
                    } catch(IllegalArgumentException e) {
                        e.printStackTrace();
                        isSuccess = false;
                    }
                    
                    int uid = -1;
                    
                    if(isSuccess) {
                        // CHECK VALID MAIL
                        MailService mailService = MailService.getMailInstance();
                        if(!mailService.isValidEmail(newUser.getEmail())) {
                            isSuccess = false;
                            messFail = "Invalid email";
                        }
                        else if(db.checkIfExistsUsername(newUser.getUsername()) == 1) {
                            isSuccess = false;
                            messFail = "Username already exists";
                        }
                        else {
                            uid = db.signUp(newUser);
                            if(uid <= 0) {
                                isSuccess = false;
                            }
                            else {
                                loginSuccess(uid, newUser.getUsername());
                            }
                        }
                    }
                    
                    // SEND STATUS
                    if(isSuccess) {
                        dataOut.write(MessageStatus.SUCCESS.toString());
                        dataOut.newLine();
                        dataOut.write(uid);
                    }
                    else {
                        dataOut.write(MessageStatus.FAIL.toString());
                        dataOut.newLine();
                        dataOut.write(messFail);
                        dataOut.newLine();
                    }
                    dataOut.flush();
                }  catch (IOException e) {
                    e.printStackTrace();
                }
                break;
                
            case LOGIN:
                messFail = "System error";
                try {
                    String username = dataIn.readLine();
                    String password = dataIn.readLine();
                    
                    if(db.checkIfExistsUsername(username) == 0) {
                        dataOut.write(MessageStatus.FAIL.toString());
                        dataOut.newLine();
                        dataOut.write("Username not exist");
                        dataOut.newLine();
                        dataOut.flush();
                        break;
                    }
                    int uid = db.login(username, password);
                    if(uid > 0) {
                        loginSuccess(uid, username);
                        
                        dataOut.write(MessageStatus.SUCCESS.toString());
                        dataOut.newLine();
                        dataOut.write(uid);
                        dataOut.flush();
                        break;
                    }
                    else if(uid == 0) {
                        messFail = "Incorrect password";
                    }
                    else if(uid == -2) {
                        messFail = "Your account is locked";
                    }
                    dataOut.write(MessageStatus.FAIL.toString());
                    dataOut.newLine();
                    dataOut.write(messFail);
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                   e.printStackTrace();
                }
                break;
                
            case FORGOT_PASSWORD:
                try {
                    String username = dataIn.readLine();
                    
                    if(db.checkIfExistsUsername(username) == 0) {
                        dataOut.write(MessageStatus.FAIL.toString());
                        dataOut.newLine();
                        dataOut.write("Cannot find your username");
                        dataOut.newLine();
                        dataOut.flush();
                        break;
                    }
                    
                    String foundEmail = db.findEmailByUsername(username);
                    if(foundEmail != null && db.forgotPassword(username, foundEmail) == 1) {
                        dataOut.write(MessageStatus.SUCCESS.toString());
                        dataOut.newLine();
                        dataOut.write(foundEmail);
                        dataOut.newLine();
                    }
                    else {
                        dataOut.write(MessageStatus.FAIL.toString());
                        dataOut.newLine();
                        dataOut.write("System error");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                   e.printStackTrace();
                }
                
                break;
                
            case VIEW_ALL_FRIENDS:
                ArrayList<User> listFriends = db.getAllFriendsOfUser(this.userId);
                JSONArray friendArr = new JSONArray();
                
                try {
                    if(listFriends == null) {
                        messRes.put("data", friendArr);
                    }
                    else {
                        for(User user : listFriends) {
                            JSONObject friendObj = new JSONObject(user);
                            friendArr.put(friendObj);
                        }
                        messRes.put("data", friendArr);
                    }
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                break;
                
            case UNFRIEND:
                try {
                    int friendId = dataIn.read();
                    
                    JSONObject friendRel = new JSONObject();
                    friendRel.put("id", friendId);
                    if(db.unfriend(this.userId, friendId) == 1) {
                        friendRel.put("status", MessageStatus.SUCCESS.toString());
                    }
                    else {
                        friendRel.put("status", MessageStatus.FAIL.toString());
                    }
                    
                    messRes.put("data", friendRel);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                break;
                
            case FIND_USER:
                try {
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    String option = messReceived.getString("option");
                    String query = messReceived.getString("query");
                    ArrayList<User> listUsers = db.searchUsers(this.userId, option, query);
                    JSONArray userdArr = new JSONArray();
                    
                    if(listUsers == null) {
                        messRes.put("data", userdArr);
                    }
                    else {
                        for(User user : listUsers) {
                            JSONObject userdObj = new JSONObject(user);
                            userdArr.put(userdObj);
                        }
                        messRes.put("data", userdArr);
                    }
                    
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                break;
                
            case ADD_FRIEND:
                try {
                    int friendId = dataIn.read();
                    
                    JSONObject objData = new JSONObject();
                    objData.put("id", friendId);
                    
                    if(db.checkIfIsFriend(this.userId, friendId)) {
                        objData.put("status", MessageStatus.FAIL.toString());
                        objData.put("statusDetail", "Friend already added!");
                    }
                    else if(db.checkIfBlocked(this.userId, friendId) || db.checkIfBlocked(friendId, this.userId)) {
                        objData.put("status", MessageStatus.FAIL.toString());
                        objData.put("statusDetail", "Cannot add friend!");
                    }
                    else {
                        if(db.addFriend(this.userId, friendId)) {
                            objData.put("status", MessageStatus.SUCCESS.toString());
                            objData.put("statusDetail", "Add friend successfully!");
                        }
                        else {
                            objData.put("status", MessageStatus.FAIL.toString());
                            objData.put("statusDetail", "Cannot add friend!");
                        }
                    }
                    
                    messRes.put("data", objData);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                break;
                
            case VIEW_CHAT_HISTORY:
            {
                try {
                    int receiverId = dataIn.read();
                    
                    ArrayList<ChatMessage> listChat = db.getChatUserHistory(this.userId, receiverId);
                    JSONArray chatArr = new JSONArray();
                    if(listChat == null) {
                        messRes.put("data", chatArr);
                    }
                    else {
                        for(ChatMessage chat : listChat) {
                            JSONObject chatObj = new JSONObject(chat);
                            chatArr.put(chatObj);
                        }
                        messRes.put("data", chatArr);
                    }
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case REPORT_USER:
            {
                try {
                    int receiverId = dataIn.read();
                    
                    JSONObject reportRes = new JSONObject();
                    if(db.reportUser(this.userId, receiverId)) {
                        reportRes.put("status", MessageStatus.SUCCESS.toString());
                    }
                    else {
                        reportRes.put("status", MessageStatus.FAIL.toString());
                    }
                    
                    messRes.put("data", reportRes);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case BLOCK_USER:
            {
                try {
                    int blockedId = dataIn.read();
                    
                    JSONObject blockRes = new JSONObject();
                    blockRes.put("id", -1);
                    
                    if(db.checkIfBlocked(this.userId, blockedId)) {
                        blockRes.put("status", MessageStatus.FAIL.toString());
                        blockRes.put("statusDetail", "Already blocked!");
                    }
                    else {
                        if(db.blockUser(this.userId, blockedId)) {
                            if(db.checkIfIsFriend(this.userId, blockedId)) {
                                db.unfriend(this.userId, blockedId);
                                blockRes.put("id", blockedId);
                            }
                            blockRes.put("status", MessageStatus.SUCCESS.toString());
                            blockRes.put("statusDetail", "Done blocked!");
                        }
                        else {
                            blockRes.put("status", MessageStatus.FAIL.toString());
                            blockRes.put("statusDetail", "Block failed!");
                        }
                    }
                    
                    messRes.put("data", blockRes);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case CHAT_USER:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int receiverId = messReceived.getInt("id");
                    String content = messReceived.getString("content");
                    broadCastMessToUsers(clientUsername, receiverId, content);
                    db.chatUser(this.userId, receiverId, content);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case CLEAR_CHAT_HISTORY:
            {
                try {
                    int otherUser = dataIn.read();
                    
                    JSONObject resObj = new JSONObject();
                    if(db.clearChatUserHistory(this.userId, otherUser)) {
                        resObj.put("status", MessageStatus.SUCCESS.toString());
                        resObj.put("statusDetail", "Clear chat history done!");
                    }
                    else {
                        resObj.put("status", MessageStatus.FAIL.toString());
                        resObj.put("statusDetail", "System error! Please try again!");
                    }
                    messRes.put("data", resObj);

                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case FIND_MESSAGE:
                try {
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    String chatName = messReceived.getString("name");
                    String query = messReceived.getString("query");
                    ArrayList<ChatMessage> listMess;
                    if(!chatName.equals(""))
                        listMess = db.searchMessagesByUser(this.userId, chatName, query);
                    else {
                        listMess = db.searchAllMessages(this.userId, query);
                    }
                    JSONArray messArr = new JSONArray();
                    for(ChatMessage chat : listMess) {
                        JSONObject messObj = new JSONObject(chat);
                        messArr.put(messObj);
                    }
                    messRes.put("data", messArr);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                break;
                
            case VIEW_ALL_GROUPS:
            {
                ArrayList<GroupChat> listGroups = db.getAllGroupsOfUser(this.userId);
                JSONArray groupArr = new JSONArray();
                
                try {
                    if(listGroups == null) {
                        messRes.put("data", groupArr);
                    }
                    else {
                        for(GroupChat gr : listGroups) {
                            JSONObject grObj = new JSONObject(gr);
                            groupArr.put(grObj);
                        }
                        messRes.put("data", groupArr);
                    }
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case VIEW_GROUP_CHAT_HISTORY:
            {
                try {
                    int grId = dataIn.read();
                    
                    ArrayList<ChatMessage> listChat = db.getChatGroupHistory(this.userId, grId);
                    JSONArray chatArr = new JSONArray();
                    if(listChat == null) {
                        messRes.put("data", chatArr);
                    }
                    else {
                        for(ChatMessage chat : listChat) {
                            JSONObject chatObj = new JSONObject(chat);
                            chatArr.put(chatObj);
                        }
                        messRes.put("data", chatArr);
                    }
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case VIEW_MEMBERS:
            {
                try {
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int groupId = messReceived.getInt("groupId");
                    boolean isEncrypted = messReceived.getBoolean("isEncrypted");
                    ArrayList<GroupMember> listMems;
                    JSONArray memArr = new JSONArray();
                    JSONObject messObj = new JSONObject();
                    
                    if(!isEncrypted) {
                        listMems = db.getAllMembers(groupId);
                        if(listMems != null) {
                            for(GroupMember mem : listMems) {
                                memArr.put(new JSONObject(mem));
                            }
                        }  
                    }
                    else {
                        listMems = db.getAllMembersWithKeys(groupId);
                        if(listMems != null) {
                            for(GroupMember mem : listMems) {
                                JSONObject memObj = new JSONObject(mem);
                                JSONArray keyStrs = new JSONArray();
                                for(Byte[] keyByte : mem.getPublicKeys()) {
                                    keyStrs.put(Base64.getEncoder().encodeToString(ArrayUtils.toPrimitive(keyByte)));
                                }
                                memObj.put("publicKeys", keyStrs);
                                memArr.put(memObj);
                            }
                        }
                    }
                                              
                    messObj.put("list", memArr);
                    
                    messObj.put("isAdmin", db.checkIfIsAdmin(this.userId, groupId));
                    messObj.put("isEncrypted", isEncrypted);
                    messRes.put("data", messObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
                break;
                
            case RENAME_GROUP:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int groupId = messReceived.getInt("groupId");
                    String newName = messReceived.getString("newName");
                    
                    Boolean renameGroup = db.renameGroupChat(groupId, newName);
                    JSONObject messObj = new JSONObject();

                    if(renameGroup){
                        messObj.put("newName", newName);
                        messObj.put("groupId", groupId);
                    }
                    messRes.put("data", messObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                    
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
            
            case ADD_MEMBER:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int groupId = messReceived.getInt("groupId");
                    String username = messReceived.getString("username");
                    
                    Boolean addMember = db.addGroupChatMember(groupId, username);
                    
                    JSONObject messObj = new JSONObject();

                    if(addMember){
                        ArrayList<GroupMember> listMems = db.getAllMembers(groupId);
                        JSONArray memArr = new JSONArray();

                        if(listMems == null) {
                            messObj.put("list", memArr);
                        }
                        else {
                            for(GroupMember mem : listMems) {
                                memArr.put(new JSONObject(mem));
                            }
                            messObj.put("list", memArr);
                        }
                    }
                    messObj.put("isAdmin", db.checkIfIsAdmin(this.userId, groupId));
                    messRes.put("data", messObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case ASSIGN_ADMIN_TO_MEMBER:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int groupId = messReceived.getInt("groupId");
                    int u_id = messReceived.getInt("u_id");
                    Boolean admin = messReceived.getBoolean("is_admin");
                    
                    Boolean setAdmin = db.setAdmin(groupId, u_id, admin);
                    
                    JSONObject messObj = new JSONObject();

                    if(setAdmin){
                        ArrayList<GroupMember> listMems = db.getAllMembers(groupId);
                        JSONArray memArr = new JSONArray();

                        if(listMems == null) {
                            messObj.put("list", memArr);
                        }
                        else {
                            for(GroupMember mem : listMems) {
                                memArr.put(new JSONObject(mem));
                            }
                            messObj.put("list", memArr);
                        }
                    }
                    messObj.put("isAdmin", db.checkIfIsAdmin(this.userId, groupId));
                    messRes.put("data", messObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
            
            case DELETE_MEMBER:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int groupId = messReceived.getInt("groupId");
                    int u_id = messReceived.getInt("u_id");
                    
                    Boolean setAdmin = db.removeMember(groupId, u_id);
                    
                    JSONObject messObj = new JSONObject();

                    if(setAdmin){
                        ArrayList<GroupMember> listMems = db.getAllMembers(groupId);
                        JSONArray memArr = new JSONArray();

                        if(listMems == null) {
                            messObj.put("list", memArr);
                        }
                        else {
                            for(GroupMember mem : listMems) {
                                memArr.put(new JSONObject(mem));
                            }
                            messObj.put("list", memArr);
                        }
                    }
                    messObj.put("isAdmin", db.checkIfIsAdmin(this.userId, groupId));
                    messRes.put("data", messObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case FIND_USER_BY_USERNAME:
            {
                try{
                    String name = dataIn.readLine();
                    User foundUser = db.findUserByUsername(this.userId, name);
                    JSONObject resObj = new JSONObject();
                    if(foundUser != null) {
                        resObj.put("foundUser", new JSONObject(foundUser));
                        resObj.put("status", MessageStatus.SUCCESS.toString());
                        resObj.put("statusDetail", "Found");
                    }
                    else {
                        resObj.put("foundUser", new JSONObject());
                        resObj.put("status", MessageStatus.FAIL.toString());
                        resObj.put("statusDetail", "User not found!");
                    }
                    messRes.put("data", resObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case CREATE_GROUP:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    String groupName = messReceived.getString("name");
                    JSONArray memArr = messReceived.getJSONArray("list");
                    
                    int groupId = db.createGroup(this.userId, groupName, memArr);
                    JSONObject resObj = new JSONObject();
                    resObj.put("id", groupId);
                    resObj.put("name", groupName);
                    if(groupId != -1) {
                        resObj.put("status", MessageStatus.SUCCESS.toString());
                        resObj.put("statusDetail", "New group chat created!");
                    }
                    else {
                        resObj.put("status", MessageStatus.FAIL.toString());
                        resObj.put("statusDetail", "System error! Please try again!");
                    }
                    
                    messRes.put("data", resObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case CHAT_GROUP:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int grId = messReceived.getInt("id");
                    String content = messReceived.getString("content");
                    broadCastMessToMembers(clientUsername, grId, content);
                    db.chatGroup(this.userId, grId, content);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case ENCRYPT_GROUP:
            {
                try{
                    int groupId = dataIn.read();
                    JSONObject resObj = new JSONObject();
                    if(db.encryptGroupChat(groupId)) {
                        resObj.put("status", MessageStatus.SUCCESS.toString());
                        resObj.put("statusDetail", "Done encrypt!");
                    }
                    else {
                        resObj.put("status", MessageStatus.FAIL.toString());
                        resObj.put("statusDetail", "System error! Please try again!");
                    }
                    resObj.put("groupId", groupId);
                    
                    messRes.put("data", resObj);
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case STORE_PUBLIC_KEY:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    String keyStr = messReceived.getString("key");
                    byte[] keyBytes = Base64.getDecoder().decode(keyStr);
                    if(db.storePublicKey(this.userId, keyBytes)) {
                        broadCastPublicKeyToMembers(keyStr);
                    }
                    
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case CHAT_GROUP_ENCRYPTED:
            {
                try{
                    JSONObject messReceived = new JSONObject(dataIn.readLine());
                    int grId = messReceived.getInt("groupId");
                    int memId = messReceived.getInt("memId");
                    String content = messReceived.getString("content");
                    byte[] contentBytes = Base64.getDecoder().decode(content);
                    
                    broadCastEncryptedMessToMembers(clientUsername, memId, grId, contentBytes);
                    db.chatGroupEncrypted(this.userId, memId, grId, contentBytes);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            case VIEW_ENCRYPTED_GROUP_CHAT_HISTORY:
            {
                try{
                    int grId = dataIn.read();
                    
                    ArrayList<ChatMessage> listChat = db.getEncryptedChatGroupHistory(this.userId, grId);
                    JSONArray chatArr = new JSONArray();
                    if(listChat == null) {
                        messRes.put("data", chatArr);
                    }
                    else {
                        for(ChatMessage chat : listChat) {
                            JSONObject chatObj = new JSONObject(chat);
                            chatArr.put(chatObj);
                        }
                        messRes.put("data", chatArr);
                    }
                    dataOut.write(messRes.toString());
                    dataOut.newLine();
                    dataOut.flush();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
                break;
                
            default:
                System.out.println("Invalid message");
        }
    }
    
    private void loginSuccess(int uid, String username) {
        UserHandleDB db = UserHandleDB.getDBInstance();
        this.userId = uid;
        this.clientUsername = username;
        userHandlers.add(this);
        db.updateAccountStatus(uid, "ONLINE");
        broadCastStatusToFriends(true);
    }
    
    public void broadCastStatusToFriends(boolean isOnline) {
        UserHandleDB db = UserHandleDB.getDBInstance();
        
        JSONObject messRes = new JSONObject();
        messRes.put("type", UserMessage.FRIEND_STATUS.toString());
        
        JSONObject userSatus = new JSONObject();
        userSatus.put("id", this.userId);
        userSatus.put("isOnline", isOnline);
        
        messRes.put("data", userSatus);
        
        for(UserHandler user : userHandlers) {
            try {
                if(user != this && db.checkIfIsFriend(this.userId, user.userId)) {
                    user.dataOut.write(messRes.toString());
                    user.dataOut.newLine();
                    user.dataOut.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void broadCastMessToMembers(String sender, int groupId, String content) {
        UserHandleDB db = UserHandleDB.getDBInstance();
        
        JSONObject messRes = new JSONObject();
        messRes.put("type", UserMessage.NEW_MESSAGE_GROUP.toString());
        
        JSONObject messObj = new JSONObject();
        messObj.put("sender", sender);
        messObj.put("groupId", groupId);
        messObj.put("content", content);
        
        messRes.put("data", messObj);
        
        for(UserHandler user : userHandlers) {
            try {
                if(user != this && db.checkIfInGroupChat(this.userId, groupId)) {
                    user.dataOut.write(messRes.toString());
                    user.dataOut.newLine();
                    user.dataOut.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void broadCastEncryptedMessToMembers(String sender, int receiverId, int groupId, byte[] content) {
        UserHandleDB db = UserHandleDB.getDBInstance();
        
        JSONObject messRes = new JSONObject();
        messRes.put("type", UserMessage.NEW_ENCRYPTED_MESSAGE_GROUP.toString());
        
        JSONObject messObj = new JSONObject();
        messObj.put("sender", sender);
        messObj.put("groupId", groupId);
        messObj.put("content", Base64.getEncoder().encodeToString(content));
        
        messRes.put("data", messObj);
        
        for(UserHandler user : userHandlers) {
            if(this.userId != receiverId && user.userId == receiverId) {
                try {
                    user.dataOut.write(messRes.toString());
                    user.dataOut.newLine();
                    user.dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }
    
    public void broadCastMessToUsers(String sender, int receiverId, String content) {        
        JSONObject messRes = new JSONObject();
        messRes.put("type", UserMessage.NEW_MESSAGE_USER.toString());
        
        JSONObject messObj = new JSONObject();
        messObj.put("sender", sender);
        messObj.put("senderId", this.userId);
        messObj.put("content", content);
        
        messRes.put("data", messObj);
        
        for(UserHandler user : userHandlers) {
            if(receiverId == user.userId) {
                try {
                    user.dataOut.write(messRes.toString());
                    user.dataOut.newLine();
                    user.dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    
    public void broadCastPublicKeyToMembers(String keyStr) {
        JSONObject messRes = new JSONObject();
        messRes.put("type", UserMessage.NEW_USER_DEVICE_ENCRYPT.toString());
        
        UserHandleDB db = UserHandleDB.getDBInstance();
        
        HashMap<Integer, ArrayList<Integer>> res = db.getAllEncryptedGroupsAndMembers(this.userId);
        if(res == null)
            return;
        
        JSONObject keyObj = new JSONObject();
        keyObj.put("newUser", this.userId);
        keyObj.put("key", keyStr);
        for(UserHandler user : userHandlers) {
            if(res.containsKey(user.userId)) {
                JSONArray grArr = new JSONArray(res.get(user.userId));
                keyObj.put("groups", grArr);
                
                messRes.put("data", keyObj);
                try {
                    user.dataOut.write(messRes.toString());
                    user.dataOut.newLine();
                    user.dataOut.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    protected void removeClientFromList() {
        userHandlers.remove(this);
        UserHandleDB db = UserHandleDB.getDBInstance();
        db.updateAccountStatus(this.userId, "OFFLINE");
        broadCastStatusToFriends(false);
    }
}
