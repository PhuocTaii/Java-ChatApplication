package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import com.btv.Admin.model.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class LoginListService {

    public String[][] getAllUserLogin() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_LOGINS.toString());
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
