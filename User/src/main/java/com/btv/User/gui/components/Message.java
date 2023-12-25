/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author taing
 */
public class Message extends JPanel {
    private String content;
    private String name;
    private Boolean type; // 1 for send, 0 for receive

    public Message(JPanel container, String content, String name, Boolean type) {
        super();
        
        setBorder(new EmptyBorder(0, 5, 0, 5));
        
        int messWidth = container.getSize().width;
        setSize(messWidth, 70);
        setPreferredSize(new Dimension(messWidth, 70));
        setMaximumSize(new Dimension(messWidth, 70));
        setMinimumSize(new Dimension(messWidth, 70));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel nameLabel = new JLabel();
        nameLabel.setText(name);
        nameLabel.setVerticalAlignment(JLabel.CENTER);
        
        messWidth = messWidth/2-10;
        nameLabel.setPreferredSize(new Dimension(messWidth, 30));
        nameLabel.setMaximumSize(new Dimension(messWidth, 30));
        nameLabel.setMinimumSize(new Dimension(messWidth, 30));
        nameLabel.setOpaque(true);
        nameLabel.setBackground(new Color(242, 242, 242));
        
        
        JScrollPane TextPanel = new JScrollPane();
        TextPanel.setSize(messWidth, 40);
        TextPanel.setPreferredSize(new Dimension(messWidth, 40));
        TextPanel.setMinimumSize(new Dimension(messWidth, 40));
        TextPanel.setMaximumSize(new Dimension(messWidth, 40));

        JTextArea contentArea = new JTextArea(content);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setEditable(false);
        TextPanel.getViewport().add(contentArea);
        if (!type) {
            nameLabel.setHorizontalAlignment(JLabel.LEFT);
            TextPanel.setAlignmentX(LEFT_ALIGNMENT);
            nameLabel.setAlignmentX(LEFT_ALIGNMENT);
            contentArea.setBackground(new Color(255, 255, 255));
        } else {
            nameLabel.setHorizontalAlignment(JLabel.RIGHT);
            TextPanel.setAlignmentX(RIGHT_ALIGNMENT);
            nameLabel.setAlignmentX(RIGHT_ALIGNMENT);
            contentArea.setBackground(new Color(224, 244, 255));
        }
        setAlignmentX(CENTER_ALIGNMENT);

        add(nameLabel);
        add(TextPanel);
    }
}
