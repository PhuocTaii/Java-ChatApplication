/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.btv.User.gui.interfaces;

import com.btv.User.helper.MessageStatus;
import com.btv.User.model.ChatMessage;
import com.btv.User.model.Group;
import com.btv.User.model.Member;
import com.btv.User.model.User;
import java.util.ArrayList;

/**
 *
 * @author tvan
 */
public interface ChatListener {
    public void loadListFriend(ArrayList<User> listFriend);
    public void updateFriendStatus(int friendId, boolean isOnline);
    public void unfriend(int friendId);
    public void loadChatUI(int id, String name, boolean isGroup);
    public void loadChatData(ArrayList<ChatMessage> listChat);
    public void reportNoti(MessageStatus res);
    public void blockNoti(MessageStatus res);
    public void loadListGroup(ArrayList<Group> listGroup);
    public void loadListMember(ArrayList<Member> listMem, boolean isAdmin);
    public void updateGroupName(int groupId, String newName);
    public void addGroupMember(ArrayList<Member> listMem, boolean isAdmin);
    public void assignAdmin(ArrayList<Member> listMem, boolean isAdmin);
    public void removeMember(ArrayList<Member> listMem, boolean isAdmin);

}
