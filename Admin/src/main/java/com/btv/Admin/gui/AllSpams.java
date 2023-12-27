/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.gui;

import com.btv.Admin.gui.components.ScrollBar;
import com.btv.Admin.model.Spam;
import com.btv.Admin.service.SpamService;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.Timer;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Vo Quoc Binh
 */
public class AllSpams extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private Object[][] userList;
    SpamService spamService;
    Spam selectedSpam;

    String query = "";
    String queryOrder = "";

    public AllSpams() {
        initComponents();
        selectedSpam = new Spam();
        spamService = new SpamService();
        selectedSpam = new Spam();
        updateTable();
        searchUsername();
        tableClickHandle();
    }

    public void updateTable() {
        userList = spamService.getAllSpam();
        tableModel = (DefaultTableModel) tableSpams.getModel();
        tableModel.setRowCount(0);
        for (Object[] row : userList) {
            tableModel.addRow(row);
        }
    }

    public void tableClickHandle() {
        tableSpams.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableSpams.getSelectedRow();
                if (selectedRow != -1) {
                    for (int i = 0; i < tableSpams.getColumnCount(); i++) {
                        switch (i) {
                            case 0:
                                selectedSpam.setReportId(Integer.parseInt(tableSpams.getValueAt(selectedRow, i).toString()));
                                break;
                            case 1:
                                selectedSpam.setSpamUsername(tableSpams.getValueAt(selectedRow, i).toString());
                                break;
                            case 2:
                                String dateString = tableSpams.getValueAt(selectedRow, i).toString();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    Date parsedDate = Date.valueOf(dateString);
                                    selectedSpam.setSpamTime(parsedDate);
                                } catch (IllegalArgumentException ex) {
                                    // Handle incorrect date format or parsing issues
                                    Logger.getLogger(AllUsers.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            case 3:
                                selectedSpam.setSpamName(tableSpams.getValueAt(selectedRow, i).toString());
                                break;
                            case 4:
                                Boolean blocked = Boolean.parseBoolean(tableSpams.getValueAt(selectedRow, i).toString());
                                selectedSpam.setBlocked(blocked);
                                spamService.blockUser(selectedSpam);
                                updateTable();
                            default:
                                break;
                        }
                    }
                    System.out.println();
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        backgroundSpams = new javax.swing.JPanel();
        titleSpams = new javax.swing.JLabel();
        spamScrollPane = new javax.swing.JScrollPane();
        tableSpams = new com.btv.Admin.gui.components.TableCustom();
        controlSectionSpam = new javax.swing.JPanel();
        SpamsFilter = new com.btv.Admin.gui.components.ComboboxCustom();
        comboBoxOrderSpams = new com.btv.Admin.gui.components.ComboboxCustom();
        jLabel2 = new javax.swing.JLabel();
        SpamsSort = new com.btv.Admin.gui.components.ComboboxCustom();
        jLabel3 = new javax.swing.JLabel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        fromDate = new com.toedter.calendar.JDateChooser();
        labelDate = new javax.swing.JLabel();
        toDate = new com.toedter.calendar.JDateChooser();
        searchField = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setOpaque(false);

        backgroundSpams.setBackground(new java.awt.Color(237, 237, 237));
        backgroundSpams.setOpaque(false);
        backgroundSpams.setPreferredSize(new java.awt.Dimension(1080, 768));

        titleSpams.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        titleSpams.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleSpams.setText("SPAMS");

        tableSpams.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Time report", "Report user", "Lock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spamScrollPane.setViewportView(tableSpams);

        controlSectionSpam.setOpaque(false);

        SpamsFilter.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Username", "Time report" }));
        SpamsFilter.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        SpamsFilter.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpamsFilterItemStateChanged(evt);
            }
        });
        SpamsFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpamsFilterActionPerformed(evt);
            }
        });

        comboBoxOrderSpams.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ASC", "DESC" }));
        comboBoxOrderSpams.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        comboBoxOrderSpams.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxOrderSpamsItemStateChanged(evt);
            }
        });
        comboBoxOrderSpams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxOrderSpamsActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel2.setText("Filtered by");

        SpamsSort.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "None", "Time report", "Username" }));
        SpamsSort.setToolTipText("");
        SpamsSort.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        SpamsSort.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SpamsSortItemStateChanged(evt);
            }
        });
        SpamsSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SpamsSortActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        jLabel3.setText("Sorted by");

        fromDate.setDateFormatString("dd-MM-yyyy");
        fromDate.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        fromDate.setPreferredSize(new java.awt.Dimension(200, 30));
        fromDate.setVisible(false);

        labelDate.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        labelDate.setText("-");
        labelDate.setVisible(false);

        toDate.setDateFormatString("dd-MM-yyyy");
        toDate.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        toDate.setPreferredSize(new java.awt.Dimension(200, 30));
        toDate.setVisible(false);

        searchField.setFont(new java.awt.Font("Calibri", 0, 14)); // NOI18N
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.setVisible(false);

        filterButton.setBackground(new java.awt.Color(48, 162, 255));
        filterButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        filterButton.setForeground(new java.awt.Color(255, 255, 255));
        filterButton.setText("Filter");
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlSectionSpamLayout = new javax.swing.GroupLayout(controlSectionSpam);
        controlSectionSpam.setLayout(controlSectionSpamLayout);
        controlSectionSpamLayout.setHorizontalGroup(
            controlSectionSpamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlSectionSpamLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SpamsFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxOrderSpams, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SpamsSort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fromDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toDate, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton)
                .addContainerGap())
            .addGroup(controlSectionSpamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(filler2, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE))
        );
        controlSectionSpamLayout.setVerticalGroup(
            controlSectionSpamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlSectionSpamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlSectionSpamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlSectionSpamLayout.createSequentialGroup()
                        .addComponent(labelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(SpamsSort, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboBoxOrderSpams, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SpamsFilter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(fromDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(toDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filterButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(controlSectionSpamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(controlSectionSpamLayout.createSequentialGroup()
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 46, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout backgroundSpamsLayout = new javax.swing.GroupLayout(backgroundSpams);
        backgroundSpams.setLayout(backgroundSpamsLayout);
        backgroundSpamsLayout.setHorizontalGroup(
            backgroundSpamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundSpamsLayout.createSequentialGroup()
                .addGroup(backgroundSpamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundSpamsLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(backgroundSpamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(controlSectionSpam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1002, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(backgroundSpamsLayout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(titleSpams, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundSpamsLayout.setVerticalGroup(
            backgroundSpamsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundSpamsLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(titleSpams, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(controlSectionSpam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(spamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(backgroundSpams, javax.swing.GroupLayout.PREFERRED_SIZE, 1082, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundSpams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxOrderSpamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxOrderSpamsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxOrderSpamsActionPerformed

    private void comboBoxOrderSpamsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxOrderSpamsItemStateChanged
        query = SpamsFilter.getSelectedItem().toString();
        queryOrder = comboBoxOrderSpams.getSelectedItem().toString();

        spamService.sortByField(tableSpams, query, queryOrder, searchField.getText());
    }//GEN-LAST:event_comboBoxOrderSpamsItemStateChanged

    private void SpamsFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpamsFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SpamsFilterActionPerformed

    private void SpamsFilterItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpamsFilterItemStateChanged
        query = SpamsFilter.getSelectedItem().toString();
        queryOrder = comboBoxOrderSpams.getSelectedItem().toString();

        spamService.sortByField(tableSpams, query, queryOrder, searchField.getText());
    }//GEN-LAST:event_SpamsFilterItemStateChanged

    private void SpamsSortItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SpamsSortItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_SpamsSortItemStateChanged

    private void SpamsSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SpamsSortActionPerformed
        String optionChosen = SpamsSort.getSelectedItem().toString();
        if (optionChosen.equals("None")) {
            fromDate.setVisible(false);
            toDate.setVisible(false);
            labelDate.setVisible(false);
            searchField.setVisible(false);
            searchField.setText("");
            // Show all data
        } else if (optionChosen.equals("Username")) {
            fromDate.setVisible(false);
            toDate.setVisible(false);
            labelDate.setVisible(false);
            searchField.setVisible(true);
        } else {
            fromDate.setVisible(true);
            toDate.setVisible(true);
            labelDate.setVisible(true);
            searchField.setVisible(false);
            searchField.setText("");
        }
//        spamService.filterByDate(tableSpams, "", optionChosen);
    }//GEN-LAST:event_SpamsSortActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed


    }//GEN-LAST:event_searchFieldActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        String optionChosen = SpamsSort.getSelectedItem().toString();
        if (optionChosen.equals("None")) {
            updateTable();
        } else if (optionChosen.equals("Username")) {
            spamService.sortByField(tableSpams, query, queryOrder, searchField.getText());
        } else {
            spamService.filterByDate(tableSpams, fromDate.getDate(), toDate.getDate());
        }
    }//GEN-LAST:event_filterButtonActionPerformed

    public void searchUsername() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                spamService.sortByField(tableSpams, query, queryOrder, searchField.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                spamService.sortByField(tableSpams, query, queryOrder, searchField.getText());
            }

            public void insertUpdate(DocumentEvent e) {
                spamService.sortByField(tableSpams, query, queryOrder, searchField.getText());
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.btv.Admin.gui.components.ComboboxCustom SpamsFilter;
    private com.btv.Admin.gui.components.ComboboxCustom SpamsSort;
    private javax.swing.JPanel backgroundSpams;
    private com.btv.Admin.gui.components.ComboboxCustom comboBoxOrderSpams;
    private javax.swing.JPanel controlSectionSpam;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JButton filterButton;
    private com.toedter.calendar.JDateChooser fromDate;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelDate;
    private javax.swing.JTextField searchField;
    private javax.swing.JScrollPane spamScrollPane;
    private com.btv.Admin.gui.components.TableCustom tableSpams;
    private javax.swing.JLabel titleSpams;
    private com.toedter.calendar.JDateChooser toDate;
    // End of variables declaration//GEN-END:variables
}