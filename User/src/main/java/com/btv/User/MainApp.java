/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.btv.User;

//import com.btv.User.components.Layout;
import com.btv.User.gui.layouts.Layout;
import com.btv.User.gui.Login;
import com.btv.User.gui.SignUp;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatLightLaf;
import com.btv.User.gui.interfaces.LoginListener;
import com.btv.User.gui.interfaces.SignUpListener;
import com.btv.User.model.User;
import com.btv.User.service.AuthService;
import com.btv.User.service.SecurityService;
import io.github.cdimascio.dotenv.Dotenv;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 *
 * @author taing
 */
public class MainApp {

    private Login logInForm;
    private SignUp signUpForm;
    private Layout mainLayout;
    private AuthService authService;
    private static PrivateKey privateKey;
    private static User user;

    public MainApp() {
        authService = new AuthService();

        logInForm = new Login(new LoginListener() {
            @Override
            public void onSignUpLinkClicked() {
                logInForm.setVisible(false);
                signUpForm.setVisible(true);
            }
            
            @Override
            public void onLoginSuccess() {
                openApp();
            }
        });
        signUpForm = new SignUp(new SignUpListener() {
            @Override
            public void onLoginLinkClicked() {
                logInForm.setVisible(true);
                signUpForm.setVisible(false);
            }
            
            @Override
            public void onSignUpSuccess() {
                openApp();
            }
        });

        logInForm.setVisible(true);
        signUpForm.setVisible(false);
    }
    
    private void openApp() {
        logInForm.setVisible(false);
        signUpForm.setVisible(false);

        mainLayout = new Layout();
        mainLayout.setVisible(true);
        mainLayout.setTitle("ChatChat");
        new Thread(ClientSocket.getInstance()).start();
        setSecurity();
    }
    
    private boolean setSecurity() {
        Dotenv dotenv = Dotenv.load();
        Path pathPrivate = Paths.get(dotenv.get("PRIVATE_KEY_PATH") + "_" + MainApp.getUser().getId() + ".pem");
        try {
            if(Files.exists(pathPrivate)) {
                this.privateKey = SecurityService.readPrivateKey(pathPrivate);
            }
            else {
                KeyPair keyPair = SecurityService.generateKeyPair();
                SecurityService.storeKey(keyPair.getPrivate(), pathPrivate);
                SecurityService.storePublicKeyInDB(keyPair.getPublic());
                this.privateKey = keyPair.getPrivate();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static PrivateKey getPrivateKey() {
        return privateKey;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        MainApp.user = user;
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