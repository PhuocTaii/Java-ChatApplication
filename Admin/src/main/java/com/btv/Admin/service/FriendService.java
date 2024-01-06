/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.Entry;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author taing
 */
public class FriendService {
    public String[][] getAllFriends(){
        ClientSocket clientSocket = ClientSocket.getInstance();
        try{
            clientSocket.dataOut.write(MessageType.VIEW_USER_FRIEND.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();
            
            int numUsers = clientSocket.dataIn.read();
            
            ArrayList<String[]> users = new ArrayList<>();
            for(int i = 0; i < numUsers; i++) {
                String userData = clientSocket.dataIn.readLine();
                users.add(userData.split("\\|"));
            }
            String[][] usersArray = new String[users.size()][];
            return users.toArray(usersArray);
        } catch(IOException e){
            System.err.println(e);
            return  null;
        }
    }
    
    public void filterByName(JTable table, String searchValue ){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        
        table.setRowSorter(rowSorter); 

        
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        }else
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i).*" + searchValue + ".*", 1));
        
    }
    
    public void filterBySearch(JTable table, String searchValue, String fieldName,JComboBox numberOptions) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);

        int columnIndex = 0;
        switch (fieldName) {
            case "Name" ->
                columnIndex = 1;
            case "Time open app" ->
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
                    public boolean include(Entry<? extends Object, ? extends Object> entry) {
                        int value1 = Integer.parseInt((String) entry.getValue(3)); // adjust these indices to match your table structure
                        return value1 > Integer.parseInt(numString);
                    }
                };
                rowSorter.setRowFilter(filter);
            }
            else{
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    public boolean include(Entry<? extends Object, ? extends Object> entry) {
                        int value1 = Integer.parseInt((String) entry.getValue(3)); // adjust these indices to match your table structure
                        return value1 < Integer.parseInt(numString);
                    }
                };
                rowSorter.setRowFilter(filter);
            }
        }
    }
}
