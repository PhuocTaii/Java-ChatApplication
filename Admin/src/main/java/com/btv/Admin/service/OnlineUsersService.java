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
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author taing
 */
public class OnlineUsersService {
    public String[][] getAllOnlineUsers(String startDate, String endDate){
        ClientSocket clientSocket = ClientSocket.getInstance();
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
    
    public void filterBySearch(JTable table, String searchValue, String fieldName,JComboBox numberOptions){
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);
        int columnIndex = 0;
        switch (fieldName) {
            case "Username" ->
                columnIndex = 1;
            case "Direct friends" ->
                columnIndex = 3;
            default -> {
            }
        }

        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else if (columnIndex == 1) {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, columnIndex)); // Case-insensitive search
        } else if (columnIndex == 3){
            filterByNumber(table, searchValue, numberOptions);
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
    
    public void filterByName(JTable table, String searchValue){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        
        table.setRowSorter(rowSorter); 

        
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        }else
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i).*" + searchValue + ".*", 1));
    }
    
    public void filterByNumber(JTable table, String numString, JComboBox numberOptions){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        
        table.setRowSorter(rowSorter); 
        
        if(numString.trim().length() == 0){
            rowSorter.setRowFilter(null);
        } else if (Integer.parseInt(numString) < 0) {
            rowSorter.setRowFilter(null);
        } else{
            if("Equal".equals(numberOptions.getSelectedItem())){
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i).*" + numString + ".*", 3));
            }
            else if("Greater".equals(numberOptions.getSelectedItem())){
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                        int value1 = Integer.parseInt((String) entry.getValue(3)); // adjust these indices to match your table structure
                        return value1 > Integer.parseInt(numString);
                    }
                };
                rowSorter.setRowFilter(filter);
            }
            else{
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    public boolean include(RowFilter.Entry<? extends Object, ? extends Object> entry) {
                        int value1 = Integer.parseInt((String) entry.getValue(3)); // adjust these indices to match your table structure
                        return value1 < Integer.parseInt(numString);
                    }
                };
                rowSorter.setRowFilter(filter);
            }
        }
    }
}
