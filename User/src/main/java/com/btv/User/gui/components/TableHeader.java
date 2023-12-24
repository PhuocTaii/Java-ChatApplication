package com.btv.User.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vo Quoc Binh
 */
public class TableHeader extends JLabel {

    public TableHeader(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.BLACK);
        setFont(new Font("sansserif", 1, 12));
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(10, 20, 10, 5));
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(230, 230, 230));
        g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
    
}
