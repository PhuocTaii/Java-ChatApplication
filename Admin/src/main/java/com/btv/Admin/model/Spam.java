package com.btv.Admin.model;

import java.sql.Date;

public class Spam {
    private String spamUsername, spamName;
    private Date spamTime;
    private int spamId, reportedId, reportId;

    public Spam() {
    }

    public String getSpamName() {
        return spamName;
    }

    public String getSpamUsername() {
        return spamUsername;
    }

    public void setSpamUsername(String spamUsername) {
        this.spamUsername = spamUsername;
    }

    public Date getSpamTime() {
        return spamTime;
    }

    public void setSpamTime(Date spamTime) {
        this.spamTime = spamTime;
    }

    public int getSpamId() {
        return spamId;
    }

    public void setSpamId(int spamId) {
        this.spamId = spamId;
    }

    public int getReportedId() {
        return reportedId;
    }

    public void setSpamName(String spamName) {
        this.spamName = spamName;
    }

    public void setReportedId(int reportedId) {
        this.reportedId = reportedId;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }
}