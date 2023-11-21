/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin.LayoutComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class MenuItem extends JLabel{

    boolean isClicked;
    String name;
    
    public MenuItem(ImageIcon icon, String nameItem) {
        isClicked = false;
        this.name = nameItem;
        
        setSize(200, 60);
        setPreferredSize(new Dimension(200, 60));
        setMaximumSize(new Dimension(200, 60));
        setMinimumSize(new Dimension(200, 60));
        setIcon(icon);
        setText(nameItem);
        setIconTextGap(10);
        setForeground(Color.WHITE);
        setOpaque(true);
        setBackground(new Color(48,162,255));
        setBorder(new EmptyBorder(20, 24, 20, 0));
        setFont(new Font("Segoe UI", 0, 16));
    }
}
