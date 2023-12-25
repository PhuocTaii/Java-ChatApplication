/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import com.btv.User.gui.interfaces.GroupMemActionEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author tvan
 */
public class RemoveMemBtn extends JButton {
    public RemoveMemBtn() {
        super("Remove");
        setBackground(new Color(239,149,149));
        setOpaque(true);
        setMargin(new java.awt.Insets(2, 0, 2, 0));
    }
    
    public void addEvent(GroupMemActionEvent event, int row) {
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event.removeMem(row);
            }
        });
    }
}
