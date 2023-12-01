/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.btv.User.gui.layouts;

import com.btv.User.gui.Chat;
import com.btv.User.gui.Search;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class Layout extends javax.swing.JFrame {

    /**
     * Creates new form Layout
     */
    private Chat chatPanel;
    private Search searchPanel;
    public Layout() {
        initComponents();
        chatItem.setBackground(new Color(6, 20, 133));
        searchItem.setBackground(new Color(48, 162, 255));
        
        chatPanel = new Chat();
        searchPanel = new Search();
        chatPanel.setVisible(true);
        searchPanel.setVisible(false);
        
        contentPage.add(chatPanel);
        contentPage.add(searchPanel);
        chatPanel.setSize(contentPage.getSize());
        searchPanel.setSize(contentPage.getSize());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuLayout = new javax.swing.JPanel();
        chatItem = new javax.swing.JLabel();
        searchItem = new javax.swing.JLabel();
        contentPage = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        menuLayout.setBackground(new java.awt.Color(48, 162, 255));
        menuLayout.setPreferredSize(new java.awt.Dimension(60, 768));

        chatItem.setBackground(new java.awt.Color(48, 162, 255));
        chatItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        chatItem.setIcon(new ImageIcon(getClass().getResource("/images/message.png"))
        );
        chatItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chatItem.setOpaque(true);
        chatItem.setPreferredSize(new java.awt.Dimension(60, 40));
        chatItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chatItemMouseClicked(evt);
            }
        });

        searchItem.setBackground(new java.awt.Color(48, 162, 255));
        searchItem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchItem.setIcon(new ImageIcon(getClass().getResource("/images/search.png"))
        );
        searchItem.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchItem.setOpaque(true);
        searchItem.setPreferredSize(new java.awt.Dimension(60, 40));
        searchItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchItemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuLayoutLayout = new javax.swing.GroupLayout(menuLayout);
        menuLayout.setLayout(menuLayoutLayout);
        menuLayoutLayout.setHorizontalGroup(
            menuLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(searchItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        menuLayoutLayout.setVerticalGroup(
            menuLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayoutLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(chatItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(searchItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(604, Short.MAX_VALUE))
        );

        getContentPane().add(menuLayout, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout contentPageLayout = new javax.swing.GroupLayout(contentPage);
        contentPage.setLayout(contentPageLayout);
        contentPageLayout.setHorizontalGroup(
            contentPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        contentPageLayout.setVerticalGroup(
            contentPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        getContentPane().add(contentPage, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chatItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chatItemMouseClicked
        // TODO add your handling code here:
        menuItemClicked(0);
    }//GEN-LAST:event_chatItemMouseClicked

    private void searchItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchItemMouseClicked
        // TODO add your handling code here:
        menuItemClicked(1);
    }//GEN-LAST:event_searchItemMouseClicked
    
    public void menuItemClicked(int i) {
        if(i == 0) {
            chatItem.setBackground(new Color(6, 20, 133));
            searchItem.setBackground(new Color(48, 162, 255));
            // show chat frame
            chatPanel.setVisible(true);
            searchPanel.setVisible(false);
        }
        else {
            chatItem.setBackground(new Color(48, 162, 255));
            searchItem.setBackground(new Color(6, 20, 133));
            // show search frame
            chatPanel.setVisible(false);
            searchPanel.setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chatItem;
    private javax.swing.JPanel contentPage;
    private javax.swing.JPanel menuLayout;
    private javax.swing.JLabel searchItem;
    // End of variables declaration//GEN-END:variables
}
