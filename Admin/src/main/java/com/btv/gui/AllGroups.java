/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.btv.gui;

import com.btv.newSwing.ScrollBar;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Vo Quoc Binh
 */
public class AllGroups extends javax.swing.JFrame {

    /**
     * Creates new form AllGroups
     */
    public AllGroups() {
        initComponents();
        init();
    }

    private int pX;
    private int pY;

    private boolean isNavbarVisible = false;

    private int widthNavbar = 250;

    String query = "";
    String queryOrder = "";

    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/com/btv/image"))
                .getImage()
                .getScaledInstance(hamButton.getWidth(), hamButton.getHeight(), Image.SCALE_SMOOTH));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundGroup = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        hamButton = new javax.swing.JLabel();
        loginScollPane = new javax.swing.JScrollPane();
        tableGroups = new com.btv.newSwing.TableCustom();
        jLabel2 = new javax.swing.JLabel();
        comboBox = new com.btv.newSwing.ComboboxCustom();
        comboBoxOrder = new com.btv.newSwing.ComboboxCustom();
        menuGroups = new com.btv.component.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(1080, 768));

        backgroundGroup.setBackground(new java.awt.Color(237, 237, 237));
        backgroundGroup.setPreferredSize(new java.awt.Dimension(1080, 768));
        backgroundGroup.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                backgroundGroupMouseDragged(evt);
            }
        });
        backgroundGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundGroupMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backgroundGroupMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("ALL GROUPS");

        hamButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hamButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/ham.png"))); // NOI18N
        hamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hamButtonMouseClicked(evt);
            }
        });

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
        if (tableGroups.getColumnModel().getColumnCount() > 0) {
            tableGroups.getColumnModel().getColumn(0).setResizable(false);
            tableGroups.getColumnModel().getColumn(0).setPreferredWidth(30);
            tableGroups.getColumnModel().getColumn(1).setResizable(false);
            tableGroups.getColumnModel().getColumn(1).setPreferredWidth(100);
            tableGroups.getColumnModel().getColumn(2).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabel2.setText("Filtered by");

        comboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Username", "Time report", "Reported user", "Lock" }));
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

        javax.swing.GroupLayout backgroundGroupLayout = new javax.swing.GroupLayout(backgroundGroup);
        backgroundGroup.setLayout(backgroundGroupLayout);
        backgroundGroupLayout.setHorizontalGroup(
            backgroundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundGroupLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(backgroundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundGroupLayout.createSequentialGroup()
                        .addComponent(hamButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(368, 368, 368))
                    .addGroup(backgroundGroupLayout.createSequentialGroup()
                        .addGroup(backgroundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundGroupLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(comboBoxOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(40, Short.MAX_VALUE))))
        );
        backgroundGroupLayout.setVerticalGroup(
            backgroundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundGroupLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(backgroundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundGroupLayout.createSequentialGroup()
                        .addComponent(hamButton)
                        .addGap(24, 24, 24)))
                .addGap(18, 18, 18)
                .addGroup(backgroundGroupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(353, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menuGroups, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(backgroundGroup, javax.swing.GroupLayout.PREFERRED_SIZE, 1083, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuGroups, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backgroundGroup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void hamButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamButtonMouseClicked
        menuGroups.openNavbar();
        setBackgroundVisible(false);
        isNavbarVisible = true;
    }//GEN-LAST:event_hamButtonMouseClicked

    private void backgroundGroupMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundGroupMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX,
                this.getLocation().y + evt.getY() - pY);
        // TODO add your handling code here:
    }//GEN-LAST:event_backgroundGroupMouseDragged

    private void backgroundGroupMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundGroupMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_backgroundGroupMousePressed

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxActionPerformed

    private void backgroundGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundGroupMouseClicked
        if (isNavbarVisible) {
            menuGroups.closeNavbar();
            setBackgroundVisible(true);
            isNavbarVisible = false;
        }
    }//GEN-LAST:event_backgroundGroupMouseClicked

    private void comboBoxOrderItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxOrderItemStateChanged
        query = comboBox.getSelectedItem().toString();
        queryOrder=comboBoxOrder.getSelectedItem().toString();
        
        filter(query, queryOrder);
    }//GEN-LAST:event_comboBoxOrderItemStateChanged

    private void comboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxItemStateChanged
        query = comboBox.getSelectedItem().toString();
        queryOrder=comboBoxOrder.getSelectedItem().toString();
        
        filter(query, queryOrder);
    }//GEN-LAST:event_comboBoxItemStateChanged

    private void comboBoxOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOrderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxOrderActionPerformed

    private void setBackgroundVisible(boolean bl) {
        tableGroups.setVisible(bl);
        tableGroups.getTableHeader().setVisible(bl);
    }

    /**
     * @param args the command line arguments
     */
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



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundGroup;
    private com.btv.newSwing.ComboboxCustom comboBox;
    private com.btv.newSwing.ComboboxCustom comboBoxOrder;
    private javax.swing.JLabel hamButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane loginScollPane;
    private com.btv.component.Menu menuGroups;
    private com.btv.newSwing.TableCustom tableGroups;
    // End of variables declaration//GEN-END:variables
}
