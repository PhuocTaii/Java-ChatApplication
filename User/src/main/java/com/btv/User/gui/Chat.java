/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.User.gui;

import com.btv.User.gui.components.FriendListCellRender;
import com.btv.User.gui.components.GroupListCellRenderer;
import com.btv.User.gui.components.MemberTableModel;
import com.btv.User.gui.components.Message;
import com.btv.User.gui.components.RemoveMemCellEditor;
import com.btv.User.gui.components.RemoveMemCellRenderer;
import com.btv.User.gui.interfaces.ChatListener;
import com.btv.User.gui.interfaces.CustomListener;
import com.btv.User.gui.interfaces.GroupMemActionEvent;
import com.btv.User.gui.layouts.Layout;
import com.btv.User.helper.MessageStatus;
import com.btv.User.model.ChatMessage;
import com.btv.User.model.Group;
import com.btv.User.model.Member;
import com.btv.User.service.UserService;
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
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import com.btv.User.model.User;
import com.btv.User.service.ChatService;
import com.btv.User.service.GroupService;
import java.util.ArrayList;
import javax.swing.JList;

/**
 *
 * @author tvan
 */
public class Chat extends javax.swing.JPanel {
    private static Chat chatPanelInst = null;
    private Layout mainFrame;
    private int receiverId;
    private boolean isGroup;
    private JPanel messagesPanel;
    private ArrayList<Integer> highlightGroups;

    /**
     * Creates new form Chat
     */
    private Chat(Layout mainFrame) {
        initComponents();
        this.mainFrame = mainFrame;
        
        highlightGroups = new ArrayList<>();
        
        // Set vertical scrollbar policy
        chatZoneScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        // set cell renderer for JList friendList
        friendList.setCellRenderer(new FriendListCellRender());
        
        // set cell renderer for JList groupList
        groupList.setCellRenderer(new GroupListCellRenderer());
        
        groupInfoPanel.setVisible(false);
        
        GroupMemActionEvent groupMemActionEvent = new GroupMemActionEvent() {
            @Override
            public void removeMem(int row) {
                MemberTableModel tableModel = (MemberTableModel)memberTable.getModel();
                Member member = tableModel.getMember(row);
                int groupId = receiverId;
                int userId = member.getId();
                System.out.println(groupId);
                System.out.println(userId);
                GroupService.removeMember(groupId, userId);
            }
        };
        
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

            @Override
            public void loadChatUI(int id, String name, boolean isGroup) {
                Chat.getChatPanelInst(null).setReceiverId(id);
                receiverLabel.setText(name);
                
                Chat.getChatPanelInst(null).setIsGroup(isGroup);
                groupInfoPanel.setVisible(isGroup);
        
                messagesPanel = new JPanel();
                messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
                
                chatZoneScroll.getViewport().add(messagesPanel);
            }

            @Override
            public void loadChatData(ArrayList<ChatMessage> listChat) {
                for(ChatMessage chat : listChat) {
                    addMessageToChatZone(chat);
                }
            }

            @Override
            public void reportNoti(MessageStatus res) {
                JOptionPane.showMessageDialog(mainFrame, res.getMessage(), "Report notification", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void blockNoti(MessageStatus res) {
                JOptionPane.showMessageDialog(mainFrame, res.getMessage(), "Block notification", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void loadListGroup(ArrayList<Group> listGroup) {
                DefaultListModel<Group> listGroupModel = new DefaultListModel<>();
                for(Group group : listGroup) {
                    if(highlightGroups.contains(group.getId()))
                        group.setIsSeen(false);
                    listGroupModel.addElement(group);
                }
                groupList.setModel((ListModel)listGroupModel);
            }

            @Override
            public void loadListMember(ArrayList<Member> listMem, boolean isAdmin) {
                MemberTableModel tableModel = new MemberTableModel(listMem);
                memberTable.setModel(tableModel);
                // set cell renderer for JTable memberTable
                memberTable.getColumnModel().getColumn(2).setCellRenderer(new RemoveMemCellRenderer(isAdmin));
                memberTable.getColumnModel().getColumn(2).setCellEditor(new RemoveMemCellEditor(groupMemActionEvent, isAdmin));
            }
            
            public void updateGroupName(int groupId, String newName){
                DefaultListModel<Group> listGroupModel = (DefaultListModel<Group>)groupList.getModel();
                for(int i = 0; i < listGroupModel.getSize(); i++){
                    Group currGroup = listGroupModel.getElementAt(i);
                    if(currGroup.getId() == groupId){
                        currGroup.setName(newName);
                        groupList.updateUI();
                        receiverLabel.setText(newName);
                    }
                }
            }
            
            public void addGroupMember(ArrayList<Member> listMem, boolean isAdmin){
                MemberTableModel tableModel = new MemberTableModel(listMem);
                memberTable.setModel(tableModel);
                // set cell renderer for JTable memberTable
                memberTable.getColumnModel().getColumn(2).setCellRenderer(new RemoveMemCellRenderer(isAdmin));
                memberTable.getColumnModel().getColumn(2).setCellEditor(new RemoveMemCellEditor(groupMemActionEvent, isAdmin));
            }
            
            public void assignAdmin(ArrayList<Member> listMem, boolean isAdmin){
                MemberTableModel tableModel = new MemberTableModel(listMem);
                memberTable.setModel(tableModel);
                // set cell renderer for JTable memberTable
                memberTable.getColumnModel().getColumn(2).setCellRenderer(new RemoveMemCellRenderer(isAdmin));
                memberTable.getColumnModel().getColumn(2).setCellEditor(new RemoveMemCellEditor(groupMemActionEvent, isAdmin));
            }
            
            public void removeMember(ArrayList<Member> listMem, boolean isAdmin){
                MemberTableModel tableModel = new MemberTableModel(listMem);
                memberTable.setModel(tableModel);
                // set cell renderer for JTable memberTable
                memberTable.getColumnModel().getColumn(2).setCellRenderer(new RemoveMemCellRenderer(isAdmin));
                memberTable.getColumnModel().getColumn(2).setCellEditor(new RemoveMemCellEditor(groupMemActionEvent, isAdmin));
            }

            @Override
            public void addNewGroupChat(Group gr) {
                DefaultListModel<Group> listGroupModel = (DefaultListModel<Group>)groupList.getModel();
                listGroupModel.addElement(gr);
            }

            @Override
            public void newMessGroupCome(ChatMessage mess, int groupId) {
                if(isGroup && groupId == receiverId) {
                    addMessageToChatZone(mess);
                }
                else {
                    highlightSeenChatGroup(groupId, false);
                }
                if(!Chat.getChatPanelInst(mainFrame).isVisible()) {
                    mainFrame.highlightChatIcon();
                }
            }
        });
        
        loadPanel();
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

        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        pageHeader = new javax.swing.JPanel();
        downButton = new javax.swing.JButton();
        receiverLabel = new javax.swing.JLabel();
        chatZoneScroll = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        messageInput = new java.awt.TextField();
        sendButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        javax.swing.JLabel friendlist = new javax.swing.JLabel();
        javax.swing.JLabel UserGoupChat = new javax.swing.JLabel();
        createGroupBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        groupList = new javax.swing.JList<>();
        groupInfoPanel = new javax.swing.JPanel();
        java.awt.Label groupMember = new java.awt.Label();
        addMemberButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.BorderLayout());

        pageHeader.setBackground(new java.awt.Color(255, 255, 255));
        pageHeader.setPreferredSize(new java.awt.Dimension(172, 40));

        downButton.setIcon(new ImageIcon(getClass().getResource("/images/down.png"))
        );
        downButton.setBorder(null);
        downButton.setFocusPainted(false);
        downButton.setFocusable(false);
        downButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                downButtonMouseClicked(evt);
            }
        });
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        receiverLabel.setBackground(new java.awt.Color(255, 255, 255));
        receiverLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        receiverLabel.setOpaque(true);

        javax.swing.GroupLayout pageHeaderLayout = new javax.swing.GroupLayout(pageHeader);
        pageHeader.setLayout(pageHeaderLayout);
        pageHeaderLayout.setHorizontalGroup(
            pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pageHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(receiverLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 297, Short.MAX_VALUE)
                .addComponent(downButton)
                .addGap(10, 10, 10))
        );
        pageHeaderLayout.setVerticalGroup(
            pageHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(downButton, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(receiverLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(pageHeader, java.awt.BorderLayout.NORTH);

        chatZoneScroll.setMaximumSize(new java.awt.Dimension(788, 32767));
        chatZoneScroll.setMinimumSize(new java.awt.Dimension(788, 16));
        chatZoneScroll.setPreferredSize(new java.awt.Dimension(788, 600));
        jPanel1.add(chatZoneScroll, java.awt.BorderLayout.CENTER);

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

        createGroupBtn.setBackground(new java.awt.Color(48, 162, 255));
        createGroupBtn.setText("Create group chat");
        createGroupBtn.setPreferredSize(new java.awt.Dimension(125, 40));
        createGroupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupBtnActionPerformed(evt);
            }
        });

        friendList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        friendList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                friendListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(friendList);

        groupList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        groupList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                groupListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(groupList);

        groupInfoPanel.setOpaque(false);

        groupMember.setBackground(new java.awt.Color(255, 255, 255));
        groupMember.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        groupMember.setText("MEMBERS");

        addMemberButton.setBackground(new java.awt.Color(48, 162, 255));
        addMemberButton.setText("Add member");
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberButtonActionPerformed(evt);
            }
        });

        memberTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Admin", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        memberTable.setColumnSelectionAllowed(true);
        memberTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                memberTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(memberTable);
        memberTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (memberTable.getColumnModel().getColumnCount() > 0) {
            memberTable.getColumnModel().getColumn(0).setResizable(false);
            memberTable.getColumnModel().getColumn(1).setResizable(false);
            memberTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout groupInfoPanelLayout = new javax.swing.GroupLayout(groupInfoPanel);
        groupInfoPanel.setLayout(groupInfoPanelLayout);
        groupInfoPanelLayout.setHorizontalGroup(
            groupInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupInfoPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(groupInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(groupInfoPanelLayout.createSequentialGroup()
                        .addComponent(groupMember, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMemberButton)))
                .addGap(0, 0, 0))
        );
        groupInfoPanelLayout.setVerticalGroup(
            groupInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(groupInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(groupInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(groupMember, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(groupInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(createGroupBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UserGoupChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(friendlist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(groupInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendlist)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserGoupChat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createGroupBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.EAST);
    }// </editor-fold>//GEN-END:initComponents

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtonActionPerformed
        // TODO add your handling code here:
        sendMessage();
    }//GEN-LAST:event_sendButtonActionPerformed

    private void createGroupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupBtnActionPerformed
        // TODO add your handling code here:
        new CreateGroupDlg(mainFrame, true);
    }//GEN-LAST:event_createGroupBtnActionPerformed

    private void messageInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_messageInputActionPerformed
        // TODO add your handling code here:
        sendMessage();
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

    private void downButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_downButtonMouseClicked
        // TODO add your handling code here:
        if(getCurrentNameChat().equals("")) return;
        
        JPopupMenu chatSettingMenu = new JPopupMenu();
        
        if(!isGroup) {
            JMenuItem reportItem = new JMenuItem("Report");
            JMenuItem clearChatItem = new JMenuItem("Clear chat history");

            chatSettingMenu.add(reportItem);
            chatSettingMenu.add(clearChatItem);

            reportItem.addActionListener(e -> handleReportUser());
            clearChatItem.addActionListener(e -> handleClearChatHistory());
        }
        else {
            JMenuItem renameItem = new JMenuItem("Rename");
            JMenuItem encodeItem = new JMenuItem("Encode");

            chatSettingMenu.add(renameItem);
            chatSettingMenu.add(encodeItem);

            renameItem.addActionListener(e -> handleRenameGroupChat());
            encodeItem.addActionListener(e -> handleEncodeGroupChat());
        }
        
        chatSettingMenu.show(downButton, evt.getX() , evt.getY());
    }//GEN-LAST:event_downButtonMouseClicked

    private void groupListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_groupListValueChanged
        // TODO add your handling code here:
        if (!evt.getValueIsAdjusting()) {
            Group selectedGroup = groupList.getSelectedValue();
            if (selectedGroup != null && (!isGroup || receiverId != selectedGroup.getId())) {
                CustomListener.getInstance().getChatListener().loadChatUI(selectedGroup.getId(), selectedGroup.getName(), true);
                ChatService.getChatGroupHistory(selectedGroup.getId());
                highlightSeenChatGroup(selectedGroup.getId(), true);
                GroupService.getMembers(selectedGroup.getId());
            }
            
            groupList.clearSelection();
        }
    }//GEN-LAST:event_groupListValueChanged

    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberButtonActionPerformed
        // TODO add your handling code here:
        String username = JOptionPane.showInputDialog(mainFrame, 
            "Please enter member's username: ", 
            "Add member",
            JOptionPane.QUESTION_MESSAGE);
        if(username == null || "".equals(username)) {
            JOptionPane.showMessageDialog(mainFrame, "Please provide username to add member", "Add member warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        MemberTableModel tableModel = (MemberTableModel)memberTable.getModel();
        for(int i = 0; i < tableModel.getRowCount(); i++) {
            Member currMem = tableModel.getMember(i);
            System.out.println(currMem.getUsername());
            if(currMem.getUsername().equalsIgnoreCase(username)) {
                JOptionPane.showMessageDialog(mainFrame, "This member already exists in the group chat", "Add member warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        // call service here
        GroupService.addMember(receiverId, username);
    }//GEN-LAST:event_addMemberButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_downButtonActionPerformed

    private void memberTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memberTableMouseClicked
        // TODO add your handling code here:
        int row = memberTable.rowAtPoint(evt.getPoint());
        int col = memberTable.columnAtPoint(evt.getPoint());
        // Xử lý sự kiện khi nhấp vào ô cụ thể
        if (row >= 0 && col == 1) {
            System.out.println("Clicked on Row: " + row + ", Column: " + col);
            // Thêm mã xử lý tại đây
            MemberTableModel model = (MemberTableModel)memberTable.getModel();
            Member mem = model.getMember(row);
            int user_id = mem.getId();
            Boolean admin = (Boolean)model.getValueAt(row, col);
//            System.out.println(;
//            System.out.println(mem.getId());
            GroupService.setAdmin(receiverId, user_id, admin);
        }
    }//GEN-LAST:event_memberTableMouseClicked
    
    public void handleReportUser() {        
        Object[] options = { "YES", "NO" };
        int selectedOption = JOptionPane.showOptionDialog(mainFrame, "Sure you want to report " + getCurrentNameChat(), "Confirm report", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        if(selectedOption == 0) {
            UserService.reportUser(receiverId);
        }
    }
    
    public void handleClearChatHistory() {
        Object[] options = { "YES", "NO" };
        int selectedOption = JOptionPane.showOptionDialog(mainFrame, "Sure you want to clear chat history with " + getCurrentNameChat(), "Confirm clear history", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        if(selectedOption == 0) {
            // call service here
            //
        }
    }
    
    public void handleRenameGroupChat() {
        String newName = JOptionPane.showInputDialog(mainFrame, 
            "New group's name: ", 
            "Rename group chat",
            JOptionPane.QUESTION_MESSAGE);
        if(newName == null || "".equals(newName) || getCurrentNameChat().equals(newName)) {
            JOptionPane.showMessageDialog(mainFrame, "Please provide new name", "Rename group chat warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        GroupService.renameChatGroup(receiverId, newName);
    }
    
    public void handleEncodeGroupChat() {
    }
    
    public void showFriendMenu(int selectedIndex, User selectedFriend, Component component, int x, int y) {
        JPopupMenu friendMenu = new JPopupMenu();

        JMenuItem chatItem = new JMenuItem("Chat");
        JMenuItem unfrItem = new JMenuItem("Unfriend");
        JMenuItem blockItem = new JMenuItem("Block");

        friendMenu.add(chatItem);
        friendMenu.add(unfrItem);
        friendMenu.add(blockItem);
        
        chatItem.addActionListener(e -> handleChatWithFriend(selectedFriend.getId(), selectedFriend.getUsername()));
        unfrItem.addActionListener(e -> handleUnfriend(selectedIndex, selectedFriend.getId(), selectedFriend.getUsername()));
        blockItem.addActionListener(e -> handleBlockFriend(selectedFriend.getId(), selectedFriend.getUsername()));

        friendMenu.show(component, x, y);
    }
    
    public void handleChatWithFriend(int friendId, String friendName) {
        if(isGroup || friendId != receiverId) {
            CustomListener.getInstance().getChatListener().loadChatUI(friendId, friendName, false);
            ChatService.getChatUserHistory(friendId);
        }
    }
    
    public void handleUnfriend(int selectedIdx, int friendId, String friendName) {
        Object[] options = { "YES", "NO" };
        int selectedOption = JOptionPane.showOptionDialog(mainFrame, "Sure you want to unfriend " + friendName, "Confirm unfriend", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        if(selectedOption == 0) {
            UserService friendService = new UserService();
            friendService.unfriend(friendId);
        }
    }
    
    public void handleBlockFriend(int friendId, String friendName) {
        Object[] options = { "YES", "NO" };
        int selectedOption = JOptionPane.showOptionDialog(mainFrame, "Sure you want to block " + friendName, "Confirm block", 
                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        if(selectedOption == 0) {
            UserService.blockUser(friendId);
        }
    }
    
    public JList<User> getFriendList() {
        return this.friendList;
    }
    
    public void loadPanel() {
        UserService.getListFriends();
        GroupService.getListGroups();
        
        this.revalidate();
        this.repaint();
    }
    
    public void addMessageToChatZone(ChatMessage mess) {
        Message newMess = new Message(messagesPanel, mess);
        
        messagesPanel.add(newMess);
        messagesPanel.revalidate();
        messagesPanel.repaint();
        
        messagesPanel.setPreferredSize(new Dimension(messagesPanel.getPreferredSize().width, messagesPanel.getPreferredSize().height + 70));
    }
    
    public void highlightSeenChatGroup(int groupId, boolean seen) {
        if(seen)
            highlightGroups.remove(Integer.valueOf(groupId));
        else
            highlightGroups.add(groupId);
        DefaultListModel<Group> listGroupModel = (DefaultListModel<Group>)groupList.getModel();
        for(int i = 0; i < listGroupModel.getSize(); i++){
            Group currGroup = listGroupModel.getElementAt(i);
            if(currGroup.getId() == groupId){
                currGroup.setIsSeen(seen);
                break;
            }
        }
        groupList.repaint();
    }
    
    public void sendMessage() {
        String content = messageInput.getText();
        if(isGroup) {
            ChatService.chatGroup(receiverId, content);
            ChatMessage mess = new ChatMessage();
            mess.setContent(content);
            mess.setSendName("You");
            mess.setIsMine(true);
            addMessageToChatZone(mess);
        }
        messageInput.setText("");
    }
    
    public String getCurrentNameChat() {
        return receiverLabel.getText();
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMemberButton;
    private javax.swing.JScrollPane chatZoneScroll;
    private javax.swing.JButton createGroupBtn;
    private javax.swing.JButton downButton;
    private javax.swing.JList<User> friendList;
    private javax.swing.JPanel groupInfoPanel;
    private javax.swing.JList<Group> groupList;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable memberTable;
    private java.awt.TextField messageInput;
    private javax.swing.JPanel pageHeader;
    private javax.swing.JLabel receiverLabel;
    private javax.swing.JButton sendButton;
    // End of variables declaration//GEN-END:variables
}
