/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin;

import com.btv.Admin.helper.MessageType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 *
 * @author tvan
 */
public class ClientSocket { // signleton
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
            dataOut.write("admin");
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
    
    public void closeClientSocket() {
        
        try {
            if(socket != null) {
                System.out.println("Client socket closed");
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
