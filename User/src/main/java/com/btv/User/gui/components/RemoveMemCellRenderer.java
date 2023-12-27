/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author tvan
 */
public class RemoveMemCellRenderer extends DefaultTableCellRenderer {
    private boolean isAdmin;
    public RemoveMemCellRenderer(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if(isAdmin) {
            RemoveMemBtn action = new RemoveMemBtn(true);
            return action;
        }
        Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        return com;
    }
}
