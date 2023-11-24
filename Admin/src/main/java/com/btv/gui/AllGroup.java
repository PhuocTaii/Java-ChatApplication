/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.gui;

import com.btv.newSwing.ScrollBar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Vo Quoc Binh
 */
public class AllGroup extends javax.swing.JPanel {

    String query = "";
    String queryOrder = "";

    public AllGroup() {
        initComponents();
        testData(tableGroups);
    }

    private void testData(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        loginScollPane.setVerticalScrollBar(new ScrollBar());

        model.addRow(new Object[]{1, "Chai", "Beverages", 18, 39});
        model.addRow(new Object[]{2, "Chang", "Beverages", 19, 39});
        model.addRow(new Object[]{3, "Aniseed Syrup", "Beverages", 18, 39});
        model.addRow(new Object[]{4, "Chef Anton's Cajun Seasoning", "Beverages", 19, 39});
        model.addRow(new Object[]{5, "Chef Anton's Gumbo Mix", "Beverages", 18, 39});
        model.addRow(new Object[]{6, "Grandma's Boysenberry Spread", "Beverages", 19, 39});
        model.addRow(new Object[]{7, "Uncle Bob's Organic Dried Pears", "Beverages", 18, 39});
        model.addRow(new Object[]{8, "Northwoods Cranberry Sauce", "Beverages", 19, 39});
        model.addRow(new Object[]{9, "Mishi Kobe Niku", "Beverages", 18, 39});
        model.addRow(new Object[]{10, "Ikura", "Beverages", 19, 39});
        model.addRow(new Object[]{11, "Queso Cabrales", "Beverages", 18, 39});
        model.addRow(new Object[]{12, "Queso Manchego La Pastora", "Beverages", 19, 39});
        model.addRow(new Object[]{13, "Konbu", "Beverages", 18, 39});
        model.addRow(new Object[]{14, "Tofu", "Seafood", 19, 39});
        model.addRow(new Object[]{15, "Genen Shouyu", "Seafood", 18, 39});
        model.addRow(new Object[]{16, "Pavlova", "Seafood", 19, 39});
        model.addRow(new Object[]{17, "Alice Mutton", "Seafood", 18, 39});
        model.addRow(new Object[]{18, "Carnarvon Tigers", "Seafood", 19, 39});
        model.addRow(new Object[]{19, "Teatime Chocolate Biscuits", "Seafood", 19, 39});
        model.addRow(new Object[]{20, "Sir Rodney's Marmalade", "Seafood", 19, 39});
        model.addRow(new Object[]{21, "Sir Rodney's Scones", "Seafood", 19, 39});
        model.addRow(new Object[]{22, "Gustaf's Knäckebröd", "Seafood", 19, 39});
        model.addRow(new Object[]{23, "Tunnbröd", "Seafood", 19, 39});
        model.addRow(new Object[]{24, "Guaraná Fantástica", "Seafood", 19, 39});
        model.addRow(new Object[]{25, "NuNuCa Nuß-Nougat-Creme", "Seafood", 19, 39});
        model.addRow(new Object[]{26, "Gumbär Gummibärchen", "Seafood", 19, 39});
        model.addRow(new Object[]{27, "Schoggi Schokolade", "Seafood", 19, 39});
        model.addRow(new Object[]{28, "Rössle Sauerkraut", "Seafood", 19, 39});
        model.addRow(new Object[]{29, "Thüringer Rostbratwurst", "Seafood", 19, 39});
        model.addRow(new Object[]{30, "Nord-Ost Matjeshering", "Seafood", 19, 39});
        model.addRow(new Object[]{31, "Gorgonzola Telino", "Seafood", 19, 39});
        model.addRow(new Object[]{32, "Mascarpone Fabioli", "Seafood", 19, 39});
        model.addRow(new Object[]{33, "Geitost", "Seafood", 19, 39});
        model.addRow(new Object[]{34, "Sasquatch Ale", "Seafood", 19, 39});
        model.addRow(new Object[]{35, "Steeleye Stout", "Seafood", 19, 39});
        model.addRow(new Object[]{36, "Inlagd Sill", "Seafood", 19, 39});
        model.addRow(new Object[]{37, "Gravad lax", "Seafood", 19, 39});
        model.addRow(new Object[]{38, "Côte de Blaye", "Seafood", 19, 39});
        model.addRow(new Object[]{39, "Chartreuse verte", "Seafood", 19, 39});
        model.addRow(new Object[]{40, "Boston Crab Meat", "Seafood", 19, 39});
        model.addRow(new Object[]{41, "Jack's New England Clam Chowder", "Seafood", 19, 39});
        model.addRow(new Object[]{42, "Singaporean Hokkien Fried Mee", "Seafood", 19, 39});
        model.addRow(new Object[]{43, "Ipoh Coffee", "Seafood", 19, 39});
    }

    public void filter(String query, String queryOrder) {

        int sortKey = 0;
        SortOrder sortKeyOrder = SortOrder.ASCENDING;

        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(tableGroups.getModel());
        tableGroups.setRowSorter(sorter);

        if (query != "None") {
            if (query.equals("ID")) {
                sortKey = 0;
            } else if (query.equals("Name")) {
                sortKey = 1;
            } else {
                sortKey = 2;
            }
        }

        if (queryOrder != "None") {
            if (queryOrder.equals("ASC")) {
                sortKeyOrder = SortOrder.ASCENDING;
            } else {
                sortKeyOrder = SortOrder.DESCENDING;
            }
        }

        List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
        sortKeys.add(new RowSorter.SortKey(sortKey, sortKeyOrder));
        sorter.setSortKeys(sortKeys);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        comboBoxOrder = new com.btv.newSwing.ComboboxCustom();
        comboBox = new com.btv.newSwing.ComboboxCustom();
        jLabel1 = new javax.swing.JLabel();
        loginScollPane = new javax.swing.JScrollPane();
        tableGroups = new com.btv.newSwing.TableCustom();

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(comboBoxOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
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
                    .addComponent(comboBoxOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(259, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxOrderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxOrderItemStateChanged
        query = comboBox.getSelectedItem().toString();
        queryOrder = comboBoxOrder.getSelectedItem().toString();

        filter(query, queryOrder);
    }//GEN-LAST:event_comboBoxOrderItemStateChanged

    private void comboBoxOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxOrderActionPerformed

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged
        query = comboBox.getSelectedItem().toString();
        queryOrder = comboBoxOrder.getSelectedItem().toString();

        filter(query, queryOrder);
    }//GEN-LAST:event_comboBoxItemStateChanged

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.btv.newSwing.ComboboxCustom comboBox;
    private com.btv.newSwing.ComboboxCustom comboBoxOrder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane loginScollPane;
    private com.btv.newSwing.TableCustom tableGroups;
    // End of variables declaration//GEN-END:variables
}
