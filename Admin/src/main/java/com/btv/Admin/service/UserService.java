/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tvan
 */
public class UserService {

    public String[][] getAllUsers() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_USERS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numUsers = clientSocket.dataIn.read();

            ArrayList<String[]> users = new ArrayList<>();
            for (int i = 0; i < numUsers; i++) {
                String userData = clientSocket.dataIn.readLine();
                users.add(userData.split("\\|"));
            }
            String[][] usersArray = new String[users.size()][];
            return users.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    public void filterByField(JTable table, String fieldName, String searchValue) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            if (fieldName.equals("Username")) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 1)); // Case-insensitive search
            } else if (fieldName.equals("Name")) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 2)); // Case-insensitive search
            } else if (fieldName.equals("Status")) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 8)); // Case-insensitive search

            }
        }
    }
}
