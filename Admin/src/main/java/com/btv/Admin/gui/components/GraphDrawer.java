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
    private String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int unitX;
    private int unitY;
    private int prevX;
    private int prevY;
    
    public int findMaxY() {
        int maxY = yCoords[0];
        for(int i = 0; i < yCoords.length; i++){
            if(maxY < yCoords[i])
                maxY = yCoords[i];
        }
        return maxY;
    }
    
    public int findMinY() {
        int minY = yCoords[0];
        for(int i = 0; i < yCoords.length; i++){
            if(minY > yCoords[i])
                minY = yCoords[i];
        }
        return minY;
    }
    
    public int findAvgY(){
        int avg = 0;
        for(int i = 0; i < yCoords.length; i++){
            avg += yCoords[i];
        }
        return avg/yCoords.length;
    }

    public GraphDrawer(int[] yCoords, int startX, int startY, int endX, int endY) {
        this.yCoords = yCoords;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.unitX = (endX - startX) / 12; // Tính toán unitX dựa trên số lượng giá trị yCoords
        this.unitY = (endY - startY) / 5;
        this.prevX = startX;
        this.prevY = endY;
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
        int sum = 0;
        for(int i = 0; i < yCoords.length; i++){
            sum += yCoords[i];
        }

        for (int i = 0; i < yCoords.length; i++) {
            int currentX = startX + i * unitX;
            int currentY = 0;
            if (findMaxY() == 0){
                currentY = endY - (yCoords[i] * 5) * unitY;
            } else{
                currentY = endY - (yCoords[i] * 5 / findMaxY()) * unitY;
            }
            g2d.drawLine(prevX, prevY, currentX, currentY);

            // Hiển thị số liệu
            String label = months[i];
            g2d.drawString(label, currentX + 5 , endY - 10); // thang o truc hoanh
            
            prevX = currentX;
            prevY = currentY;
        }
        
        g2d.drawString(Integer.toString(findMaxY()), startX - 30, startY + 15);
        g2d.drawString(Integer.toString(findMinY()), startX - 30, endY - 5);
        if(findAvgY() != 0){
            g2d.drawString(Integer.toString(findAvgY()), startX - 30, startY + (endY - startY) / 2);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(endX, endY);
    }
}
