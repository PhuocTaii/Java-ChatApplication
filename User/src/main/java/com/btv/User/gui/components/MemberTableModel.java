/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.model.Member;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tvan
 */
public class MemberTableModel extends AbstractTableModel {
    private ArrayList<Member> memList;
    private String[] columnNames = {"Username", "Admin", ""};

    public MemberTableModel(ArrayList<Member> memList) {
        this.memList = memList;
    }

    @Override
    public int getRowCount() {
        return memList.size();
    }
    
    public void clearData() {
        memList.clear();
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
        Member member = memList.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return member.getUsername();
            case 1:
                return member.getIsAdmin();
            default:
                return null;
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 1) {
            return Boolean.class;
        }
        if (columnIndex == 2) {
            return RemoveMemBtn.class;
        }
        return String.class;
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0) {
            return false;
        } else {
            return true;
        }
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        switch (col) {
            case 0:
                memList.get(row).setUsername((String)value);
            case 1:
                memList.get(row).setIsAdmin((Boolean)value);
        }
        fireTableCellUpdated(row, col);
    }
    
    public Member getMember(int row) {
        return memList.get(row);
    }
}
