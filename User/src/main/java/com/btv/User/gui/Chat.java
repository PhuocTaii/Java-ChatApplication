/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.User.gui;

import com.btv.User.gui.components.FriendListCellRender;
import com.btv.User.gui.components.Message;
import com.btv.User.gui.interfaces.ChatListener;
import com.btv.User.gui.interfaces.CustomListener;
import com.btv.User.gui.layouts.Layout;
import com.btv.User.service.FriendService;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import com.btv.User.model.User;
import java.util.ArrayList;
import javax.swing.JList;

/**
 *
 * @author tvan
 */
public class Chat extends javax.swing.JPanel {
    private static Chat chatPanelInst = null;
    private Layout mainFrame;

    /**
     * Creates new form Chat
     */
    private Chat(Layout mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        
        JPanel messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "abc", false));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "abc", false));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "abc", false));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.add(new Message("abcabcxyz", "xyz", true));
        messagesPanel.revalidate();

        messagesPanel.setPreferredSize(new Dimension(680, messagesPanel.getPreferredSize().height + 20));

        chatZone.getViewport().add(messagesPanel);
        chatZone.getViewport().revalidate();

        // Set vertical scrollbar policy
        chatZone.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // set cell renderer for JList friendList
        friendList.setCellRenderer(new FriendListCellRender());
        friendList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // set listeners
        CustomListener.getInstance().addChatListener(new ChatListener() {
            @Override
            public void loadListFriend(ArrayList<User> listFriend) {
                DefaultListModel<User> listFriendModel = new DefaultListModel<>();
                for(User friend : listFriend) {
                    listFriendModel.addElement(friend);
                }
                friendList.setModel((ListModel)listFriendModel);
            }
            
            @Override
            public void updateFriendStatus(int friendId, boolean isOnline) {
                DefaultListModel<User> listFriendModel = (DefaultListModel<User>)friendList.getModel();
                for(int i = 0; i < listFriendModel.getSize(); i++) {
                    User currFriend = listFriendModel.getElementAt(i);
                    if(currFriend.getId() == friendId) {
                        currFriend.setIsOnline(isOnline);
                        listFriendModel.setElementAt(currFriend, i);
                        break;
                    }
                }
            }
            
            @Override
            public void unfriend(int friendId) {
                DefaultListModel<User> listFriendModel = (DefaultListModel<User>)friendList.getModel();
                for(int i = 0; i < listFriendModel.getSize(); i++) {
                    User currFriend = listFriendModel.getElementAt(i);
                    if(currFriend.getId() == friendId) {
                        listFriendModel.remove(i);
                        break;
                    }
                }
            }
        });
        
        loadChatPanel();
    }
    
    public static Chat getChatPanelInst(Layout mainFrame) {
        if(chatPanelInst == null)
            chatPanelInst = new Chat(mainFrame);
        return chatPanelInst;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pageHeader = new javax.swing.JPanel();
        reportButton = new javax.swing.JButton();
        encodeButton = new javax.swing.JButton();
        receiver = new java.awt.Label();
        chatZone = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        messageInput = new java.awt.TextField();
        sendButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        friendlist = new javax.swing.JLabel();
        UserGoupChat = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        groupMember = new java.awt.Label();
        AddmemberButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        pageHeader.setBackground(new java.awt.Color(255, 255, 255));
        pageHeader.setPreferredSize(new java.awt.Dimension(172, 40));

        reportButton.setIcon(new ImageIcon(getClass().getResource("/images/warning.png")));
        reportButton.setBorder(null);
        reportButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        reportButton.setFocusPainted(false);
        reportButton.setFocusable(false);

        encodeButton.setIcon(new ImageIcon(getClass().getResource("/images/encode.png"))
        );
        encodeButton.setBorder(null);
        encodeButton.setFocusPainted(false);
        encodeButton.setFocusable(false);

        receiver.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        receiver.setText("NVanA");

        javax.swing.GroupLayout pageHeaderLayout = new javax.swing.GroupLayout(pageHeader);
        pageHeader.setLayout(pageHeaderLayout);
        pageHeaderLayout.setHorizontalGroup(
            pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHeaderLayout.createSequentialGroup()
                .addComponent(receiver, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 642, Short.MAX_VALUE)
                .addComponent(reportButton)
                .addGap(26, 26, 26)
                .addComponent(encodeButton)
                .addGap(24, 24, 24))
        );
        pageHeaderLayout.setVerticalGroup(
            pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(reportButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(encodeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(receiver, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(pageHeader, java.awt.BorderLayout.NORTH);

        chatZone.setMaximumSize(new java.awt.Dimension(788, 32767));
        chatZone.setMinimumSize(new java.awt.Dimension(788, 16));
        chatZone.setPreferredSize(new java.awt.Dimension(788, 600));
        jPanel1.add(chatZone, java.awt.BorderLayout.CENTER);

        messageInput.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        messageInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                messageInputActionPerformed(evt);
            }
        });

        sendButton.setBackground(new java.awt.Color(48, 162, 255));
        sendButton.setText("SEND");
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(messageInput, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sendButton)
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(messageInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.SOUTH);

        add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        friendlist.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        friendlist.setText("LIST FRIENDS");

        UserGoupChat.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        UserGoupChat.setText("GROUP CHAT");

        jButton2.setBackground(new java.awt.Color(48, 162, 255));
        jButton2.setText("Create group chat");
        jButton2.setPreferredSize(new java.awt.Dimension(125, 40));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        friendList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                friendListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(friendList);

        jScrollPane3.setViewportView(groupList);

        jPanel4.setOpaque(false);

        groupMember.setBackground(new java.awt.Color(255, 255, 255));
        groupMember.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        groupMember.setText("MEMBERS");

        AddmemberButton.setBackground(new java.awt.Color(48, 162, 255));
        AddmemberButton.setText("Add member");

        memberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Text", null, null},
                {"Text", null, null},
                {"Text", null, null}
            },
            new String [] {
                "Username", "Admin", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        class ButtonRenderer extends DefaultTableCellRenderer {
            private JButton button;

            public ButtonRenderer() {
                button = new JButton("Remove");
                button.setBackground(new Color(239,149,149));
                button.setOpaque(true);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                return button;
            }
        }

        memberTable.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
        //jTable1.getColumnModel().getColumn(2).setCellEditor(new ButtonEditor(new JCheckBox()));
        jScrollPane2.setViewportView(memberTable);
        if (memberTable.getColumnModel().getColumnCount() > 0) {
            memberTable.getColumnModel().getColumn(0).setResizable(false);
            memberTable.getColumnModel().getColumn(1).setResizable(false);
            memberTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(groupMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AddmemberButton)))
                .addGap(0, 0, 0))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(groupMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(AddmemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserGoupChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(friendlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendlist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserGoupChat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sendButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void messageInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_messageInputActionPerformed

    private void friendListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_friendListValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {
            User selectedFriend = friendList.getSelectedValue();
            int selectedIndex = friendList.getSelectedIndex();
            if (selectedFriend != null) {
                Rectangle bounds = friendList.getCellBounds(selectedIndex, selectedIndex);
                if (bounds != null) {
                    showFriendMenu(selectedIndex, selectedFriend, friendList, bounds.x, bounds.y + bounds.height);
                }
            }
            
            friendList.clearSelection();
        }
    }//GEN-LAST:event_friendListValueChanged

    private void showFriendMenu(int selectedIndex, User selectedFriend, Component component, int x, int y) {
        JPopupMenu friendMenu = new JPopupMenu();

        JMenuItem chatItem = new JMenuItem("Chat");
        JMenuItem unfrItem = new JMenuItem("Unfriend");
        JMenuItem blockItem = new JMenuItem("Block");

        friendMenu.add(chatItem);
        friendMenu.add(unfrItem);
        friendMenu.add(blockItem);
        
        chatItem.addActionListener(e -> handleChatWithFriend());
        unfrItem.addActionListener(e -> handleUnfriend(selectedIndex, selectedFriend.getId(), selectedFriend.getUsername()));
        blockItem.addActionListener(e -> handleBlockFriend());

        friendMenu.show(component, x, y);
    }
    
    public void handleChatWithFriend() {
    }
    
    public void handleUnfriend(int selectedIdx, int friendId, String friendName) {
        Object[] options = { "YES", "NO" };
        int selectedOption = JOptionPane.showOptionDialog(mainFrame, "Sure you want to unfriend " + friendName, "Confirm unfriend", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        if(selectedOption == 0) {
            FriendService friendService = new FriendService();
            friendService.unfriend(friendId);
        }
    }
    
    public void handleBlockFriend() {
    }
    
    public JList<User> getFriendList() {
        return this.friendList;
    }
    
    public void loadChatPanel() {
        FriendService friendService = new FriendService();
        friendService.getListFriends();
        
        this.revalidate();
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddmemberButton;
    private javax.swing.JLabel UserGoupChat;
    private javax.swing.JScrollPane chatZone;
    private javax.swing.JButton encodeButton;
    private javax.swing.JList<User> friendList;
    private javax.swing.JLabel friendlist;
    private javax.swing.JList<String> groupList;
    private java.awt.Label groupMember;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable memberTable;
    private java.awt.TextField messageInput;
    private javax.swing.JPanel pageHeader;
    private java.awt.Label receiver;
    private javax.swing.JButton reportButton;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
