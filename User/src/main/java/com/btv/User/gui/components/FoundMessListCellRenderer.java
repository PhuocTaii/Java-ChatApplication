/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.model.ChatMessage;
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
public class FoundMessListCellRenderer extends JLabel implements ListCellRenderer<ChatMessage> {
    public FoundMessListCellRenderer() {
        setOpaque(true);
    }
    
    @Override
    public Component getListCellRendererComponent(JList<? extends ChatMessage> list, ChatMessage mess, int index,
        boolean isSelected, boolean cellHasFocus) {
                   
        setBackground(null);
        setForeground(Color.BLACK);
        
        setText("<html><font color='#30A2FF'><b>" + mess.getChatName() + "</b></font><br>"
                + "<i>" + mess.getSendName() + "</i>" + ": " + mess.getContent()
                + "</html>");
        
        setBorder(BorderFactory.createEmptyBorder(0, 5,0, 5));
        
        return this;
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        preferredSize.height = 40;
        return preferredSize;
    }
}
