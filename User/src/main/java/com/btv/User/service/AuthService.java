package com.btv.User.service;

import com.btv.User.ClientSocket;
import com.btv.User.MainApp;
import com.btv.User.helper.MessageStatus;
import com.btv.User.helper.MessageType;
import com.btv.User.model.User;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author tvan
 */
public class AuthService {
    public MessageStatus signup(String username, String email, String password, String name, String address, Date birthDate, boolean gender) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            // send request to sign up
            clientSocket.dataOut.write(MessageType.REGISTER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            clientSocket.dataOut.write(username + "|");
            clientSocket.dataOut.write(email + "|");
            clientSocket.dataOut.write(password + "|");
            clientSocket.dataOut.write(name + "|");
            clientSocket.dataOut.write(address + "|");
            clientSocket.dataOut.write((birthDate != null ? dateFormat.format(birthDate) : "") + "|");
            clientSocket.dataOut.write(gender + "|");
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            MessageStatus res = MessageStatus.valueOf(clientSocket.dataIn.readLine());
            if(res == MessageStatus.SUCCESS) {
                User user = new User();
                user.setId(clientSocket.dataIn.read());
                user.setUsername(clientSocket.dataIn.readLine());
                MainApp.setUser(user);
                return res;
            }
            else {
                res.setMessage(clientSocket.dataIn.readLine());
                return res;
            }
        } catch (IOException e) {
            System.err.println(e);
            return MessageStatus.FAIL;
        }
    }
    
    public MessageStatus login(String username, String password) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to sign up
            clientSocket.dataOut.write(MessageType.LOGIN.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            clientSocket.dataOut.write(username);
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(password);
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            MessageStatus res = MessageStatus.valueOf(clientSocket.dataIn.readLine());
            if(res == MessageStatus.SUCCESS) {
                User user = new User();
                user.setId(clientSocket.dataIn.read());
                user.setUsername(clientSocket.dataIn.readLine());
                MainApp.setUser(user);
                return res;
            }
            else {
                res.setMessage(clientSocket.dataIn.readLine());
                return res;
            }
        } catch (IOException e) {
            System.err.println(e);
            return MessageStatus.FAIL;
        }
    }
    
    public MessageStatus forgotPassword(String username) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        
        try {
            // send request to sign up
            clientSocket.dataOut.write(MessageType.FORGOT_PASSWORD.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
                        
            clientSocket.dataOut.write(username);
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            MessageStatus res = MessageStatus.valueOf(clientSocket.dataIn.readLine());
            res.setMessage(clientSocket.dataIn.readLine());

            return res;
        } catch (IOException e) {
            System.err.println(e);
            return MessageStatus.FAIL;
        }
    }
}
