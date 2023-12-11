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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author taing
 */
public class NewUserService {
    public String[][] getAllNewUsers(){
        ClientSocket clientSocket = ClientSocket.getInstance();
        try{
            clientSocket.dataOut.write(MessageType.VIEW_NEW_USERS.toString());
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
    
    public void filterByName(JTable table, String searchValue){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        
        table.setRowSorter(rowSorter); 

        
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        }else
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i).*" + searchValue + ".*", 2));
        
    }
    
    public void filterByDate(JTable table, Date StartDate, Date EndDate){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
            
        table.setRowSorter(rowSorter);
        
        RowFilter<DefaultTableModel, Integer> dateFilter = new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                DefaultTableModel model = entry.getModel();
                int row = entry.getIdentifier();
                System.out.println(model.getValueAt(row, 3).getClass());
                String dateString  = (String)model.getValueAt(row, 3);
                try{
                    Date creationTime = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                    return (!StartDate.after(creationTime) && !EndDate.before(creationTime));
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }            
        };
        
        rowSorter.setRowFilter(dateFilter);
    }
}
