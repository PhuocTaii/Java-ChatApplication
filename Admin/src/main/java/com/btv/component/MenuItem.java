package com.btv.component;

import com.btv.model.MenuModel;
import static com.btv.model.MenuModel.MenuType.MENU;
import static com.btv.model.MenuModel.MenuType.TITLE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

public class MenuItem extends javax.swing.JPanel {

    private boolean isSelected;

    public MenuItem(MenuModel data) {
        initComponents();
        setOpaque(false);
        if (data.getType() == MenuModel.MenuType.MENU) {
            iconLabel.setIcon(data.toIcon());
            nameLabel.setText(data.getName());
        } else if (data.getType() == MenuModel.MenuType.TITLE) {
            iconLabel.setText(data.getName());
            iconLabel.setFont(new Font("sansserif", 1, 12));
            nameLabel.setVisible(false);
        } else {
            nameLabel.setText(" ");
        }
    }


    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
        repaint();
    }

//    @Override
//    protected void paintChildren(Graphics g) {
//        if (isSelected) {
//            Graphics2D g2 = (Graphics2D) g;
//            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//            g2.setColor(new Color(255, 255, 255, 80));
//            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
//        }
//
//        super.paintComponent(g);
//    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 102));

        nameLabel.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(iconLabel)
                .addGap(30, 30, 30)
                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel nameLabel;
    // End of variables declaration//GEN-END:variables
}
