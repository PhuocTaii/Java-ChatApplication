/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.socket;

import com.btv.Server.database.ChatDB;
import com.btv.Server.helpers.MessageStatus;
import com.btv.Server.helpers.UserMessage;
import com.btv.Server.model.User;
import com.btv.Server.service.MailService;
import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public class UserHandler extends ClientHandler{
    public static ArrayList<UserHandler> userHandlers = new ArrayList<>();
    
    private int userId;
    public UserHandler(Socket clientSocket) {
        super(clientSocket);
        userHandlers.add(this);
    }

    public void handleMessage(String messStr) {
        UserMessage mess =  UserMessage.valueOf(messStr);
        ChatDB db = ChatDB.getDBInstance();
        boolean isSuccess;
        String messFail;
        
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
                                this.userId = uid;
                                db.updateAccountStatus(uid, "ONLINE");
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
                        this.userId = uid;
                        db.updateAccountStatus(uid, "ONLINE");
                        
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
                
                
                break;
            default:
                System.out.println("Invalid message");
        }
    }
    
    protected void removeClientFromList() {
        userHandlers.remove(this);
        ChatDB db = ChatDB.getDBInstance();
        db.updateAccountStatus(this.userId, "OFFLINE");
    }
}
