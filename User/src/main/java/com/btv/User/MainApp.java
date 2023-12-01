/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.btv.User;

import com.btv.User.gui.layouts.Layout;
import com.btv.User.gui.Login;
import com.btv.User.gui.Search;
import com.btv.User.gui.SignUp;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import com.btv.User.gui.interfaces.LoginListener;
import com.btv.User.gui.interfaces.SignUpListener;

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
        
        // init instance to send role
        ClientSocket.getInstance();
        
        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                new MainApp();
                JFrame frame = new JFrame();
                frame.add(new Search());
                frame.setSize(1080, 768);
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                frame.setVisible(true);
            }
        });
    }
}
