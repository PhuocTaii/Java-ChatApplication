/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.User.gui;

import com.btv.User.gui.components.SearchTableModel;
import com.btv.User.gui.components.TableActionCellEditor;
import com.btv.User.gui.components.TableActionCellRender;
import com.btv.User.gui.interfaces.CustomListener;
import com.btv.User.gui.interfaces.SearchListener;
import com.btv.User.gui.interfaces.SearchUserActionEvent;
import com.btv.User.gui.layouts.Layout;
import com.btv.User.helper.MessageStatus;
import com.btv.User.model.User;
import com.btv.User.service.FriendService;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author tvan
 */
public class Search extends javax.swing.JPanel {
    
    private static Search searchPanelInst = null;
    private Layout mainFrame;

    /**
     * Creates new form Search
     */
    private Search(Layout mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        
        SearchUserActionEvent event = new SearchUserActionEvent() {
            @Override
            public void onChat(int row) {
                SearchTableModel tableModel = (SearchTableModel)tableUserSearch.getModel();
                User user = tableModel.getUser(row);
                CustomListener.getInstance().getChatListener().loadChatUI(user.getId(), user.getUsername());
                CustomListener.getInstance().getMenuListener().showChatPanel();
            }

            @Override
            public void onBlock(int row) {
                SearchTableModel tableModel = (SearchTableModel)tableUserSearch.getModel();
                int userId = tableModel.getUser(row).getId();
            }

            @Override
            public void onAddFriend(int row) {
                SearchTableModel tableModel = (SearchTableModel)tableUserSearch.getModel();
                int userId = tableModel.getUser(row).getId();
                new FriendService().addFriend(userId);
            }
        };
        
        CustomListener.getInstance().addSearchListener(new SearchListener() {
            @Override
            public void showFoundUsers(ArrayList<User> listUser) {
                SearchTableModel tableModel = new SearchTableModel(listUser);
                tableUserSearch.setModel(tableModel);
                tableUserSearch.getColumnModel().getColumn(2).setCellRenderer(new TableActionCellRender());
                tableUserSearch.getColumnModel().getColumn(2).setCellEditor(new TableActionCellEditor(event));
            }

            @Override
            public void addFriend(MessageStatus res) {
                JOptionPane.showMessageDialog(mainFrame, res.getMessage(), "Add friend", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
    
    public static Search getSearchPanelInst(Layout mainFrame) {
        if(searchPanelInst == null)
            searchPanelInst = new Search(mainFrame);
        return searchPanelInst;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundSearch = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUserSearch = new com.btv.User.gui.components.TableCustom();
        buttonSearchUser = new javax.swing.JButton();
        textFieldSearchMessage = new javax.swing.JTextField();
        comboboxSearch = new com.btv.User.gui.components.ComboboxCustom();
        jLabel1 = new javax.swing.JLabel();
        textFieldSearchName = new javax.swing.JTextField();
        buttonSearchMessage = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        labelChoosenPerson = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        backgroundSearch.setPreferredSize(new java.awt.Dimension(1080, 768));

        tableUserSearch.setModel(new SearchTableModel(new ArrayList<User>()));
        jScrollPane1.setViewportView(tableUserSearch);
        if (tableUserSearch.getColumnModel().getColumnCount() > 0) {
            tableUserSearch.getColumnModel().getColumn(0).setResizable(false);
            tableUserSearch.getColumnModel().getColumn(1).setResizable(false);
            tableUserSearch.getColumnModel().getColumn(2).setResizable(false);
        }

        buttonSearchUser.setBackground(new java.awt.Color(48, 162, 255));
        buttonSearchUser.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonSearchUser.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchUser.setText("Search");
        buttonSearchUser.setAutoscrolls(true);
        buttonSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchUserActionPerformed(evt);
            }
        });

        textFieldSearchMessage.setPreferredSize(new java.awt.Dimension(64, 40));
        textFieldSearchMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchMessageActionPerformed(evt);
            }
        });

        comboboxSearch.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Username", "Name" }));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Search by");

        textFieldSearchName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldSearchNameActionPerformed(evt);
            }
        });

        buttonSearchMessage.setBackground(new java.awt.Color(48, 162, 255));
        buttonSearchMessage.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        buttonSearchMessage.setForeground(new java.awt.Color(255, 255, 255));
        buttonSearchMessage.setText("Search");
        buttonSearchMessage.setBorderPainted(false);
        buttonSearchMessage.setPreferredSize(new java.awt.Dimension(79, 40));
        buttonSearchMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSearchMessageActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Chat with:");

        labelChoosenPerson.setBackground(new java.awt.Color(255, 255, 255));
        labelChoosenPerson.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        labelChoosenPerson.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelChoosenPerson.setText("Binh");
        labelChoosenPerson.setOpaque(true);

        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout backgroundSearchLayout = new javax.swing.GroupLayout(backgroundSearch);
        backgroundSearch.setLayout(backgroundSearchLayout);
        backgroundSearchLayout.setHorizontalGroup(
            backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundSearchLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1068, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundSearchLayout.createSequentialGroup()
                        .addComponent(textFieldSearchMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSearchMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundSearchLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(labelChoosenPerson, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundSearchLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboboxSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textFieldSearchName)
                        .addGap(12, 12, 12)
                        .addComponent(buttonSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        backgroundSearchLayout.setVerticalGroup(
            backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundSearchLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonSearchUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboboxSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addComponent(textFieldSearchName, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textFieldSearchMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSearchMessage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(backgroundSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(labelChoosenPerson, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textFieldSearchMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldSearchMessageActionPerformed

    private void textFieldSearchNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldSearchNameActionPerformed
        // TODO add your handling code here:
        buttonSearchUserActionPerformed(evt);
    }//GEN-LAST:event_textFieldSearchNameActionPerformed

    private void buttonSearchMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchMessageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonSearchMessageActionPerformed

    private void buttonSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSearchUserActionPerformed
        // TODO add your handling code here:
        String optionSearch =  (String)comboboxSearch.getSelectedItem();
        String query = textFieldSearchName.getText();
        if(query.equalsIgnoreCase("")) {
            SearchTableModel tableModel = (SearchTableModel) tableUserSearch.getModel();
            tableModel.clearData();
        }
        else
            new FriendService().searchUsers(optionSearch, query);
    }//GEN-LAST:event_buttonSearchUserActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundSearch;
    private javax.swing.JButton buttonSearchMessage;
    private javax.swing.JButton buttonSearchUser;
    private com.btv.User.gui.components.ComboboxCustom comboboxSearch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelChoosenPerson;
    private com.btv.User.gui.components.TableCustom tableUserSearch;
    private javax.swing.JTextField textFieldSearchMessage;
    private javax.swing.JTextField textFieldSearchName;
    // End of variables declaration//GEN-END:variables
}
