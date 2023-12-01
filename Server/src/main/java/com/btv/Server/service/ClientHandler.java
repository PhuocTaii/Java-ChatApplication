/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.service;

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
public abstract class ClientHandler implements Runnable{
    protected Socket clientSocket;
    protected BufferedReader dataIn;
    protected BufferedWriter dataOut;
    
    public ClientHandler(Socket clientSocket){
        try{
            this.clientSocket = clientSocket;
            this.dataIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.dataOut = new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));
        } catch(IOException e){
            System.err.println(e);
        }
    }
    
    @Override
    final public void run() {
        String messageFromClient;

        while(clientSocket.isConnected()){
            try{
                messageFromClient = dataIn.readLine();
                handleMessage(messageFromClient);
            } catch(IOException e){
                System.err.println(e);
                break;
            }
        }

        closeClientSocket();
    }
    
    public abstract void handleMessage(String messStr);
    
    public void closeClientSocket() {
        try {
            if(clientSocket != null && clientSocket.isConnected()) {
                System.out.println("client closed");
                clientSocket.close();
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
