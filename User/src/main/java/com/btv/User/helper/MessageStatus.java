/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.btv.User.helper;

/**
 *
 * @author tvan
 */
public enum MessageStatus {
    SUCCESS(true, "Successful"),
    FAIL(false, "Failed");

    private boolean success;
    private String message;

    MessageStatus(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
