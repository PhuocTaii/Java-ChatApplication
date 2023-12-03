package com.btv.Admin.gui.components;

import com.btv.Admin.gui.interfaces.EventMenuSelected;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Menu extends javax.swing.JPanel {

    public boolean isOpen = false;
    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
        menuList.addEventMenuSelected(event);
    }

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

        menuList = new com.btv.Admin.gui.components.MenuList<>();
        labelTitle = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        testIcon = new javax.swing.JLabel();
        hamButton = new javax.swing.JLabel();

        setBackground(new java.awt.Color(48, 162, 255));
        setPreferredSize(new java.awt.Dimension(250, 768));

        menuList.setForeground(new java.awt.Color(255, 255, 255));
        menuList.setOpaque(false);

        labelTitle.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CHAT CHAT");

        hamButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/images/ham-white.png"))); // NOI18N
        hamButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hamButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout labelTitleLayout = new javax.swing.GroupLayout(labelTitle);
        labelTitle.setLayout(labelTitleLayout);
        labelTitleLayout.setHorizontalGroup(
            labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labelTitleLayout.createSequentialGroup()
                .addGroup(labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(labelTitleLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hamButton)
                            .addComponent(testIcon)))
                    .addGroup(labelTitleLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        labelTitleLayout.setVerticalGroup(
            labelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, labelTitleLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(hamButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(testIcon)
                .addGap(61, 61, 61)
                .addComponent(jLabel1)
                .addContainerGap(104, Short.MAX_VALUE))
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

    private void hamButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamButtonMouseClicked
        closeNavbar();
    }//GEN-LAST:event_hamButtonMouseClicked
    private int widthNavbar = 250;

    public void closeNavbar() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                isOpen = false;

                for (int i = widthNavbar; i > 0; i--) {
                    setSize(i, getHeight());
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
    private javax.swing.JLabel hamButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel labelTitle;
    private com.btv.Admin.gui.components.MenuList<String> menuList;
    private javax.swing.JLabel testIcon;
    // End of variables declaration//GEN-END:variables
}
