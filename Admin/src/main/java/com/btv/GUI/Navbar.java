/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.GUI;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vo Quoc Binh
 */
public class Navbar extends javax.swing.JPanel {

    /**
     * Creates new form Navbar
     */
    public Navbar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        hamButton = new javax.swing.JLabel();
        allUsersPane = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        loginListPane = new javax.swing.JPanel();
        icon1 = new javax.swing.JLabel();
        title1 = new javax.swing.JLabel();
        allGroupsPane = new javax.swing.JPanel();
        icon2 = new javax.swing.JLabel();
        title2 = new javax.swing.JLabel();
        spamPane = new javax.swing.JPanel();
        icon9 = new javax.swing.JLabel();
        title9 = new javax.swing.JLabel();
        newUserPane = new javax.swing.JPanel();
        icon10 = new javax.swing.JLabel();
        title10 = new javax.swing.JLabel();
        friendPane = new javax.swing.JPanel();
        icon11 = new javax.swing.JLabel();
        title11 = new javax.swing.JLabel();
        onlinePane = new javax.swing.JPanel();
        icon12 = new javax.swing.JLabel();
        title12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(48, 162, 255));
        setPreferredSize(new java.awt.Dimension(250, 768));

        hamButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hamButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/ham-white.png"))); // NOI18N
        hamButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hamButtonMouseClicked(evt);
            }
        });

        allUsersPane.setBackground(new java.awt.Color(48, 162, 255));

        icon.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon.setForeground(new java.awt.Color(255, 255, 255));
        icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icon.setText("  All users");
        icon.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/allUsers.png"))); // NOI18N

        javax.swing.GroupLayout allUsersPaneLayout = new javax.swing.GroupLayout(allUsersPane);
        allUsersPane.setLayout(allUsersPaneLayout);
        allUsersPaneLayout.setHorizontalGroup(
            allUsersPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, allUsersPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon)
                .addGap(12, 12, 12))
        );
        allUsersPaneLayout.setVerticalGroup(
            allUsersPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allUsersPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allUsersPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon)
                    .addComponent(title))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        loginListPane.setBackground(new java.awt.Color(48, 162, 255));

        icon1.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon1.setForeground(new java.awt.Color(255, 255, 255));
        icon1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon1.setText("  Login lists");
        icon1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/login.png"))); // NOI18N

        javax.swing.GroupLayout loginListPaneLayout = new javax.swing.GroupLayout(loginListPane);
        loginListPane.setLayout(loginListPaneLayout);
        loginListPaneLayout.setHorizontalGroup(
            loginListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginListPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon1)
                .addGap(12, 12, 12))
        );
        loginListPaneLayout.setVerticalGroup(
            loginListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginListPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(loginListPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon1)
                    .addComponent(title1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        allGroupsPane.setBackground(new java.awt.Color(48, 162, 255));

        icon2.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon2.setForeground(new java.awt.Color(255, 255, 255));
        icon2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon2.setText("  All group chats");
        icon2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/groups.png"))); // NOI18N

        javax.swing.GroupLayout allGroupsPaneLayout = new javax.swing.GroupLayout(allGroupsPane);
        allGroupsPane.setLayout(allGroupsPaneLayout);
        allGroupsPaneLayout.setHorizontalGroup(
            allGroupsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, allGroupsPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon2)
                .addGap(12, 12, 12))
        );
        allGroupsPaneLayout.setVerticalGroup(
            allGroupsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(allGroupsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(allGroupsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon2)
                    .addComponent(title2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        spamPane.setBackground(new java.awt.Color(48, 162, 255));

        icon9.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon9.setForeground(new java.awt.Color(255, 255, 255));
        icon9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon9.setText("  Spams");
        icon9.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/spams.png"))); // NOI18N

        javax.swing.GroupLayout spamPaneLayout = new javax.swing.GroupLayout(spamPane);
        spamPane.setLayout(spamPaneLayout);
        spamPaneLayout.setHorizontalGroup(
            spamPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spamPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon9)
                .addGap(12, 12, 12))
        );
        spamPaneLayout.setVerticalGroup(
            spamPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spamPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spamPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon9)
                    .addComponent(title9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        newUserPane.setBackground(new java.awt.Color(48, 162, 255));

        icon10.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon10.setForeground(new java.awt.Color(255, 255, 255));
        icon10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon10.setText("  New users");
        icon10.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/newUsers.png"))); // NOI18N

        javax.swing.GroupLayout newUserPaneLayout = new javax.swing.GroupLayout(newUserPane);
        newUserPane.setLayout(newUserPaneLayout);
        newUserPaneLayout.setHorizontalGroup(
            newUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newUserPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon10)
                .addGap(12, 12, 12))
        );
        newUserPaneLayout.setVerticalGroup(
            newUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newUserPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newUserPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon10)
                    .addComponent(title10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        friendPane.setBackground(new java.awt.Color(48, 162, 255));

        icon11.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon11.setForeground(new java.awt.Color(255, 255, 255));
        icon11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon11.setText("  Friends");
        icon11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/friends.png"))); // NOI18N

        javax.swing.GroupLayout friendPaneLayout = new javax.swing.GroupLayout(friendPane);
        friendPane.setLayout(friendPaneLayout);
        friendPaneLayout.setHorizontalGroup(
            friendPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, friendPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon11)
                .addGap(12, 12, 12))
        );
        friendPaneLayout.setVerticalGroup(
            friendPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(friendPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon11)
                    .addComponent(title11))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        onlinePane.setBackground(new java.awt.Color(48, 162, 255));

        icon12.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        icon12.setForeground(new java.awt.Color(255, 255, 255));
        icon12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        icon12.setText("  Online users");
        icon12.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        title12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/online.png"))); // NOI18N

        javax.swing.GroupLayout onlinePaneLayout = new javax.swing.GroupLayout(onlinePane);
        onlinePane.setLayout(onlinePaneLayout);
        onlinePaneLayout.setHorizontalGroup(
            onlinePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, onlinePaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(icon12)
                .addGap(12, 12, 12))
        );
        onlinePaneLayout.setVerticalGroup(
            onlinePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(onlinePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(onlinePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(icon12)
                    .addComponent(title12))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHAT CHAT");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(loginListPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(allGroupsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spamPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newUserPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(friendPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(onlinePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(allUsersPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(hamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(hamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(jLabel1)
                .addGap(77, 77, 77)
                .addComponent(allUsersPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginListPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allGroupsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spamPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newUserPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(onlinePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void hamButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamButtonMouseClicked
    }//GEN-LAST:event_hamButtonMouseClicked
    private int widthNavbar = 250;

    void closeNavbar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = widthNavbar; i > 0; i--) {
                    setSize(i, getHeight());
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Navbar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    void openNavbar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < widthNavbar; i++) {
                    setSize(i, getHeight());
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Navbar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel allGroupsPane;
    private javax.swing.JPanel allUsersPane;
    private javax.swing.JPanel friendPane;
    private javax.swing.JLabel hamButton;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel icon1;
    private javax.swing.JLabel icon10;
    private javax.swing.JLabel icon11;
    private javax.swing.JLabel icon12;
    private javax.swing.JLabel icon2;
    private javax.swing.JLabel icon9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel loginListPane;
    private javax.swing.JPanel newUserPane;
    private javax.swing.JPanel onlinePane;
    private javax.swing.JPanel spamPane;
    private javax.swing.JLabel title;
    private javax.swing.JLabel title1;
    private javax.swing.JLabel title10;
    private javax.swing.JLabel title11;
    private javax.swing.JLabel title12;
    private javax.swing.JLabel title2;
    private javax.swing.JLabel title9;
    // End of variables declaration//GEN-END:variables
}
