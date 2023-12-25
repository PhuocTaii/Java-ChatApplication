/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author taing
 */
public class GraphDrawer extends JPanel {
    private int[] yCoords;

    private int startX = 50;
    private int startY = 0;
    private int endX = 939;
    private int endY = 251;
    private int unitX = (endX - startX) / 12;
    private int unitY = (endY - startY) / 5;
    private int prevX = startX;
    private int prevY = endY;

    public GraphDrawer(int[] yCoords) {
        this.yCoords = yCoords;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Vẽ lưới đồ thị
        g2d.setColor(Color.BLUE);
        for (int i = startX; i < endX; i += unitX) {
            g2d.drawLine(i, startY, i, endY);
        }

        for (int i = startY; i < endY; i += unitY) {
            g2d.drawLine(startX, i, endX, i);
        }

        // Vẽ trục x và y
        g2d.setColor(Color.BLACK);
        g2d.drawLine(startX, startY, startX, endY);
        g2d.drawLine(startX, endY, endX, endY);

        // Vẽ đồ thị
        g2d.setColor(Color.RED);
        for (int y : yCoords) {
            g2d.drawLine(prevX, prevY, prevX += unitX, prevY = endY - (y * unitY));
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(endX, endY);
    }
}
