/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.model;

import com.sun.source.tree.IfTree;

/**
 *
 * @author taing
 */
public class Friends extends User{
    private int directFriends, indirectFriends;

    public Friends() {
        super();
    }
    
    public int getDirectFriends(){
        return this.directFriends;
    }
    
    public int getIndirectFriends(){
        return this.indirectFriends;
    }
    
    public void setDirectFriends(int n){
        this.directFriends = n;
    }
    
    public void setIndirectFriends(int n){
        this.indirectFriends = n;
    }
}
