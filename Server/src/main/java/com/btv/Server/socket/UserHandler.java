/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.socket;

import com.btv.Server.database.ChatDB;
import com.btv.Server.helpers.MessageStatus;
import com.btv.Server.helpers.UserMessage;
import com.btv.Server.model.ChatMessage;
import com.btv.Server.model.User;
import com.btv.Server.service.MailService;
import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author tvan
 */
public class UserHandler extends ClientHandler{
    public static ArrayList<UserHandler> userHandlers = new ArrayList<>();
    
    private int userId;
    private Socket clientSocket;
    public UserHandler(Socket clientSocket) {
        super(clientSocket);
        this.clientSocket = clientSocket;
    }

    public void handleMessage(String messStr) {
        UserMessage mess =  UserMessage.valueOf(messStr);
        ChatDB db = ChatDB.getDBInstance();
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
                            int uid = db.signUp(newUser);
                            if(uid <= 0) {
                                isSuccess = false;
                            }
                            else {
                                loginSuccess(uid);
                            }
                        }
                    }
                    
                    // SEND STATUS
                    if(isSuccess) {
                        dataOut.write(MessageStatus.SUCCESS.toString());
                        dataOut.newLine();
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
                        loginSuccess(uid);
                        
                        dataOut.write(MessageStatus.SUCCESS.toString());
                        dataOut.newLine();
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
                    else if(db.checkIfBlocked(this.userId, friendId)) {
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
                
            default:
                System.out.println("Invalid message");
        }
    }
    
    private void loginSuccess(int uid) {
        ChatDB db = ChatDB.getDBInstance();
        this.userId = uid;
        userHandlers.add(this);
        db.updateAccountStatus(uid, "ONLINE");
        broadCastStatusToFriends(true);
    }
    
    public void broadCastStatusToFriends(boolean isOnline) {
        ChatDB db = ChatDB.getDBInstance();
        
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
    
    protected void removeClientFromList() {
        userHandlers.remove(this);
        ChatDB db = ChatDB.getDBInstance();
        db.updateAccountStatus(this.userId, "OFFLINE");
        broadCastStatusToFriends(false);
    }
}
