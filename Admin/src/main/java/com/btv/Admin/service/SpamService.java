package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    public void sortByField(JTable table, String fieldName, String order, String searchValue) {
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
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 1)); // Case-insensitive search

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

    

    public void filterByDate(JTable table, Date StartDate, Date EndDate) {
        DefaultTableModel tableModel;
        String[][] userList = getAllSpam();
        tableModel = (DefaultTableModel) table.getModel();

        tableModel.setRowCount(0);

        int i = 0;

        System.out.println("-------------------");
        for (Object[] row : userList) {
            String dateString = (String) row[2];
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date entryDate = dateFormat.parse(dateString);

                if (!entryDate.before(StartDate) && !entryDate.after(EndDate)) {
                    tableModel.addRow(row);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

            i++;
        }

    }

}
