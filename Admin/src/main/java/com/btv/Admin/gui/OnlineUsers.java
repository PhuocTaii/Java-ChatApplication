/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.btv.Admin.gui;

import com.btv.Admin.gui.components.GraphDrawer;
import com.btv.Admin.service.OnlineUsersService;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class OnlineUsers extends javax.swing.JPanel {
    private DefaultTableModel tableModel;
    private String[][] onlineUsersList;
    private String[][] loginLog;
    private OnlineUsersService onlineUserService;
    private GraphDrawer drawer;
    /**
     * Creates new form OnlineUsers
     */
    public OnlineUsers() {
        initComponents();
        onlineUserService = new OnlineUsersService();

        loginLog = onlineUserService.getAllLoginTimes();
        
        int year = yearchooser.getYear();
 
        int monthCnt[] = onlineUserService.MakeChart(loginLog, year);
        
        drawer = new GraphDrawer(monthCnt);
        statistic.setLayout(new BorderLayout());
        statistic.add(drawer, BorderLayout.CENTER);
        statistic.setPreferredSize(drawer.getPreferredSize());
        statistic.setMaximumSize(drawer.getPreferredSize());
        onlineUserService.filterByName(tableCustom1, "");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pageheader = new javax.swing.JPanel();
        header = new javax.swing.JLabel();
        options = new javax.swing.JPanel();
        connector = new javax.swing.JLabel();
        searchButton1 = new javax.swing.JButton();
        filterOptions = new javax.swing.JComboBox<>();
        filter = new javax.swing.JLabel();
        startDate = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        endDate = new com.toedter.calendar.JDateChooser();
        Input = new javax.swing.JTextField();
        numberOptions = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCustom1 = new com.btv.Admin.gui.components.TableCustom();
        statisticzone = new javax.swing.JPanel();
        zoneName = new javax.swing.JLabel();
        year = new javax.swing.JLabel();
        yearchooser = new com.toedter.calendar.JYearChooser();
        statistic = new javax.swing.JPanel();

        setOpaque(false);

        pageheader.setMaximumSize(new java.awt.Dimension(121, 32));
        pageheader.setMinimumSize(new java.awt.Dimension(121, 32));
        pageheader.setOpaque(false);
        pageheader.setPreferredSize(new java.awt.Dimension(121, 32));
        pageheader.setLayout(new java.awt.BorderLayout());

        header.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        header.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.setText(" ONLINE USERS");
        pageheader.add(header, java.awt.BorderLayout.CENTER);

        options.setOpaque(false);

        connector.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        connector.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        connector.setText("-");

        searchButton1.setBackground(new java.awt.Color(48, 162, 255));
        searchButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        searchButton1.setForeground(new java.awt.Color(255, 255, 255));
        searchButton1.setText("Search");
        searchButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchButton1.setPreferredSize(new java.awt.Dimension(75, 30));
        searchButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButton1MouseClicked(evt);
            }
        });

        filterOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "Name", "Number" }));
        Input.setVisible(false);
        filterOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterOptionsActionPerformed(evt);
            }
        });

        filter.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filter.setText("Filter:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Period of time:");

        endDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                endDatePropertyChange(evt);
            }
        });

        Input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InputActionPerformed(evt);
            }
        });

        numberOptions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Equal", "Greater", "Less" }));

        javax.swing.GroupLayout optionsLayout = new javax.swing.GroupLayout(options);
        options.setLayout(optionsLayout);
        optionsLayout.setHorizontalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(connector, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(filter)
                .addGap(18, 18, 18)
                .addComponent(filterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(numberOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Input, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(searchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        optionsLayout.setVerticalGroup(
            optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(optionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(searchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filterOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Input, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(numberOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(connector, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startDate, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        //numberOptions.setVisible(false);

        tableCustom1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Time create", "Time open app", "No. users chat with", "No. groups chat with"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableCustom1);

        statisticzone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        statisticzone.setOpaque(false);

        zoneName.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        zoneName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        zoneName.setText("STATISTIC");

        year.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        year.setText("Year:");
        year.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        yearchooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                yearchooserPropertyChange(evt);
            }
        });

        statistic.setPreferredSize(new java.awt.Dimension(0, 160));
        statistic.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                statisticPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout statisticLayout = new javax.swing.GroupLayout(statistic);
        statistic.setLayout(statisticLayout);
        statisticLayout.setHorizontalGroup(
            statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        statisticLayout.setVerticalGroup(
            statisticLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 160, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout statisticzoneLayout = new javax.swing.GroupLayout(statisticzone);
        statisticzone.setLayout(statisticzoneLayout);
        statisticzoneLayout.setHorizontalGroup(
            statisticzoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticzoneLayout.createSequentialGroup()
                .addGap(475, 475, 475)
                .addComponent(year)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(yearchooser, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(statisticzoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(statisticzoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zoneName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statistic, javax.swing.GroupLayout.DEFAULT_SIZE, 937, Short.MAX_VALUE))
                .addContainerGap())
        );
        statisticzoneLayout.setVerticalGroup(
            statisticzoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statisticzoneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zoneName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(statisticzoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(yearchooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(year, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statistic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pageheader, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(options, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(statisticzone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pageheader, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(statisticzone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void statisticPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_statisticPropertyChange
        // TODO add your handling code here:

    }//GEN-LAST:event_statisticPropertyChange

    private void yearchooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_yearchooserPropertyChange
        // TODO add your handling code here:
        int year = yearchooser.getYear();
        statistic.remove(drawer);
        int monthCnt[] = onlineUserService.MakeChart(loginLog, year);
        
        drawer = new GraphDrawer(monthCnt);
        statistic.setLayout(new BorderLayout());
        statistic.add(drawer, BorderLayout.CENTER);
        statistic.setPreferredSize(drawer.getPreferredSize());
        statistic.setMaximumSize(drawer.getPreferredSize());
        
        
        statistic.revalidate();
        statistic.repaint();
    }//GEN-LAST:event_yearchooserPropertyChange

    private void filterOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterOptionsActionPerformed
        // TODO add your handling code here:
        JComboBox cb = (JComboBox)evt.getSource();
        String optionChosen = (String)cb.getSelectedItem();
        searchButton1.setVisible(true);

        if ("None".equals(optionChosen)) {
            Input.setVisible(false);
            numberOptions.setVisible(false);
        }
        else if ("Name".equals(optionChosen)) {
            Input.setVisible(true);
            numberOptions.setVisible(false);
        }else{
            Input.setVisible(true);
            numberOptions.setVisible(true);

        }

        filterOptions.revalidate();
        filterOptions.repaint();
    }//GEN-LAST:event_filterOptionsActionPerformed

    private void InputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_InputActionPerformed

    private void endDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_endDatePropertyChange
        // TODO add your handling code here:
        endDate.getDateEditor().addPropertyChangeListener(e -> {
    if (startDate.getDate() != null){
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                onlineUserService = new OnlineUsersService();

                LocalDate fromDate = startDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate toDate = endDate.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                onlineUsersList = onlineUserService.getAllOnlineUsers(fromDate.toString(), toDate.toString());
                tableModel = (DefaultTableModel)tableCustom1.getModel();
                tableModel.setRowCount(0);
                for(Object[] row : onlineUsersList) {
                    tableModel.addRow(row);
                }

            }
        });
        timer.setRepeats(false); // Set to false to run only once
        timer.start();
    }});        
    }//GEN-LAST:event_endDatePropertyChange

    private void searchButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButton1MouseClicked
        // TODO add your handling code here:
        if("None".equals(filterOptions.getSelectedItem())){
            Input.setText("");
        }
        else if("Name".equals(filterOptions.getSelectedItem())){
            String searchString = Input.getText();
            onlineUserService.filterByName(tableCustom1, searchString);
            Input.setText("");
        }
        else{
            String numString = Input.getText();
            onlineUserService.filterByNumber(tableCustom1, numString, numberOptions);
            Input.setText("");
        }
    }//GEN-LAST:event_searchButton1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Input;
    private javax.swing.JLabel connector;
    private com.toedter.calendar.JDateChooser endDate;
    private javax.swing.JLabel filter;
    private javax.swing.JComboBox<String> filterOptions;
    private javax.swing.JLabel header;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> numberOptions;
    private javax.swing.JPanel options;
    private javax.swing.JPanel pageheader;
    private javax.swing.JButton searchButton1;
    private com.toedter.calendar.JDateChooser startDate;
    private javax.swing.JPanel statistic;
    private javax.swing.JPanel statisticzone;
    private com.btv.Admin.gui.components.TableCustom tableCustom1;
    private javax.swing.JLabel year;
    private com.toedter.calendar.JYearChooser yearchooser;
    private javax.swing.JLabel zoneName;
    // End of variables declaration//GEN-END:variables
}
