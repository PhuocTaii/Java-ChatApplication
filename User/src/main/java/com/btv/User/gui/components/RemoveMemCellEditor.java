/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.gui.interfaces.GroupMemActionEvent;
import com.btv.User.gui.interfaces.SearchUserActionEvent;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author tvan
 */
public class RemoveMemCellEditor extends DefaultCellEditor {
    private GroupMemActionEvent event;
    
    public RemoveMemCellEditor(GroupMemActionEvent event) {
        super(new JCheckBox());
        this.event = event;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object object, boolean bln, int row, int column) {
        RemoveMemBtn action = new RemoveMemBtn();
        action.addEvent(event, row);
        return action;
    }
}
