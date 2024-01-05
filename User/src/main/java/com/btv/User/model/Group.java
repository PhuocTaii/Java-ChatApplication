/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.model;

/**
 *
 * @author tvan
 */
public class Group {
    private int id;
    private String name;
    private boolean isSeen, isEncrypted;
    
    public Group() {
        isSeen = true;
        isEncrypted = false;
    }
    
    public Group(int id, boolean isEncrypted) {
        this.id = id;
        this.isEncrypted = isEncrypted;
    }
    
    public Group(Group group) {
        this.id = group.id;
        this.name = group.name;
        isSeen = true;
        isEncrypted = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public boolean getIsEncrypted() {
        return isEncrypted;
    }

    public void setIsEncrypted(boolean isEncrypted) {
        this.isEncrypted = isEncrypted;
    }
}
