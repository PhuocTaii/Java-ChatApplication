package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import com.btv.Admin.model.Group;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class GroupService {

    public String[][] getAllGroups() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_GROUPS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numGroup = clientSocket.dataIn.read();

            ArrayList<String[]> groups = new ArrayList<>();
            for (int i = 0; i < numGroup; i++) {
                String userData = clientSocket.dataIn.readLine();
                groups.add(userData.split("\\|"));
            }
            String[][] usersArray = new String[groups.size()][];
            return groups.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    public void filterByField(JTable table, String fieldName, String order) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);

        List<RowSorter.SortKey> sortKeys = new ArrayList<>();

        int columnIndex = 0;
        switch (fieldName) {
            case "ID" ->
                columnIndex = 0;
            case "Name" ->
                columnIndex = 1;
            case "Time Create" ->
                columnIndex = 2;
            default -> {
            }
        }

        // If column found, set sorting
        if (columnIndex != -1) {
            if (order.equalsIgnoreCase("ASC")) {
                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING));
            } else if (order.equalsIgnoreCase("DESC")) {
                sortKeys.add(new RowSorter.SortKey(columnIndex, SortOrder.DESCENDING));
            }
            rowSorter.setSortKeys(sortKeys);
            rowSorter.sort();
        } else {
            System.out.println("Column '" + fieldName + "' not found.");
        }
    }

    public void filterByGroupName(JTable table, String searchValue) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 1)); // Case-insensitive search

        }
    }

    public String[] getGroupMember(Group group) {
        ClientSocket clientSocket = ClientSocket.getInstance();

        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_MEMBERS_GROUP.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(group.getId() + "|");
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numMember = clientSocket.dataIn.read();

            ArrayList<String> memberName = new ArrayList<>();
            for (int i = 0; i < numMember; i++) {
                String userData = clientSocket.dataIn.readLine();
                memberName.add(userData);
            }
            String[] usersArray = new String[memberName.size()];

            return memberName.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    public String[] getGroupAdmin(Group group) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_ADMINS_GROUP.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(group.getId() + "|");
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numMember = clientSocket.dataIn.read();

            ArrayList<String> memberName = new ArrayList<>();
            for (int i = 0; i < numMember; i++) {
                String userData = clientSocket.dataIn.readLine();
                memberName.add(userData);
            }
            String[] usersArray = new String[memberName.size()];

            return memberName.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }
}
