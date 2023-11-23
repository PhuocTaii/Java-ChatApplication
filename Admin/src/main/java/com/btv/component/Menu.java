package com.btv.component;

import com.btv.model.MenuModel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Menu extends javax.swing.JPanel {

    public boolean isOpen = false;

    public Menu() {
        initComponents();
        setOpaque(false);
//        menuList.setOpaque(false);
        init();
    }

    void init() {
        menuList.addItem(new MenuModel("allUsers", "All users"));
        menuList.addItem(new MenuModel("login", "Login list"));
        menuList.addItem(new MenuModel("groups", "All group chats"));
        menuList.addItem(new MenuModel("spams", "Spams"));
        menuList.addItem(new MenuModel("newUsers", "New users"));
        menuList.addItem(new MenuModel("friends", "Friends"));
        menuList.addItem(new MenuModel("online", "Online users"));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuList = new com.btv.component.MenuList<>();
        labelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        testIcon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(48, 162, 255));
        setPreferredSize(new java.awt.Dimension(250, 768));

        menuList.setForeground(new java.awt.Color(255, 255, 255));
        menuList.setOpaque(false);

        labelTitle.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHAT CHAT");

        javax.swing.GroupLayout labelTitleLayout = new javax.swing.GroupLayout(labelTitle);
        labelTitle.setLayout(labelTitleLayout);
        labelTitleLayout.setHorizontalGroup(
            labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelTitleLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(testIcon)
                    .addComponent(jLabel1))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        labelTitleLayout.setVerticalGroup(
            labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelTitleLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(testIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(70, 70, 70))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menuList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(menuList, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private int widthNavbar = 250;

    public void closeNavbar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                isOpen = false;

                for (int i = widthNavbar; i > 0; i--) {
                    setSize(i, getHeight());
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    public void openNavbar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                isOpen = true;

                for (int i = 0; i < widthNavbar; i++) {
                    setSize(i, getHeight());
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }).start();

    }

    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setPaint(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);

        super.paintChildren(g);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel labelTitle;
    private com.btv.component.MenuList<String> menuList;
    private javax.swing.JLabel testIcon;
    // End of variables declaration//GEN-END:variables
}
