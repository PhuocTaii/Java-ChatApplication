/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.newSwing;

import com.btv.User.helper.TableActionEvent;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Vo Quoc Binh
 */
public class TableActionCellEditor extends DefaultCellEditor{
    private TableActionEvent event;
    
    public TableActionCellEditor(TableActionEvent event){
        super(new JCheckBox());
        this.event=event;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        PanelActionUser action=new PanelActionUser();
        action.initEvent(event, row);
        action.setBackground(table.getSelectionBackground());
        return action;
    }
    
    
    
}
