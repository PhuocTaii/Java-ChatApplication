/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Server.socket;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tvan
 */
public class MailService {
    private static MailService mailInstance = null;
    
    public static final String HOST_NAME = "smtp.gmail.com";
    public static final int SSL_PORT = 465; // Port for SSL
    public static final String APP_EMAIL = "ChatChat.BTV@gmail.com"; // your email
    public static final String APP_PASSWORD = System.getenv("APP_MAIL_PASSWORD"); // your password
    
    private Session session;
            
    private MailService() {
        // Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST_NAME);
        props.put("mail.smtp.socketFactory.port", SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", SSL_PORT);
        
        //create the Session object
        Authenticator authenticator = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(APP_EMAIL, APP_PASSWORD);
            }
        };
        session = Session.getInstance(props, authenticator);
    }
    
    public static MailService getMailInstance() {
        if(mailInstance == null) {
            mailInstance = new MailService();
        }
        return mailInstance;
    }
    
    public static boolean isValidEmail(String email) {
       final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(email);
       return matcher.matches() ? true : false;
    }
    
    public boolean sendMail(String mailReceive, String text) {
        try {
            MimeMessage message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailReceive));
            message.setSubject("Reset password");
            message.setText(text);
 
            // send message
            Transport.send(message);
 
            System.out.println("Email sent successfully");
            
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}