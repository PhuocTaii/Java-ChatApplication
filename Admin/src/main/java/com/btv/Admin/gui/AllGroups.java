/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.gui;

import com.btv.Admin.service.GroupService;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vo Quoc Binh
 */
public class AllGroups extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private String[][] userList;
    GroupService groupService;

    String query = "ID";
    String queryOrder = "ASC";

    public AllGroups() {
        initComponents();
        groupService = new GroupService();
        updateTable();
        searchField.setForeground(Color.GRAY);
        searchField.setText("Search Group Name");
        searchField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchField.getText().equals("Search Group Name")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchField.getText().isEmpty()) {
                    searchField.setForeground(Color.GRAY);
                    searchField.setText("Search Group Name");
                }
            }
        });
        searchGroup();
    }

    public void updateTable() {
        userList = groupService.getAllGroups();
        tableModel = (DefaultTableModel) tableGroups.getModel();
        tableModel.setRowCount(0);
        for (Object[] row : userList) {
            tableModel.addRow(row);
        }
    }

    public void searchGroup() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                groupService.filterByGroupName(tableGroups, searchField.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                groupService.filterByGroupName(tableGroups, searchField.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                groupService.filterByGroupName(tableGroups, searchField.getText());
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        comboBoxOrder = new com.btv.Admin.gui.components.ComboboxCustom();
        comboBox = new com.btv.Admin.gui.components.ComboboxCustom();
        jLabel1 = new javax.swing.JLabel();
        loginScollPane = new javax.swing.JScrollPane();
        tableGroups = new com.btv.Admin.gui.components.TableCustom();
        searchField = new javax.swing.JTextField();

        setMaximumSize(new java.awt.Dimension(1080, 768));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1080, 768));

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Filtered by");

        comboBoxOrder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASC", "DESC" }));
        comboBoxOrder.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxOrderItemStateChanged(evt);
            }
        });
        comboBoxOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxOrderActionPerformed(evt);
            }
        });

        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Name", "Time Create" }));
        comboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxItemStateChanged(evt);
            }
        });
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALL GROUPS");

        tableGroups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Time create"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loginScollPane.setViewportView(tableGroups);

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(400, 400, 400))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(comboBoxOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(371, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxOrderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxOrderItemStateChanged
        query = comboBox.getSelectedItem().toString();
        queryOrder = comboBoxOrder.getSelectedItem().toString();
        groupService.filterByField(tableGroups, query, queryOrder);
    }//GEN-LAST:event_comboBoxOrderItemStateChanged

    private void comboBoxOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxOrderActionPerformed

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged
        query = comboBox.getSelectedItem().toString();
        queryOrder = comboBoxOrder.getSelectedItem().toString();
        groupService.filterByField(tableGroups, query, queryOrder);


    }//GEN-LAST:event_comboBoxItemStateChanged

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        groupService.filterByGroupName(tableGroups, searchField.getText());
    }//GEN-LAST:event_searchFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.btv.Admin.gui.components.ComboboxCustom comboBox;
    private com.btv.Admin.gui.components.ComboboxCustom comboBoxOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane loginScollPane;
    private javax.swing.JTextField searchField;
    private com.btv.Admin.gui.components.TableCustom tableGroups;
    // End of variables declaration//GEN-END:variables
}
