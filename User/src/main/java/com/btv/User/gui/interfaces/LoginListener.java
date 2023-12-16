/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.btv.User.gui.interfaces;

import com.btv.User.helper.MessageStatus;

/**
 *
 * @author Admin
 */
public interface LoginListener {
    void onSignUpLinkClicked();
    MessageStatus onLoginButtonClicked();
    MessageStatus onForgotPassLinkClicked();
    void onLoginSuccess();
}
