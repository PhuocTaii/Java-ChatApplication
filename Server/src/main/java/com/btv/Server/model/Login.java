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
public class Login {
    private Date loginDate;
    private int id;

    public Login(Date logDate, int id) {
        this.loginDate = logDate;
        this.id = id;
    }
    
    public Login(){
        
    }
    
    public Date getLoginDate(){
        return this.loginDate;
    }
    
    public int getId(){
        return this.id;
    }
    
    public void setLoginDate(Date n){
        this.loginDate = n;
    }
    
    public void setId(int n){
        this.id = n;
    }
}
