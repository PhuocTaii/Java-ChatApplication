/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.model.User;
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
public class FriendListCellRender extends JLabel implements ListCellRenderer<User> {
    public FriendListCellRender() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends User> list, User user, int index,
        boolean isSelected, boolean cellHasFocus) {
                   
        setBackground(null);
        setText(user.getUsername());
        
        setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
        
        if(user.isIsOnline()) {
            setForeground(Color.GREEN);
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
