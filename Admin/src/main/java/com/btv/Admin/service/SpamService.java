package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class SpamService {

    public String[][] getAllSpam() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            clientSocket.dataOut.write(MessageType.VIEW_SPAMS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numSpam = clientSocket.dataIn.read();

            ArrayList<String[]> spams = new ArrayList<>();
            for (int i = 0; i < numSpam; i++) {
                String userData = clientSocket.dataIn.readLine();
                spams.add(userData.split("\\|"));
            }
            String[][] spamArray = new String[spams.size()][];
            return spams.toArray(spamArray);
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
            case "Username" ->
                columnIndex = 1;
            case "Time report" ->
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

    public void filterBySearch(JTable table, String searchValue, String fieldName) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);

        int columnIndex = 0;
        switch (fieldName) {
            case "Username" ->
                columnIndex = 1;
            default -> {
            }
        }

        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 1)); // Case-insensitive search

        }
    }

}
