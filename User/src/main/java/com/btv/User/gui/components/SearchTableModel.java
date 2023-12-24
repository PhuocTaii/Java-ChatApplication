/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.model.User;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tvan
 */
public class SearchTableModel extends AbstractTableModel {
    private ArrayList<User> userList;
    private String[] columnNames = {"Username", "Name", "Action"};

    public SearchTableModel(ArrayList<User> userList) {
        this.userList = userList;
    }

    @Override
    public int getRowCount() {
        return userList.size();
    }
    
    public void clearData() {
        userList.clear();
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = userList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return user.getUsername();
            case 1:
                return user.getName();
            default:
                return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 2) {
            return PanelActionUser.class;
        }
        return String.class;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        switch (col) {
            case 0:
                userList.get(row).setUsername((String)value);
            case 1:
                userList.get(row).setName((String)value);
        }
        fireTableCellUpdated(row, col);
    }
    
    public User getUser(int row) {
        return userList.get(row);
    }
}
