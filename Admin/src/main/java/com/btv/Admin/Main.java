/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.btv.Admin;

import com.btv.Admin.gui.interfaces.EventMenuSelected;
import com.btv.Admin.gui.AllGroups;
import com.btv.Admin.gui.AllUsers;
import com.btv.Admin.gui.ListLogin;
import com.btv.Admin.gui.NewUsers;
import com.btv.Admin.gui.OnlineUsers;
import com.btv.Admin.gui.AllSpams;
import com.btv.Admin.gui.UserFriends;
import com.formdev.flatlaf.FlatLightLaf;
import java.text.ParseException;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vo Quoc Binh
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    private AllUsers users;
    private NewUsers newUsers;
    private UserFriends userFriends;
    private OnlineUsers onlineUsers;
    private AllGroups group;
    private AllSpams spam;
    private ListLogin listLogin;

    public Main(){
        initComponents();
        
        users = new AllUsers();
        newUsers = new NewUsers();
        userFriends = new UserFriends();
        onlineUsers = new OnlineUsers();
        group = new AllGroups();
        
        spam = new AllSpams(this);
        
        listLogin = new ListLogin();
        
        menu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selected(int index) {
                System.out.println(index);
                switch (index) {
                    case 0:
                        setForm(users);
                        users.updateTable();
                        break;
                    case 1:
                        setForm(listLogin);
                        listLogin.updateTable();
                        break;
                    case 2:
                        setForm(group);
                        group.updateTable();
                        break;
                    case 3:
                        setForm(spam);
                        spam.updateTable();
                        break;
                    case 4:
                        setForm(newUsers);
                        newUsers.updateTable();
                        break;
                    case 5:
                        setForm(userFriends);
                        userFriends.updateTable();
                        break;
                    default:
                        setForm(onlineUsers);
                        break;
                }
            }
        });
        
        setForm(users);
    }

    private void setForm(JComponent com) {
        mainContent.add(com);
        mainContent.repaint();
        mainContent.revalidate();
        mainContent.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.btv.Admin.gui.components.PanelBorder();
        menu = new com.btv.Admin.gui.components.Menu();
        hamButton = new javax.swing.JLabel();
        mainContent = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBorder1.setBackground(new java.awt.Color(237, 237, 237));
        panelBorder1.setOpaque(false);
        panelBorder1.setPreferredSize(new java.awt.Dimension(1080, 768));

        hamButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hamButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/images/ham.png"))); // NOI18N
        hamButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hamButtonMouseClicked(evt);
            }
        });

        mainContent.setOpaque(false);
        mainContent.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(mainContent, javax.swing.GroupLayout.DEFAULT_SIZE, 1080, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(33, 33, 33)
                    .addComponent(hamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(1014, Short.MAX_VALUE)))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(mainContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBorder1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(hamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(712, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void hamButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamButtonMouseClicked
        mainContent.setVisible(false);
        menu.openNavbar();
    }//GEN-LAST:event_hamButtonMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatLightLaf.setup();
        
        // init instance to send role
        ClientSocket.getInstance();
        
//        System.out.println("------------------------");
        
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Main mainClient = new Main();
                mainClient.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hamButton;
    private javax.swing.JPanel mainContent;
    private com.btv.Admin.gui.components.Menu menu;
    private com.btv.Admin.gui.components.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
