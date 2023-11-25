package com.btv.gui;

import com.btv.newSwing.ScrollBar;
import java.awt.Image;
import java.awt.MenuBar;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LoginList extends javax.swing.JFrame {

    private int pX;
    private int pY;

    private boolean isNavbarVisible = false;

    private int widthNavbar = 250;

    public LoginList() {
        initComponents();
        init();

    }

    private void init() {
        setIconImage(new ImageIcon(getClass().getResource("/com/btv/image"))
                .getImage()
                .getScaledInstance(hamButton.getWidth(), hamButton.getHeight(), Image.SCALE_SMOOTH));
        testData(tableLogin);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        loginScollPane = new javax.swing.JScrollPane();
        tableLogin = new com.btv.newSwing.TableCustom();
        hamButton = new javax.swing.JLabel();
        menuLogin = new com.btv.component.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        background.setBackground(new java.awt.Color(237, 237, 237));
        background.setMinimumSize(new java.awt.Dimension(800, 600));
        background.setPreferredSize(new java.awt.Dimension(1024, 768));
        background.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                backgroundMousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("LOGIN LIST");
        jLabel1.setToolTipText("");
        jLabel1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        loginScollPane.setBorder(null);

        tableLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Username", "Name", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        loginScollPane.setViewportView(tableLogin);
        if (tableLogin.getColumnModel().getColumnCount() > 0) {
            tableLogin.getColumnModel().getColumn(0).setResizable(false);
            tableLogin.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableLogin.getColumnModel().getColumn(1).setResizable(false);
            tableLogin.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableLogin.getColumnModel().getColumn(2).setResizable(false);
            tableLogin.getColumnModel().getColumn(2).setPreferredWidth(120);
            tableLogin.getColumnModel().getColumn(3).setResizable(false);
        }

        hamButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        hamButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/btv/image/ham.png"))); // NOI18N
        hamButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hamButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hamButtonMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
        background.setLayout(backgroundLayout);
        backgroundLayout.setHorizontalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundLayout.createSequentialGroup()
                        .addComponent(hamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(278, 278, 278)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 925, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        backgroundLayout.setVerticalGroup(
            backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hamButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(loginScollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        menuLogin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                menuLoginPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(menuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, 1027, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(background, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backgroundMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_backgroundMousePressed

    private void hamButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hamButtonMouseClicked
        menuLogin.openNavbar();
        setBackgroundVisible(false);
        isNavbarVisible = true;
    }//GEN-LAST:event_hamButtonMouseClicked

    private void backgroundMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundMouseClicked
        if (isNavbarVisible) {
            menuLogin.closeNavbar();
            setBackgroundVisible(true);
            isNavbarVisible = false;
        }
    }//GEN-LAST:event_backgroundMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        menuLogin.closeNavbar();
        setBackgroundVisible(true);
        isNavbarVisible = false;
       
    }//GEN-LAST:event_formMouseClicked

    private void menuLoginPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_menuLoginPropertyChange
//            setBackgroundVisible(true);
    }//GEN-LAST:event_menuLoginPropertyChange

    @Override
    public MenuBar getMenuBar() {
        return super.getMenuBar(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    
    private void setBackgroundVisible(boolean bl) {
        background.setVisible(bl);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel background;
    private javax.swing.JLabel hamButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane loginScollPane;
    private com.btv.component.Menu menuLogin;
    private com.btv.newSwing.TableCustom tableLogin;
    // End of variables declaration//GEN-END:variables
}
