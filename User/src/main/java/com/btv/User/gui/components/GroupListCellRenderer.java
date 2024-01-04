/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.model.Group;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author tvan
 */
public class GroupListCellRenderer extends JLabel implements ListCellRenderer<Group> {
    public GroupListCellRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends Group> list, Group group, int index,
        boolean isSelected, boolean cellHasFocus) {
                   
        setBackground(null);
        setText(group.getName());
        
        setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
        
//        if (isSelected) {
//            setForeground(new Color(13, 113, 182));
//        } else {
//            setForeground(Color.BLACK);
//        }
        
        if(!group.getIsSeen()) {
            setForeground(Color.RED);
        } else {
            setForeground(Color.BLACK);
        }
        
        return this;
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        preferredSize.height = 20;
        return preferredSize;
    }
}
