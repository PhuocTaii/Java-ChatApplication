/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.btv.User.gui.interfaces;

import com.btv.User.helper.MessageStatus;
import com.btv.User.model.Member;

/**
 *
 * @author tvan
 */
public interface CreateGroupListener {
    public void addFoundMember(MessageStatus res, Member mem);
    public void createGroup(MessageStatus res);
}
