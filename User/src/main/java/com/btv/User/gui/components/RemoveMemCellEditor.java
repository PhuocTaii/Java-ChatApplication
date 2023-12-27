    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.gui.interfaces.GroupMemActionEvent;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author tvan
 */
public class RemoveMemCellEditor extends DefaultCellEditor {
    private GroupMemActionEvent event;
    private boolean isAdmin;
    
    public RemoveMemCellEditor(GroupMemActionEvent event, boolean isAdmin) {
        super(new JCheckBox());
        this.event = event;
        this.isAdmin = isAdmin;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object object, boolean bln, int row, int column) {
        RemoveMemBtn action = new RemoveMemBtn(isAdmin);
        action.addEvent(event, row);
        return action;
    }
}
