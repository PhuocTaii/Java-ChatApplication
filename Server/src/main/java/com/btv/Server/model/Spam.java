package com.btv.Server.model;

import java.sql.Date;

public class Spam {
    private String reportedUsername, spamName, reporter;
    private Date spamTime;
    private int spamId, reportedId, reportId;
    private boolean isLocked;

    public Spam() {
    }

    public String getSpamName() {
        return spamName;
    }

    public String getReportedUsername() {
        return reportedUsername;
    }

    public void setReportedUsername(String reportedUsername) {
        this.reportedUsername = reportedUsername;
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

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
    
}