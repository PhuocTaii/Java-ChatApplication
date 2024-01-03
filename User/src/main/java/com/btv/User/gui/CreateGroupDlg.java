/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.btv.User.gui;

import com.btv.User.gui.components.MemberTableModel;
import com.btv.User.gui.components.RemoveMemCellEditor;
import com.btv.User.gui.components.RemoveMemCellRenderer;
import com.btv.User.gui.interfaces.CreateGroupListener;
import com.btv.User.gui.interfaces.CustomListener;
import com.btv.User.gui.interfaces.GroupMemActionEvent;
import com.btv.User.helper.MessageStatus;
import com.btv.User.model.Member;
import com.btv.User.service.GroupService;
import com.btv.User.service.UserService;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author tvan
 */
public class CreateGroupDlg extends javax.swing.JDialog {

    /**
     * Creates new form CreateGroupDlg
     */
    private JFrame mainFrame;
    public CreateGroupDlg(JFrame parent, boolean modal) {
        super(parent, modal);
        this.mainFrame = parent;
        initComponents();
        
        addedMemTable.setModel(new MemberTableModel(new ArrayList<>()));
        
        GroupMemActionEvent groupMemActionEvent = new GroupMemActionEvent() {
            @Override
            public void removeMem(int row) {
                MemberTableModel tableModel = (MemberTableModel)addedMemTable.getModel();
                Member member = tableModel.getMember(row);
                tableModel.removeMember(member);
            }
        };
        
        CustomListener.getInstance().addCreateGroupListener(new CreateGroupListener() {
            @Override
            public void addFoundMember(MessageStatus res, Member mem) {
                if(res == MessageStatus.SUCCESS) {
                    MemberTableModel model = (MemberTableModel)addedMemTable.getModel();
                    model.addMember(mem);
                    addedMemTable.getColumnModel().getColumn(2).setCellRenderer(new RemoveMemCellRenderer(true));
                    addedMemTable.getColumnModel().getColumn(2).setCellEditor(new RemoveMemCellEditor(groupMemActionEvent, true));
                }
                else
                    JOptionPane.showMessageDialog(mainFrame, res.getMessage(), "Create group notification", JOptionPane.WARNING_MESSAGE);
            }

            @Override
            public void createGroup(MessageStatus res) {
                if(res == MessageStatus.SUCCESS) {
                    JOptionPane.showMessageDialog(mainFrame, res.getMessage(), "Create group notification", JOptionPane.INFORMATION_MESSAGE);
                    closeDialog();
                }
                else {
                    JOptionPane.showMessageDialog(mainFrame, res.getMessage(), "Create group notification", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        this.setVisible(true);
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
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        groupNameTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel3 = new javax.swing.JLabel();
        memberTextField = new javax.swing.JTextField();
        javax.swing.JLabel jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        addedMemTable = new javax.swing.JTable();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        createBtn = new javax.swing.JButton();

        jButton1.setBackground(new java.awt.Color(48, 162, 255));
        jButton1.setText("CREATE");
        jButton1.setMaximumSize(new java.awt.Dimension(50, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(50, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(50, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Create group chat");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NEW GROUP CHAT");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        getContentPane().add(jLabel1, java.awt.BorderLayout.NORTH);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Group name:");
        jLabel2.setPreferredSize(new java.awt.Dimension(150, 30));

        groupNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        groupNameTextField.setPreferredSize(new java.awt.Dimension(250, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Members (Username):");
        jLabel3.setPreferredSize(new java.awt.Dimension(150, 30));

        memberTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        memberTextField.setPreferredSize(new java.awt.Dimension(250, 30));
        memberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberTextFieldActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Added members:");

        addedMemTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(addedMemTable);
        if (addedMemTable.getColumnModel().getColumnCount() > 0) {
            addedMemTable.getColumnModel().getColumn(0).setResizable(false);
            addedMemTable.getColumnModel().getColumn(1).setResizable(false);
            addedMemTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(groupNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(memberTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addGap(50, 50, 50))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setPreferredSize(new java.awt.Dimension(568, 50));

        createBtn.setBackground(new java.awt.Color(48, 162, 255));
        createBtn.setLabel("CREATE");
        createBtn.setPreferredSize(new java.awt.Dimension(120, 30));
        createBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createBtnActionPerformed(evt);
            }
        });
        jPanel2.add(createBtn);

        getContentPane().add(jPanel2, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createBtnActionPerformed
        // TODO add your handling code here:
        String groupName = groupNameTextField.getText();
        ArrayList<Member> members = ((MemberTableModel)addedMemTable.getModel()).getMemberList();
        if(groupName.equals("") || members.size() < 1) {
            JOptionPane.showMessageDialog(mainFrame, "Your group chat must have a name and at least 1 new member", "Create group notification", JOptionPane.WARNING_MESSAGE);
            return;
        }
//        for(Member mem : members) {
//            System.out.println(mem.getId());
//            System.out.println(mem.getIsAdmin());
//            System.out.println(mem.getUsername());
//        }
//        System.out.println(groupName);
        GroupService.createGroupChat(groupName, members);
    }//GEN-LAST:event_createBtnActionPerformed

    private void memberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberTextFieldActionPerformed
        // TODO add your handling code here:
        String username = memberTextField.getText();
        ArrayList<Member> listAdded = ((MemberTableModel)addedMemTable.getModel()).getMemberList();
        for(Member mem : listAdded) {
            if(username.equals(mem.getUsername()))
                return;
        }
        UserService.findUserByUsername(username);
        memberTextField.setText("");
    }//GEN-LAST:event_memberTextFieldActionPerformed

    public void closeDialog() {
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable addedMemTable;
    private javax.swing.JButton createBtn;
    private javax.swing.JTextField groupNameTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField memberTextField;
    // End of variables declaration//GEN-END:variables
}
