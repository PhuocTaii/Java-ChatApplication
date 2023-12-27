/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.gui;

import com.btv.Admin.service.FriendService;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class UserFriends extends javax.swing.JPanel {

    /**
     * Creates new form UserFriends
     */
    private DefaultTableModel tableModel;
    private String[][] friendList;
    private FriendService friendService;
    public UserFriends() {
        initComponents();
        
        friendService = new FriendService();
        friendList = friendService.getAllFriends();
        tableModel = (DefaultTableModel)tableCustom1.getModel();
        tableModel.setRowCount(0);
        for(Object[] row : friendList) {
            tableModel.addRow(row);
        }
        friendService.filterByName(tableCustom1, "");
    }
    
    public void updateTable(){
        friendList = friendService.getAllFriends();
        tableModel = (DefaultTableModel) tableCustom1.getModel();
        tableModel.setRowCount(0);
        for (Object[] row : friendList) {
            tableModel.addRow(row);
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

        headpage = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        options = new javax.swing.JPanel();
        filter = new javax.swing.JLabel();
        Input = new javax.swing.JTextField();
        numberOptions = new javax.swing.JComboBox<>();
        filterOptions = new javax.swing.JComboBox<>();
        searchButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCustom1 = new com.btv.Admin.gui.components.TableCustom();

        setOpaque(false);

        headpage.setMaximumSize(new java.awt.Dimension(121, 32));
        headpage.setMinimumSize(new java.awt.Dimension(121, 32));
        headpage.setOpaque(false);
        headpage.setPreferredSize(new java.awt.Dimension(121, 32));
        headpage.setLayout(new java.awt.BorderLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText("FRIENDS");
        headpage.add(header, java.awt.BorderLayout.CENTER);

        options.setOpaque(false);

        filter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filter.setText("Filter by");

        Input.setPreferredSize(new java.awt.Dimension(70, 30));
        Input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputActionPerformed(evt);
            }
        });

        numberOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Equal", "Greater", "Less" }));

        filterOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Name", "Number" }));
        filterOptions.setPreferredSize(new java.awt.Dimension(70, 30));
        Input.setVisible(false);
        numberOptions.setVisible(false);
        filterOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterOptionsActionPerformed(evt);
            }
        });

        searchButton.setBackground(new java.awt.Color(48, 162, 255));
        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 255, 255));
        searchButton.setText("Search");
        searchButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton.setPreferredSize(new java.awt.Dimension(75, 30));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout optionsLayout = new javax.swing.GroupLayout(options);
        options.setLayout(optionsLayout);
        optionsLayout.setHorizontalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filter)
                .addGap(18, 18, 18)
                .addComponent(filterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Input, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(numberOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(386, Short.MAX_VALUE))
        );
        optionsLayout.setVerticalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numberOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Input, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addContainerGap())
        );

        tableCustom1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Time create", "Direct friends", "Indirect friends"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableCustom1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headpage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(options, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headpage, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterOptionsActionPerformed
        // TODO add your handling code here:        
        JComboBox cb = (JComboBox)evt.getSource();
        String optionChosen = (String)cb.getSelectedItem();
        if ("None".equals(optionChosen)) {
            Input.setVisible(false);
            numberOptions.setVisible(false);
            // Show all data
        }
        else if ("Name".equals(optionChosen)) {
            Input.setVisible(true);
            numberOptions.setVisible(false);
        }
        else {
            Input.setVisible(true);
            numberOptions.setVisible(true);
        }
        
        filterOptions.revalidate();
        filterOptions.repaint();
    }//GEN-LAST:event_filterOptionsActionPerformed

    private void InputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        if("None".equals(filterOptions.getSelectedItem())){
            Input.setText("");
        }
        else if("Name".equals(filterOptions.getSelectedItem())){
            String searchString = Input.getText();
            friendService.filterByName(tableCustom1, searchString);
            Input.setText("");
        }
        else{
            String numString = Input.getText();
            friendService.filterByNumber(tableCustom1, numString, numberOptions);
            Input.setText("");
        }
    }//GEN-LAST:event_searchButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Input;
    private javax.swing.JLabel filter;
    private javax.swing.JComboBox<String> filterOptions;
    private javax.swing.JLabel header;
    private javax.swing.JPanel headpage;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> numberOptions;
    private javax.swing.JPanel options;
    private javax.swing.JButton searchButton;
    private com.btv.Admin.gui.components.TableCustom tableCustom1;
    // End of variables declaration//GEN-END:variables
}
