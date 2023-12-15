package com.btv.Server.model;

import java.sql.Date;


public class Group {
    private String  groupName;
    private Date  timeCreate;
    private int id;

    public Group(String groupName, Date timeCreate, int id) {
        this.groupName = groupName;
        this.timeCreate = timeCreate;
        this.id = id;
    }

    public Group() {
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
