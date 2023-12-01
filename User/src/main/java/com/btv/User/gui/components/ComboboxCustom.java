/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.gui.components;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vo Quoc Binh
 */
public class ComboboxCustom extends JComboBox<Object> {
    
    public ComboboxCustom() {
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(5, 3, 0, 3));
        setFont(new Font("Calibri",Font.BOLD, 18));
    }
}
