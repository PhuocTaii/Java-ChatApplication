/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.btv.User.helper;

/**
 *
 * @author Vo Quoc Binh
 */
public interface TableActionEvent {

    public void onChat(int row);

    public void onAddFriend(int row);

    public void onBlock(int row);
}
