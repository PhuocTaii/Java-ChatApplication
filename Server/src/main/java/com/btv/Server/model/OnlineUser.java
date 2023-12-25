/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.model;

import java.util.Date;


/**
 *
 * @author taing
 */
public class OnlineUser extends User{
    private int loginTimes, userChatWith, groupChatWith;
    
    public OnlineUser() {
        super();
    }
    
    
    public int getuserChatWith(){
        return this.userChatWith;
    }
    
    public int getgroupChatWith(){
        return this.groupChatWith;
    }
    
    public int getLoginTime(){
        return this.loginTimes;
    }
    
    public void setloginTimes(int n){
        this.loginTimes = n;
    }
    
    public void setuserChatWith(int n){
        this.userChatWith = n;
    }
    
    public void setgroupChatWith(int n){
        this.groupChatWith = n;
    }
    
}
