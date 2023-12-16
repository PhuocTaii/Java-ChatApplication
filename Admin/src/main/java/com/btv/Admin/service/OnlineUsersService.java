/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author taing
 */
public class OnlineUsersService {
    public String[][] getAllOnlineUsers(String startDate, String endDate){
        ClientSocket clientSocket = ClientSocket.getInstance();
        
//        System.out.println(startDate.toString());
//        System.out.println(endDate.toString());
        
//        LocalDate fromDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//        LocalDate toDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

//        System.out.println(fromDate);
//        System.out.println(toDate);
        try{
            
            clientSocket.dataOut.write(MessageType.VIEW_ONLINE_USERS.toString());
            clientSocket.dataOut.newLine();
            

            clientSocket.dataOut.write(startDate+ "|" + endDate + "|");
            clientSocket.dataOut.newLine();

            
            clientSocket.dataOut.flush();
            
            int numUsers = clientSocket.dataIn.read();
            
            ArrayList<String[]> onlineUsers = new ArrayList<>();
            for(int i = 0; i < numUsers; i++) {
                String userData = clientSocket.dataIn.readLine();
                onlineUsers.add(userData.split("\\|"));
            }
            String[][] usersArray = new String[onlineUsers.size()][];
            return onlineUsers.toArray(usersArray);
        } catch(IOException e){
            System.err.println(e);
            return  null;
        }
    }
    
    public String[][] getAllLoginTimes(){
        ClientSocket clientSocket = ClientSocket.getInstance();
        try{
            clientSocket.dataOut.write(MessageType.VIEW_LOGINS_LOG.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            int numLogs = clientSocket.dataIn.read();
            ArrayList<String[]> loginLog = new ArrayList<>();
            for(int i = 0; i < numLogs; i++){
                String logData = clientSocket.dataIn.readLine();
                loginLog.add(logData.split("\\|"));
            }
            String[][] logsArray = new String[loginLog.size()][];
            return loginLog.toArray(logsArray);
        } catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }
//    
    public int[] MakeChart(String[][]tmp, int year){
        int monthCnt[] = new int[12];

        try{
            for(int i = 0; i < tmp.length; i++){
                Date creationTime = new SimpleDateFormat("yyyy-MM-dd").parse(tmp[i][1]);
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(creationTime);
                if(cal.get(Calendar.YEAR) == year){
                    int idx = cal.get(Calendar.MONTH);
                    monthCnt[idx]++;
                }
            }
                return monthCnt;
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
