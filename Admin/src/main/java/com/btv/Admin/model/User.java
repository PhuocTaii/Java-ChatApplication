package com.btv.Admin.model;

import java.sql.Date;


public class User {
    private String address, email, name, password, status, username;
    private Date birthday, timeCreate;
    private int id;
    private int gender;

    public User() {
    }

    public User(User user){
        
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public User(String username,String name,String address,Date birthday, int gender,String email, Date timeCreate, String status, String password) {
        this.address = address;
        this.email = email;
        this.name = name;
        this.password = password;
        this.status = status;
        this.username = username;
        this.birthday = birthday;
        this.timeCreate = timeCreate;
        this.gender = gender;
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

    public void setGender(int gender) {
        this.gender = gender;
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

    public int getGender() {
        return gender;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }
    
}
