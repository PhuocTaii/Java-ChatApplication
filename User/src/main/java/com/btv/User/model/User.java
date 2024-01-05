/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.User.model;

import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author tvan
 */
public class User {
    private int id;
    private String username;
    private String name;
    private boolean isOnline, isSeen;
    private ArrayList<Byte[]> publicKeys;
    
    public User() {
        isSeen = true;
        publicKeys = new ArrayList<>();
    }

    public User(int id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
        isSeen = true;
        publicKeys = new ArrayList<>();
    }
    
    public User(int id, String username, boolean isOnline) {
        this.id = id;
        this.username = username;
        this.isOnline = isOnline;
        isSeen = true;
        publicKeys = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public ArrayList<Byte[]> getPublicKeys() {
        return publicKeys;
    }

    public void addPublicKey(byte[] key) {
        publicKeys.add(ArrayUtils.toObject(key));
    }
}
