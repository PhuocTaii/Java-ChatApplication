/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.Model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.TextArea;
import javax.swing.BoxLayout;
import static javax.swing.GroupLayout.Alignment.CENTER;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import static javax.swing.SwingConstants.RIGHT;

/**
 *
 * @author taing
 */
public class Message extends JPanel {
    private String content;
    private String name;
    private Boolean type; // 0 for send, 1 for receive
    

    public Message(String content, String name, Boolean type) {
        super();
        setSize(660, 70);
        setPreferredSize(new Dimension(660, 70));
        setMaximumSize(new Dimension(660, 70));
        setMinimumSize(new Dimension(660, 70));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel();
        nameLabel.setText(name);
        nameLabel.setVerticalAlignment(JLabel.CENTER);

        nameLabel.setPreferredSize(new Dimension(320, 30));
        nameLabel.setMaximumSize(new Dimension(320, 30));
        nameLabel.setMinimumSize(new Dimension(320, 30));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(242, 242, 242));
        
        
        JScrollPane TextPanel = new JScrollPane();
        TextPanel.setSize(320, 40);
        TextPanel.setPreferredSize(new Dimension(320, 40));
        TextPanel.setMinimumSize(new Dimension(320, 40));
        TextPanel.setMaximumSize(new Dimension(320, 40));

        JTextArea contentArea = new JTextArea(content);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        TextPanel.getViewport().add(contentArea);
        if (!type) {
            nameLabel.setHorizontalAlignment(JLabel.LEFT);
            TextPanel.setAlignmentX(RIGHT_ALIGNMENT);
            nameLabel.setAlignmentX(RIGHT_ALIGNMENT);
            contentArea.setBackground(new Color(255, 255, 255));
        } else {
            nameLabel.setHorizontalAlignment(JLabel.RIGHT);
            nameLabel.setHorizontalAlignment(JLabel.RIGHT);
            TextPanel.setAlignmentX(LEFT_ALIGNMENT);
            nameLabel.setAlignmentX(LEFT_ALIGNMENT);
            contentArea.setBackground(new Color(224, 244, 255));
        }
        add(nameLabel);
        add(TextPanel);
    }
}
