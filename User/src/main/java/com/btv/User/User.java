/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.btv.User;
import com.btv.User.LayoutComponents.Layout;
import com.btv.User.Features.Login;
import com.btv.User.Features.SignUp;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import com.btv.User.Utils.LoginListener;
import com.btv.User.Utils.SignUpListener;

/**
 *
 * @author taing
 */
public class User {
    private Login logInForm;
    private SignUp signUpForm;

    public User() {
        logInForm = new Login(new LoginListener() {
            @Override
            public void onSignUpLinkClicked() {
                logInForm.setVisible(false);
                signUpForm.setVisible(true);
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
                new User();
            }
        });
    }
}