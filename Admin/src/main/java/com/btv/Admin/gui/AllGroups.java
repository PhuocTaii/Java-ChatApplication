/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.gui;

import com.btv.Admin.model.Group;
import com.btv.Admin.service.GroupService;
import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
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
    Group selectGroup;

    String query = "ID";
    String queryOrder = "ASC";

    public AllGroups() {
        initComponents();
        groupService = new GroupService();
        selectGroup = new Group();
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
        groupService.filterByField(tableGroups, "", "");

        tableClickHandle();
    }

    public void tableClickHandle() {
        tableGroups.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableGroups.getSelectedRow();
                if (selectedRow != -1) {
                    for (int i = 0; i < tableGroups.getColumnCount(); i++) {
                        switch (i) {
                            case 0:
                                selectGroup.setId(Integer.parseInt(tableGroups.getValueAt(selectedRow, i).toString()));
                                break;
                            case 1:
                                selectGroup.setGroupName((String) tableGroups.getValueAt(selectedRow, i));
                                break;
                            case 2:
                                String dateString = tableGroups.getValueAt(selectedRow, i).toString();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                try {
                                    // Parsing the date string to verify its correctness
                                    Date parsedDate = Date.valueOf(dateString);
                                    selectGroup.setTimeCreate(parsedDate);

                                } catch (IllegalArgumentException ex) {
                                    // Handle incorrect date format or parsing issues
                                    Logger.getLogger(AllUsers.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    groupChatName.setText(selectGroup.getGroupName());
                    getGroupMember(selectGroup);
                    getGroupAdmin(selectGroup);
                }

            }
        });

    }

    public void getGroupMember(Group group) {
        String[] groupMemberList = groupService.getGroupMember(group);

        DefaultListModel<String> modelMember = new DefaultListModel<>();
        for (String member : groupMemberList) {
            modelMember.addElement(member);
        }

        memberList.setModel(modelMember);
    }

    public void getGroupAdmin(Group group) {
        String[] groupMemberList = groupService.getGroupAdmin(group);

        DefaultListModel<String> modelMember = new DefaultListModel<>();
        for (String member : groupMemberList) {
            modelMember.addElement(member);
        }

        adminList.setModel(modelMember);
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

        jLabel1 = new javax.swing.JLabel();
        loginScollPane = new javax.swing.JScrollPane();
        tableGroups = new com.btv.Admin.gui.components.TableCustom();
        searchField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        groupChatName = new javax.swing.JLabel();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        javax.swing.JLabel jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        memberList = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        adminList = new javax.swing.JList<>();

        setMaximumSize(new java.awt.Dimension(1080, 768));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1080, 768));

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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setMaximumSize(new java.awt.Dimension(32767, 319));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 319));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setText("Group chat: ");

        groupChatName.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        groupChatName.setPreferredSize(new java.awt.Dimension(0, 22));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("Members");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setText("Admins");

        jScrollPane2.setPreferredSize(new java.awt.Dimension(379, 200));

        memberList.setBackground(new java.awt.Color(242, 242, 242));
        memberList.setBorder(null);
        memberList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(memberList);

        jScrollPane4.setPreferredSize(new java.awt.Dimension(379, 200));

        adminList.setBackground(new java.awt.Color(242, 242, 242));
        adminList.setBorder(null);
        adminList.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jScrollPane4.setViewportView(adminList);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(254, 254, 254))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupChatName, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(groupChatName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginScollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        groupService.filterByGroupName(tableGroups, searchField.getText());
    }//GEN-LAST:event_searchFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> adminList;
    private javax.swing.JLabel groupChatName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane loginScollPane;
    private javax.swing.JList<String> memberList;
    private javax.swing.JTextField searchField;
    private com.btv.Admin.gui.components.TableCustom tableGroups;
    // End of variables declaration//GEN-END:variables
}
