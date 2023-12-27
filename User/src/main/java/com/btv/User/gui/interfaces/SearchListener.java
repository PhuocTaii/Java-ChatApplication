/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.btv.User.gui.interfaces;

import com.btv.User.helper.MessageStatus;
import com.btv.User.model.User;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public interface SearchListener {
    public void showFoundUsers(ArrayList<User> listUser);
    public void addFriend(MessageStatus res);
}
