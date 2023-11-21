/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.btv.Admin.LayoutComponents;

import com.btv.Admin.Features.AllUsers;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Admin
 */
public class Layout extends javax.swing.JFrame {
    
    private MenuSidebar myMenu;

    /**
     * Creates new form Layout
     */
    public Layout() {
        this.setTitle("ChatChat");
        initComponents();
        // setup menu sidebar
        myMenu = new MenuSidebar();
        myMenu.setSize(menuLayout.getWidth(), menuLayout.getHeight());
        menuLayout.add(myMenu);
        myMenu.items[0].isClicked = true;
        myMenu.items[0].setBackground(new Color(6, 20, 133));
        
        for(MenuItem item: myMenu.items)
            addMouseListenerToMenuItem(item);
                
        // add screens
        allUsersScreen = new AllUsers();
        allUsersScreen.setSize(contentPage.getWidth(), contentPage.getHeight());
        contentPage.add(allUsersScreen);
        allUsersScreen.setVisible(true);
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
        contentPage = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1280, 768));
        setPreferredSize(new java.awt.Dimension(1280, 768));
        setResizable(false);

        menuLayout.setPreferredSize(new java.awt.Dimension(200, 768));

        javax.swing.GroupLayout menuLayoutLayout = new javax.swing.GroupLayout(menuLayout);
        menuLayout.setLayout(menuLayoutLayout);
        menuLayoutLayout.setHorizontalGroup(
            menuLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        menuLayoutLayout.setVerticalGroup(
            menuLayoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        getContentPane().add(menuLayout, java.awt.BorderLayout.WEST);

        javax.swing.GroupLayout contentPageLayout = new javax.swing.GroupLayout(contentPage);
        contentPage.setLayout(contentPageLayout);
        contentPageLayout.setHorizontalGroup(
            contentPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        contentPageLayout.setVerticalGroup(
            contentPageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE)
        );

        getContentPane().add(contentPage, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPage;
    private javax.swing.JPanel menuLayout;
    // End of variables declaration//GEN-END:variables
    private AllUsers allUsersScreen;
    
    private void addMouseListenerToMenuItem(MenuItem currItem) {
        currItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if(!currItem.isClicked)
                    currItem.setBackground(new Color(7,22,187));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(!currItem.isClicked)
                    currItem.setBackground(new Color(48,162,255));
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                // open screen related to menu item
                switch (currItem.name) {
                    case "All users":
                        allUsersScreen.setVisible(true);
                        break;
                    case "Login list":
                        allUsersScreen.setVisible(false);
                        break;
                    case "All group chats":
                        allUsersScreen.setVisible(false);
                        break;
                    case "Spams":
                        allUsersScreen.setVisible(false);
                        break;
                    case "New users":
                        allUsersScreen.setVisible(false);
                        break;
                    case "Friends":
                        allUsersScreen.setVisible(false);
                        break;
                    case "Online users":
                        allUsersScreen.setVisible(false);
                        break;
                    default:

                }
                
                // reset other panels (not the clicked menu item)
                for(MenuItem tempItem:myMenu.items) {
                    tempItem.isClicked = false;
                    tempItem.setBackground(new Color(48,162,255));
                }
                currItem.isClicked = true;
                currItem.setBackground(new Color(6, 20, 133));
            }
        });
    }
}