/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btv.Admin;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;

/**
 *
 * @author taing
 */
public class Admin {

    public static void main(String args[]) {           
        FlatLightLaf.setup();
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AllUsers().setVisible(true);
            }
        });
    }
}
