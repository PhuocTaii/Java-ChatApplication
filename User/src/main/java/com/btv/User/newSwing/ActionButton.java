package com.btv.User.newSwing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton {

    private boolean mousePress;

    public ActionButton() {
        setFont(new Font("Arial", Font.BOLD, 14));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePress = true;

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mousePress = false;

            }

//            @Override
//            public void mouseClicked(MouseEvent e) {
//                System.out.println("Like");
//            }

        });
    }

}
