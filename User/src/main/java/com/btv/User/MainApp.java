/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btv.User;
import com.btv.User.components.Layout;
import com.btv.User.gui.Login;
import com.btv.User.gui.SignUp;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import com.btv.User.utils.LoginListener;
import com.btv.User.utils.SignUpListener;

/**
 *
 * @author taing
 */
public class MainApp {
    private Login logInForm;
    private SignUp signUpForm;
    private Layout mainLayout;

    public MainApp() {
        mainLayout = new Layout();
        
        logInForm = new Login(new LoginListener() {
            @Override
            public void onSignUpLinkClicked() {
                logInForm.setVisible(false);
                signUpForm.setVisible(true);
            }
            @Override
            public void onLoginButtonClicked() {
                logInForm.setVisible(false);
                signUpForm.setVisible(false);
                
                mainLayout.setVisible(true);
                mainLayout.setTitle("ChatChat");
            }
        });
        signUpForm = new SignUp(new SignUpListener() {
            @Override
            public void onLoginLinkClicked() {
                logInForm.setVisible(true);
                signUpForm.setVisible(false);
            }
        });
        
        logInForm.setVisible(true);
        signUpForm.setVisible(false);
    }

    public static void main(String args[]) {           
        FlatLightLaf.setup();
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp();
            }
        });
    }
}