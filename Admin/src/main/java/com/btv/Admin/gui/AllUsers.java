/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.gui;

import com.btv.Admin.model.User;
import com.btv.Admin.service.UserService;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class AllUsers extends javax.swing.JPanel {

    boolean isLocked = false; // temp
    private DefaultTableModel tableModel;
    private String[][] userList;
    private UserService userService;
    private User selectedUser;

    /**
     * Creates new form AllUser
     */
    public AllUsers() {
        initComponents();
        selectedUser = new User();
        userService = new UserService();

        updateTable();
        addUserDialog.setLocationRelativeTo(null);
        tableClickHandle();        
        userService.filterByField(tableUsers, "", "");
    }

    public void tableClickHandle() {
        tableUsers.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tableUsers.getSelectedRow();
                if (selectedRow != -1) {
                    for (int i = 0; i < tableUsers.getColumnCount(); i++) {
                        switch (i) {
                            case 0:
                                selectedUser.setId(Integer.parseInt(tableUsers.getValueAt(selectedRow, i).toString()));
                            case 1:
                                selectedUser.setUsername((String) tableUsers.getValueAt(selectedRow, i));
                                usernameField.setText(tableUsers.getValueAt(selectedRow, i).toString());
                                break;
                            case 2:
                                selectedUser.setName((String) tableUsers.getValueAt(selectedRow, i));
                                nameField.setText(tableUsers.getValueAt(selectedRow, i).toString());
                                break;
                            case 3:
                                selectedUser.setAddress((String) tableUsers.getValueAt(selectedRow, i));

                                addressField.setText(tableUsers.getValueAt(selectedRow, i).toString());
                                break;
                            case 4:
                                String dateString = tableUsers.getValueAt(selectedRow, i).toString();
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                                try {
                                    // Parsing the date string to verify its correctness
                                    Date parsedDate = Date.valueOf(dateString);
                                    selectedUser.setBirthday(parsedDate);

                                    // Setting the date in the JDateChooser
                                    birthChooser.setDate(parsedDate);
                                } catch (IllegalArgumentException ex) {
                                    // Handle incorrect date format or parsing issues
                                    Logger.getLogger(AllUsers.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                break;

                            case 5:

                                String male = tableUsers.getValueAt(selectedRow, i).toString();
                                if (male.equalsIgnoreCase("male")) {
                                    maleRadioButton.setSelected(true);
                                    selectedUser.setGender(true);

                                } else {
                                    femaleRadioButton.setSelected(true);
                                    selectedUser.setGender(false);
                                }
                                break;
                            case 6:
                                selectedUser.setEmail((String) tableUsers.getValueAt(selectedRow, i));

                                emailField.setText(tableUsers.getValueAt(selectedRow, i).toString());
                                break;
                            case 7:
                                String timeCreateString = tableUsers.getValueAt(selectedRow, i).toString();

                                if (!timeCreateString.isEmpty() && !timeCreateString.equals("null")) { // Check for non-empty and non-null values
                                    try {
                                        // Debug print the string content
                                        System.out.println("TimeCreate String: " + timeCreateString);

                                        // Convert the string to a java.sql.Date object
                                        Date timeCreateDate = Date.valueOf(timeCreateString);
                                        selectedUser.setTimeCreate(timeCreateDate);
                                    } catch (IllegalArgumentException ex) {
                                        // Handle incorrect date format or parsing issues
                                        Logger.getLogger(AllUsers.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } else {
                                    // Handle empty or null values in the table cell
                                    // This could be setting a default date or displaying a message
                                    System.out.println("Empty or null value encountered");
                                }
                                break;

                            case 8:
                                selectedUser.setStatus((String) tableUsers.getValueAt(selectedRow, i));
                                String status = selectedUser.getStatus();
                                if (status.equals("LOCKED")) {
                                    isLocked = true;
                                    lockToggleButton.setIcon(new ImageIcon(getClass().getResource("/com/btv/images/lock.png")));
                                } else {
                                    isLocked = false;
                                    lockToggleButton.setIcon(new ImageIcon(getClass().getResource("/com/btv/images/unlock.png")));
                                }
                            case 9:
                                selectedUser.setPassword((String) tableUsers.getValueAt(selectedRow, i));
                                passwordField.setText(tableUsers.getValueAt(selectedRow, i).toString());
                                break;

                            default:
                                break;
                        }
                    }
                    System.out.println(); // New line for next row
                    getLoginListByUser(selectedUser);
                    getFriendListByUser(selectedUser);
                }
            }
        });
    }

    public void updateTable() {
        userList = userService.getAllUsers();
        tableModel = (DefaultTableModel) tableUsers.getModel();
        tableModel.setRowCount(0);
        for (Object[] row : userList) {
            tableModel.addRow(row);
        }
    }

    private void getUserField() {
        selectedUser.setUsername(usernameField.getText());
        selectedUser.setName(nameField.getText());
        selectedUser.setAddress(addressField.getText());
        selectedUser.setBirthday(new Date(birthChooser.getDate().getTime()));

        if (maleRadioButton.isSelected()) {
            selectedUser.setGender(true);
        } else {
            selectedUser.setGender(false);
        }
        selectedUser.setEmail(emailField.getText());
        selectedUser.setPassword(new String(passwordField.getPassword()));

        if (isLocked) {
            selectedUser.setStatus("LOCKED");
        } 
        else if (selectedUser.getStatus().equals("LOCKED")) {
            selectedUser.setStatus("OFFLINE");
        }

    }

    private void clearField() {
        usernameField.setText("");
        nameField.setText("");
        addressField.setText("");
        birthChooser.setDate(null);
        genderBtnGroup.setSelected(null, isLocked);
        emailField.setText("");
        passwordField.setText("");
        messageField.setText("");
        
        isLocked = false;
        lockToggleButton.setIcon(new ImageIcon(getClass().getResource("/com/btv/images/unlock.png")));
        
        loginList.setModel(new DefaultListModel<>());
        friendList.setModel(new DefaultListModel<>());
    }

    private void getLoginListByUser(User user) {
        String[] loginListString = userService.getLoginTime(user);

        DefaultListModel<String> modelLoginList = new DefaultListModel<>();
        for (String login : loginListString) {
            modelLoginList.addElement(login);
        }

        loginList.setModel(modelLoginList);
    }

    private void getFriendListByUser(User user) {
        String[] friendListString = userService.getFriendName(user);

        DefaultListModel<String> modelFriendList = new DefaultListModel<>();
        for (String name : friendListString) {
            modelFriendList.addElement(name);
        }

        friendList.setModel(modelFriendList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genderBtnGroup = new javax.swing.ButtonGroup();
        addUserDialog = new javax.swing.JDialog();
        titleAddDialg = new javax.swing.JLabel();
        javax.swing.JPanel inputPanel = new javax.swing.JPanel();
        javax.swing.JPanel addFieldsPanel = new javax.swing.JPanel();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        javax.swing.JLabel usernameAddLabel = new javax.swing.JLabel();
        usernameAddField = new javax.swing.JTextField();
        javax.swing.Box.Filler filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        javax.swing.JPanel jPanel10 = new javax.swing.JPanel();
        javax.swing.JLabel passwordAddLabel = new javax.swing.JLabel();
        passwordAddField = new javax.swing.JTextField();
        javax.swing.Box.Filler filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        javax.swing.JPanel jPanel5 = new javax.swing.JPanel();
        javax.swing.JLabel emailAddLabel = new javax.swing.JLabel();
        emailAddField = new javax.swing.JTextField();
        javax.swing.Box.Filler filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        javax.swing.JPanel jPanel9 = new javax.swing.JPanel();
        javax.swing.JLabel genderAddLabel = new javax.swing.JLabel();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        maleRadioButtonAdd = new javax.swing.JRadioButton();
        femaleRadioButtonAdd = new javax.swing.JRadioButton();
        javax.swing.Box.Filler filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        javax.swing.JPanel addFieldsPanel1 = new javax.swing.JPanel();
        javax.swing.JPanel jPanel6 = new javax.swing.JPanel();
        javax.swing.JLabel nameAddLabel = new javax.swing.JLabel();
        nameAddField = new javax.swing.JTextField();
        javax.swing.Box.Filler filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        javax.swing.JPanel jPanel7 = new javax.swing.JPanel();
        javax.swing.JLabel addressAddLabel = new javax.swing.JLabel();
        addressAddField = new javax.swing.JTextField();
        javax.swing.Box.Filler filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        jPanel8 = new javax.swing.JPanel();
        javax.swing.JLabel birthAddLabel = new javax.swing.JLabel();
        birthDateChooserAdd = new com.toedter.calendar.JDateChooser();
        javax.swing.Box.Filler filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 5), new java.awt.Dimension(0, 5), new java.awt.Dimension(32767, 5));
        javax.swing.JPanel jPanel3 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        genderAddbuttonGroup = new javax.swing.ButtonGroup();
        javax.swing.JLabel title = new javax.swing.JLabel();
        javax.swing.JPanel controlSection = new javax.swing.JPanel();
        javax.swing.JLabel filterLabel = new javax.swing.JLabel();
        filterOptions = new javax.swing.JComboBox<>();
        filterTextField = new javax.swing.JTextField(16);
        statusOptions = new javax.swing.JComboBox<>();
        javax.swing.Box.Filler filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        addBtn = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableUsers = new com.btv.Admin.gui.components.TableCustom();
        javax.swing.JPanel infoSection = new javax.swing.JPanel();
        javax.swing.JPanel infoFields = new javax.swing.JPanel();
        javax.swing.JPanel usernamePanel = new javax.swing.JPanel();
        javax.swing.JLabel usernameLabel = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        javax.swing.JPanel namePanel = new javax.swing.JPanel();
        javax.swing.JLabel nameLabel = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        javax.swing.JPanel addressPanel = new javax.swing.JPanel();
        javax.swing.JLabel addressLabel = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        javax.swing.JPanel birthPanel = new javax.swing.JPanel();
        javax.swing.JLabel birthLabel = new javax.swing.JLabel();
        birthChooser = new com.toedter.calendar.JDateChooser();
        javax.swing.JPanel genderPanel = new javax.swing.JPanel();
        javax.swing.JLabel genderLabel = new javax.swing.JLabel();
        javax.swing.JPanel genderOptionPanel = new javax.swing.JPanel();
        maleRadioButton = new javax.swing.JRadioButton();
        femaleRadioButton = new javax.swing.JRadioButton();
        javax.swing.JPanel emailPanel = new javax.swing.JPanel();
        javax.swing.JLabel addressLabel1 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        javax.swing.JPanel passwordPanel = new javax.swing.JPanel();
        javax.swing.JLabel passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField();
        javax.swing.JPanel messagePanel = new javax.swing.JPanel();
        javax.swing.JLabel messageLabel1 = new javax.swing.JLabel();
        messageField = new javax.swing.JTextField();
        javax.swing.JPanel actionPanel = new javax.swing.JPanel();
        updateButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        lockToggleButton = new javax.swing.JToggleButton();
        javax.swing.JPanel loginHistoryPanel = new javax.swing.JPanel();
        javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
        loginScrollPane = new javax.swing.JScrollPane();
        loginList = new javax.swing.JList<>();
        javax.swing.JPanel friendsPanel = new javax.swing.JPanel();
        javax.swing.JLabel jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();

        addUserDialog.setTitle("Add New User");
        addUserDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addUserDialog.setMinimumSize(new java.awt.Dimension(860, 295));
        addUserDialog.setModal(true);
        addUserDialog.setResizable(false);
        addUserDialog.setSize(new java.awt.Dimension(900, 370));

        titleAddDialg.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        titleAddDialg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleAddDialg.setText("ADD NEW USER");
        titleAddDialg.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 0, 5, 0));
        titleAddDialg.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addUserDialog.getContentPane().add(titleAddDialg, java.awt.BorderLayout.NORTH);

        inputPanel.setPreferredSize(new java.awt.Dimension(900, 220));
        inputPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 0));

        addFieldsPanel.setMinimumSize(new java.awt.Dimension(300, 183));
        addFieldsPanel.setPreferredSize(new java.awt.Dimension(300, 215));
        addFieldsPanel.setLayout(new javax.swing.BoxLayout(addFieldsPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanel4.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel4.setPreferredSize(new java.awt.Dimension(130, 42));
        jPanel4.setLayout(new java.awt.BorderLayout(0, 2));

        usernameAddLabel.setText("Username");
        jPanel4.add(usernameAddLabel, java.awt.BorderLayout.NORTH);

        usernameAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameAddFieldActionPerformed(evt);
            }
        });
        jPanel4.add(usernameAddField, java.awt.BorderLayout.SOUTH);

        addFieldsPanel.add(jPanel4);
        addFieldsPanel.add(filler9);

        jPanel10.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel10.setLayout(new java.awt.BorderLayout(0, 2));

        passwordAddLabel.setText("Password");
        jPanel10.add(passwordAddLabel, java.awt.BorderLayout.NORTH);

        passwordAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordAddFieldActionPerformed(evt);
            }
        });
        jPanel10.add(passwordAddField, java.awt.BorderLayout.SOUTH);

        addFieldsPanel.add(jPanel10);
        addFieldsPanel.add(filler12);

        jPanel5.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel5.setLayout(new java.awt.BorderLayout(0, 2));

        emailAddLabel.setText("Email");
        jPanel5.add(emailAddLabel, java.awt.BorderLayout.NORTH);

        emailAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailAddFieldActionPerformed(evt);
            }
        });
        jPanel5.add(emailAddField, java.awt.BorderLayout.SOUTH);

        addFieldsPanel.add(jPanel5);
        addFieldsPanel.add(filler10);

        jPanel9.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel9.setLayout(new java.awt.BorderLayout(0, 2));

        genderAddLabel.setText("Gender");
        jPanel9.add(genderAddLabel, java.awt.BorderLayout.NORTH);

        genderAddbuttonGroup.add(maleRadioButtonAdd);
        maleRadioButtonAdd.setSelected(true);
        maleRadioButtonAdd.setText("Male");
        maleRadioButtonAdd.setPreferredSize(new java.awt.Dimension(48, 22));
        maleRadioButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maleRadioButtonAddActionPerformed(evt);
            }
        });

        genderAddbuttonGroup.add(femaleRadioButtonAdd);
        femaleRadioButtonAdd.setText("Female");
        femaleRadioButtonAdd.setPreferredSize(new java.awt.Dimension(98, 22));
        femaleRadioButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleRadioButtonAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(maleRadioButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(femaleRadioButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(femaleRadioButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maleRadioButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.add(jPanel2, java.awt.BorderLayout.CENTER);

        addFieldsPanel.add(jPanel9);
        addFieldsPanel.add(filler11);

        inputPanel.add(addFieldsPanel);

        addFieldsPanel1.setMinimumSize(new java.awt.Dimension(300, 300));
        addFieldsPanel1.setPreferredSize(new java.awt.Dimension(300, 215));
        addFieldsPanel1.setLayout(new javax.swing.BoxLayout(addFieldsPanel1, javax.swing.BoxLayout.Y_AXIS));

        jPanel6.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel6.setLayout(new java.awt.BorderLayout(0, 2));

        nameAddLabel.setText("Full name");
        jPanel6.add(nameAddLabel, java.awt.BorderLayout.NORTH);

        nameAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameAddFieldActionPerformed(evt);
            }
        });
        jPanel6.add(nameAddField, java.awt.BorderLayout.SOUTH);

        addFieldsPanel1.add(jPanel6);
        addFieldsPanel1.add(filler7);

        jPanel7.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel7.setLayout(new java.awt.BorderLayout(0, 2));

        addressAddLabel.setText("Address");
        jPanel7.add(addressAddLabel, java.awt.BorderLayout.PAGE_START);

        addressAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressAddFieldActionPerformed(evt);
            }
        });
        jPanel7.add(addressAddField, java.awt.BorderLayout.SOUTH);

        addFieldsPanel1.add(jPanel7);
        addFieldsPanel1.add(filler6);

        jPanel8.setMaximumSize(new java.awt.Dimension(2147483647, 42));
        jPanel8.setLayout(new java.awt.BorderLayout(0, 2));

        birthAddLabel.setText("Date of birth");
        jPanel8.add(birthAddLabel, java.awt.BorderLayout.CENTER);

        birthDateChooserAdd.setDateFormatString("dd-MM-yyyy");
        jPanel8.add(birthDateChooserAdd, java.awt.BorderLayout.PAGE_END);

        addFieldsPanel1.add(jPanel8);
        addFieldsPanel1.add(filler5);

        inputPanel.add(addFieldsPanel1);

        addUserDialog.getContentPane().add(inputPanel, java.awt.BorderLayout.CENTER);

        jPanel3.setMaximumSize(new java.awt.Dimension(32767, 30));
        jPanel3.setMinimumSize(new java.awt.Dimension(110, 30));
        jPanel3.setPreferredSize(new java.awt.Dimension(500, 80));

        saveButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveButton.setText("SAVE");
        saveButton.setAlignmentY(0.0F);
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        saveButton.setMargin(new java.awt.Insets(2, 5, 3, 5));
        saveButton.setMaximumSize(new java.awt.Dimension(100, 30));
        saveButton.setMinimumSize(new java.awt.Dimension(100, 30));
        saveButton.setPreferredSize(new java.awt.Dimension(100, 30));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(379, 379, 379)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );

        addUserDialog.getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1080, 768));

        title.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("ALL USERS");
        title.setMinimumSize(new java.awt.Dimension(900, 32));

        controlSection.setOpaque(false);

        filterLabel.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        filterLabel.setText("Filter by");

        filterOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Name", "Username", "Status" }));
        filterOptions.setPreferredSize(new java.awt.Dimension(70, 30));
        filterOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterOptionsActionPerformed(evt);
            }
        });

        filterTextField.setMinimumSize(new java.awt.Dimension(64, 30));
        filterTextField.setName(""); // NOI18N
        filterTextField.setPreferredSize(new java.awt.Dimension(64, 30));
        filterTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextFieldActionPerformed(evt);
            }
        });

        statusOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lock", "Online", "Offline" }));
        statusOptions.setPreferredSize(new java.awt.Dimension(72, 30));
        statusOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusOptionsActionPerformed(evt);
            }
        });
        statusOptions.setVisible(false);

        addBtn.setBackground(new java.awt.Color(48, 162, 255));
        addBtn.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setText("Add User");
        addBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addBtn.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        addBtn.setPreferredSize(new java.awt.Dimension(84, 30));
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
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

        javax.swing.GroupLayout controlSectionLayout = new javax.swing.GroupLayout(controlSection);
        controlSection.setLayout(controlSectionLayout);
        controlSectionLayout.setHorizontalGroup(
            controlSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlSectionLayout.createSequentialGroup()
                .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(filterLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        controlSectionLayout.setVerticalGroup(
            controlSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlSectionLayout.createSequentialGroup()
                .addGroup(controlSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(filler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(controlSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterLabel))
                    .addGroup(controlSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        filterTextField.setVisible(false);
        searchButton.setVisible(false);

        tableUsers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Name", "Address", "Date of birth", "Gender", "Email", "Time create", "Status", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableUsers);

        infoSection.setMaximumSize(new java.awt.Dimension(32767, 396));
        infoSection.setOpaque(false);

        infoFields.setMaximumSize(new java.awt.Dimension(32767, 310));
        infoFields.setOpaque(false);
        infoFields.setLayout(new java.awt.GridLayout(8, 1, 0, 10));

        usernamePanel.setLayout(new java.awt.BorderLayout());

        usernameLabel.setText("Username:");
        usernameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        usernamePanel.add(usernameLabel, java.awt.BorderLayout.CENTER);

        usernameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        usernameField.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        usernameField.setPreferredSize(new java.awt.Dimension(200, 20));
        usernamePanel.add(usernameField, java.awt.BorderLayout.EAST);

        infoFields.add(usernamePanel);

        namePanel.setLayout(new java.awt.BorderLayout());

        nameLabel.setText("Name:");
        nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        namePanel.add(nameLabel, java.awt.BorderLayout.CENTER);

        nameField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        nameField.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        nameField.setPreferredSize(new java.awt.Dimension(200, 30));
        namePanel.add(nameField, java.awt.BorderLayout.EAST);

        infoFields.add(namePanel);

        addressPanel.setLayout(new java.awt.BorderLayout());

        addressLabel.setText("Address:");
        addressLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        addressPanel.add(addressLabel, java.awt.BorderLayout.LINE_START);

        addressField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        addressField.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        addressField.setPreferredSize(new java.awt.Dimension(200, 30));
        addressPanel.add(addressField, java.awt.BorderLayout.EAST);

        infoFields.add(addressPanel);

        birthPanel.setLayout(new java.awt.BorderLayout());

        birthLabel.setText("Date of birth:");
        birthLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        birthPanel.add(birthLabel, java.awt.BorderLayout.LINE_START);

        birthChooser.setDateFormatString("dd-MM-yyyy");
        birthChooser.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        birthChooser.setPreferredSize(new java.awt.Dimension(200, 30));
        birthPanel.add(birthChooser, java.awt.BorderLayout.EAST);

        infoFields.add(birthPanel);

        genderPanel.setLayout(new java.awt.BorderLayout());

        genderLabel.setText("Gender:");
        genderLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        genderLabel.setPreferredSize(new java.awt.Dimension(70, 16));
        genderPanel.add(genderLabel, java.awt.BorderLayout.LINE_START);

        genderOptionPanel.setPreferredSize(new java.awt.Dimension(200, 30));
        genderOptionPanel.setLayout(new java.awt.BorderLayout(10, 0));

        genderBtnGroup.add(maleRadioButton);
        maleRadioButton.setText("Male");
        maleRadioButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        genderOptionPanel.add(maleRadioButton, java.awt.BorderLayout.LINE_START);

        genderBtnGroup.add(femaleRadioButton);
        femaleRadioButton.setText("Female");
        femaleRadioButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        femaleRadioButton.setMaximumSize(new java.awt.Dimension(48, 21));
        femaleRadioButton.setMinimumSize(new java.awt.Dimension(48, 21));
        femaleRadioButton.setPreferredSize(new java.awt.Dimension(48, 21));
        genderOptionPanel.add(femaleRadioButton, java.awt.BorderLayout.CENTER);

        genderPanel.add(genderOptionPanel, java.awt.BorderLayout.EAST);

        infoFields.add(genderPanel);

        emailPanel.setLayout(new java.awt.BorderLayout());

        addressLabel1.setText("Email:");
        addressLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        emailPanel.add(addressLabel1, java.awt.BorderLayout.LINE_START);

        emailField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        emailField.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        emailField.setPreferredSize(new java.awt.Dimension(200, 30));
        emailPanel.add(emailField, java.awt.BorderLayout.EAST);

        infoFields.add(emailPanel);

        passwordPanel.setLayout(new java.awt.BorderLayout());

        passwordLabel.setText("Password:");
        passwordLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        passwordPanel.add(passwordLabel, java.awt.BorderLayout.LINE_START);

        passwordField.setPreferredSize(new java.awt.Dimension(200, 30));
        passwordPanel.add(passwordField, java.awt.BorderLayout.EAST);

        infoFields.add(passwordPanel);

        messagePanel.setLayout(new java.awt.BorderLayout());

        messageLabel1.setText("Message:");
        messageLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        messagePanel.add(messageLabel1, java.awt.BorderLayout.CENTER);

        messageField.setEditable(false);
        messageField.setBackground(new java.awt.Color(240, 240, 240));
        messageField.setForeground(new java.awt.Color(255, 51, 51));
        messageField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        messageField.setBorder(null);
        messageField.setMaximumSize(new java.awt.Dimension(2147483647, 30));
        messageField.setPreferredSize(new java.awt.Dimension(200, 30));
        messagePanel.add(messageField, java.awt.BorderLayout.EAST);

        infoFields.add(messagePanel);

        actionPanel.setMaximumSize(new java.awt.Dimension(32767, 310));
        actionPanel.setMinimumSize(new java.awt.Dimension(160, 0));
        actionPanel.setOpaque(false);
        actionPanel.setPreferredSize(new java.awt.Dimension(140, 310));

        updateButton.setBackground(new java.awt.Color(48, 162, 255));
        updateButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateButton.setForeground(new java.awt.Color(255, 255, 255));
        updateButton.setText("UPDATE");
        updateButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateButton.setPreferredSize(new java.awt.Dimension(130, 35));
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        actionPanel.add(updateButton);

        deleteButton.setBackground(new java.awt.Color(239, 149, 149));
        deleteButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("DELETE");
        deleteButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteButton.setPreferredSize(new java.awt.Dimension(130, 35));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        actionPanel.add(deleteButton);

        lockToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/images/unlock.png")));
        lockToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockToggleButtontoggleLockUser(evt);
            }
        });
        actionPanel.add(lockToggleButton);

        loginHistoryPanel.setOpaque(false);
        loginHistoryPanel.setPreferredSize(new java.awt.Dimension(93, 310));
        loginHistoryPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN HISTORY");
        jLabel1.setPreferredSize(new java.awt.Dimension(93, 30));
        loginHistoryPanel.add(jLabel1, java.awt.BorderLayout.NORTH);

        loginList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loginScrollPane.setViewportView(loginList);

        loginHistoryPanel.add(loginScrollPane, java.awt.BorderLayout.CENTER);

        friendsPanel.setOpaque(false);
        friendsPanel.setPreferredSize(new java.awt.Dimension(93, 310));
        friendsPanel.setLayout(new java.awt.BorderLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("LIST FRIENDS");
        jLabel2.setPreferredSize(new java.awt.Dimension(93, 30));
        friendsPanel.add(jLabel2, java.awt.BorderLayout.NORTH);

        friendList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(friendList);

        friendsPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout infoSectionLayout = new javax.swing.GroupLayout(infoSection);
        infoSection.setLayout(infoSectionLayout);
        infoSectionLayout.setHorizontalGroup(
            infoSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoSectionLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(infoFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(actionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginHistoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(friendsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );
        infoSectionLayout.setVerticalGroup(
            infoSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoSectionLayout.createSequentialGroup()
                .addGroup(infoSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoSectionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(friendsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginHistoryPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(actionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(463, 463, 463))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(controlSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(infoSection, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(controlSection, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(infoSection, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void filterOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterOptionsActionPerformed
        // TODO add your handling code here:
        JComboBox cb = (JComboBox) evt.getSource();
        String optionChosen = (String) cb.getSelectedItem();
        if ("None".equals(optionChosen)) {
            filterTextField.setVisible(false);
            statusOptions.setVisible(false);
            searchButton.setVisible(false);
            // Show all data
        } else if ("Status".equals(optionChosen)) {
            filterTextField.setVisible(false);
            statusOptions.setVisible(true);
            searchButton.setVisible(true);
        } else {
            filterTextField.setVisible(true);
            statusOptions.setVisible(false);
            searchButton.setVisible(true);
        }
        userService.filterByField(tableUsers, "", optionChosen);
    }//GEN-LAST:event_filterOptionsActionPerformed

    private void filterTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_filterTextFieldActionPerformed

    private void statusOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusOptionsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusOptionsActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        addUserDialog.setVisible(true);
    }//GEN-LAST:event_addBtnActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        String searchString = filterTextField.getText();
        String searchOption = filterOptions.getSelectedItem().toString();
        if (searchOption.equals("Status")) {
            searchString = statusOptions.getSelectedItem().toString();
        }
        userService.filterByField(tableUsers, searchOption, searchString);
        filterTextField.setText("");
    }//GEN-LAST:event_searchButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
        if (usernameField.getText().equals("")) {
            return;
        }

        int res = JOptionPane.showConfirmDialog(this,
                "Sure you want to update this user?", "Update comfirmation", JOptionPane.YES_NO_OPTION);

        if (res == JOptionPane.YES_OPTION) {
            getUserField();
            String message = userService.validateUser(selectedUser);
//            System.out.println(selectedUser.getStatus());

            if (message.equals("valid")) {
                userService.modifyUser(selectedUser);
                clearField();
                updateTable();
            } else {
                messageField.setText(message);
            }
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if (usernameField.getText().equals("")) {
            return;
        }
        int res = JOptionPane.showConfirmDialog(this,
                "Sure you want to delete this user?", "Delete comfirmation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

        if (res == JOptionPane.YES_OPTION) {
            userService.deleteUser(selectedUser);
            updateTable();
            clearField();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void lockToggleButtontoggleLockUser(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockToggleButtontoggleLockUser
        // TODO add your handling code here:
        if (usernameField.getText().equals("")) {
            return;
        }
        isLocked = !isLocked;
        if (isLocked) {
            lockToggleButton.setIcon(new ImageIcon(getClass().getResource("/com/btv/images/lock.png")));
        } else {
            lockToggleButton.setIcon(new ImageIcon(getClass().getResource("/com/btv/images/unlock.png")));
        }

    }//GEN-LAST:event_lockToggleButtontoggleLockUser

    private void usernameAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameAddFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameAddFieldActionPerformed

    private void emailAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailAddFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailAddFieldActionPerformed

    private void nameAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameAddFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameAddFieldActionPerformed

    private void addressAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressAddFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressAddFieldActionPerformed

    private void maleRadioButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maleRadioButtonAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maleRadioButtonAddActionPerformed

    private void femaleRadioButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleRadioButtonAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleRadioButtonAddActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed

        String address, email, name, password, status, username;
        Date birthday, timeCreate;
        boolean gender;
        address = addressAddField.getText();
        email = emailAddField.getText();
        name = nameAddField.getText();
        password = passwordAddField.getText();
        status = "OFFLINE";
        username = usernameAddField.getText();

        if (birthDateChooserAdd.getDate() != null) {
            birthday = new Date(birthDateChooserAdd.getDate().getTime());
        } else {
            birthday = null;
        }

        timeCreate = java.sql.Date.valueOf(LocalDate.now());
        if (maleRadioButtonAdd.isSelected()) {
            gender = true;
        } else {
            gender = false;
        }

        User newUser = new User(username, name, address, birthday, gender, email, timeCreate, status, password);

        String validMessage = userService.validateUser(newUser);
        if (validMessage.equals("valid")) {
            userService.addNewUser(newUser);
            addUserDialog.setVisible(false);

            addressAddField.setText("");
            emailAddField.setText("");
            nameAddField.setText("");
            passwordAddField.setText("");
            usernameAddField.setText("");
            updateTable();
        } else {
//            messageAddLabel.setText(validMessage);
//            messageAddLabel.setForeground(Color.red);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void passwordAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordAddFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordAddFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JDialog addUserDialog;
    private javax.swing.JTextField addressAddField;
    private javax.swing.JTextField addressField;
    private com.toedter.calendar.JDateChooser birthChooser;
    private com.toedter.calendar.JDateChooser birthDateChooserAdd;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField emailAddField;
    private javax.swing.JTextField emailField;
    private javax.swing.JRadioButton femaleRadioButton;
    private javax.swing.JRadioButton femaleRadioButtonAdd;
    private javax.swing.JComboBox<String> filterOptions;
    private javax.swing.JTextField filterTextField;
    private javax.swing.JList<String> friendList;
    private javax.swing.ButtonGroup genderAddbuttonGroup;
    private javax.swing.ButtonGroup genderBtnGroup;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToggleButton lockToggleButton;
    private javax.swing.JList<String> loginList;
    private javax.swing.JScrollPane loginScrollPane;
    private javax.swing.JRadioButton maleRadioButton;
    private javax.swing.JRadioButton maleRadioButtonAdd;
    private javax.swing.JTextField messageField;
    private javax.swing.JTextField nameAddField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField passwordAddField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JComboBox<String> statusOptions;
    private com.btv.Admin.gui.components.TableCustom tableUsers;
    private javax.swing.JLabel titleAddDialg;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField usernameAddField;
    private javax.swing.JTextField usernameField;
    // End of variables declaration//GEN-END:variables
}
