/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author tvan
 */
public class User {
    private int id;
    private String address, email, name, password, status, username, genderStr;
    private Date birthday, timeCreate, loginDate;
    private boolean gender;
    private ArrayList<Byte[]> publicKeys;
    
    public User() {
        publicKeys = new ArrayList<>();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
        if(gender){
            this.genderStr = "Male";
        }else
            this.genderStr = "Female";
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public Date getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }

    public String getGenderStr() {
        return genderStr;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }
    
    public boolean getGender() {
        return this.gender;
    }

    public ArrayList<Byte[]> getPublicKeys() {
        return publicKeys;
    }

    public void addPublicKey(byte[] key) {
        publicKeys.add(ArrayUtils.toObject(key));
    }
}
