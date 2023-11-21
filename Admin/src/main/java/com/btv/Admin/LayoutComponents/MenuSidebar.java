/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.LayoutComponents;

import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class MenuSidebar extends javax.swing.JPanel {
    
    MenuItem[] items = new MenuItem[7];
    
    /**
     * Creates new form MenuSidebar
     */
    public MenuSidebar() {
        initComponents();
        items[0] = new MenuItem(new ImageIcon(getClass().getResource("/images/user.png")),  "All users");
        items[1] = new MenuItem(new ImageIcon(getClass().getResource("/images/login.png")),  "Login list");
        items[2] = new MenuItem(new ImageIcon(getClass().getResource("/images/group-chat.png")),  "All group chats");
        items[3] = new MenuItem(new ImageIcon(getClass().getResource("/images/spam.png")),  "Spams");
        items[4] = new MenuItem(new ImageIcon(getClass().getResource("/images/user-add.png")),  "New users");
        items[5] = new MenuItem(new ImageIcon(getClass().getResource("/images/friends.png")),  "Friends");
        items[6] = new MenuItem(new ImageIcon(getClass().getResource("/images/online.png")),  "Online users");
        for(MenuItem item: items) {
            menuItems.add(item);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleContainer = new javax.swing.JPanel();
        nameApp = new javax.swing.JLabel();
        menuItems = new javax.swing.JPanel();

        setBackground(new java.awt.Color(48, 162, 255));
        setPreferredSize(new java.awt.Dimension(200, 768));

        titleContainer.setMaximumSize(new java.awt.Dimension(200, 32));
        titleContainer.setMinimumSize(new java.awt.Dimension(200, 32));
        titleContainer.setOpaque(false);
        titleContainer.setPreferredSize(new java.awt.Dimension(200, 32));
        titleContainer.setLayout(new java.awt.BorderLayout());

        nameApp.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        nameApp.setForeground(new java.awt.Color(255, 255, 255));
        nameApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nameApp.setText("CHAT CHAT");
        titleContainer.add(nameApp, java.awt.BorderLayout.CENTER);

        menuItems.setOpaque(false);
        menuItems.setLayout(new javax.swing.BoxLayout(menuItems, javax.swing.BoxLayout.Y_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(titleContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(menuItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel menuItems;
    private javax.swing.JLabel nameApp;
    private javax.swing.JPanel titleContainer;
    // End of variables declaration//GEN-END:variables

    
}