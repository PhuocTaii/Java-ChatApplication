/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.btv.User;

import com.btv.User.gui.layouts.Layout;
import com.btv.User.gui.Login;
import com.btv.User.gui.SignUp;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import com.btv.User.gui.interfaces.LoginListener;
import com.btv.User.gui.interfaces.SignUpListener;
import com.btv.User.helper.MessageStatus;
import com.btv.User.service.AuthService;

/**
 *
 * @author taing
 */
public class MainApp {

    private Login logInForm;
    private SignUp signUpForm;
    private Layout mainLayout;
    private AuthService authService;

    public MainApp() {
        mainLayout = new Layout();
        authService = new AuthService();

        logInForm = new Login(new LoginListener() {
            @Override
            public void onSignUpLinkClicked() {
                logInForm.setVisible(false);
                signUpForm.setVisible(true);
            }

            @Override
            public MessageStatus onLoginButtonClicked() {
                return authService.login(logInForm.username, logInForm.password);
            }
            
            @Override
            public MessageStatus onForgotPassLinkClicked() {
                return authService.forgotPassword(logInForm.username);
            }
            
            @Override
            public void onLoginSuccess() {
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
            
            @Override
            public MessageStatus onSignUpButtonClicked() {
                return authService.signup(signUpForm.username, signUpForm.email, signUpForm.password, signUpForm.name, signUpForm.address, signUpForm.birthDate, signUpForm.gender);
            }
            
            @Override
            public void onSignUpSuccess() {
                logInForm.setVisible(false);
                signUpForm.setVisible(false);

                mainLayout.setVisible(true);
                mainLayout.setTitle("ChatChat");
            }
        });

        logInForm.setVisible(true);
        signUpForm.setVisible(false);
    }

    public static void main(String args[]) {
        FlatLightLaf.setup();
        
        // init instance to send role
        ClientSocket.getInstance();
        
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainApp();
            }
        });
    }
}
