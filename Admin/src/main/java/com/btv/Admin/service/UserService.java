/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.btv.Admin.service;

import com.btv.Admin.ClientSocket;
import com.btv.Admin.helper.MessageType;
import com.btv.Admin.model.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author tvan
 */
public class UserService {

    public String[][] getAllUsers() {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_USERS.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numUsers = clientSocket.dataIn.read();

            ArrayList<String[]> users = new ArrayList<>();
            for (int i = 0; i < numUsers; i++) {
                String userData = clientSocket.dataIn.readLine();
                users.add(userData.split("\\|"));
            }
            String[][] usersArray = new String[users.size()][];
            return users.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }
    }

    public void filterByField(JTable table, String fieldName, String searchValue) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);
        if (searchValue.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            if (fieldName.equals("Username")) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 1)); // Case-insensitive search
            } else if (fieldName.equals("Name")) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 2)); // Case-insensitive search
            } else if (fieldName.equals("Status")) {
                rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchValue, 8)); // Case-insensitive search

            }
        }
    }

    public void addNewUser(User newUser) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.ADD_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(newUser.getUsername() + "|");
            clientSocket.dataOut.write(newUser.getName() + "|");
            clientSocket.dataOut.write(newUser.getAddress() + "|");
            clientSocket.dataOut.write(newUser.getBirthday().toString() + "|");
            clientSocket.dataOut.write(newUser.getEmail() + "|");
            clientSocket.dataOut.write(newUser.getGender() + "|");
            clientSocket.dataOut.write(newUser.getTimeCreate() + "|");
            clientSocket.dataOut.write(newUser.getStatus() + "|");
            clientSocket.dataOut.write(newUser.getPassword() + "|");
            clientSocket.dataOut.newLine();

            clientSocket.dataOut.flush();

            // read number of users
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String validateUser(User user) {
        if (!isValidUsername(user.getUsername())) {
            return "Username is not valide.";
        } else if (!isValidPassword(user.getPassword())) {
            return "Password at least 6 characters.";
        } else if (!isValidEmail(user.getEmail())) {
            return "Email is not valid.";
        } else if (!isValidBirthday(user.getBirthday())) {
            return "Birthday is not allowed empty.";
        }

        return "valid";
    }

    public static boolean isValidUsername(String username) {
        String regex = "^[a-zA-Z0-9_]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);

        return matcher.matches();
    }

    public static boolean isValidBirthday(Date birthdate) {
        return birthdate != null;
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        return password.length() >= 6;
    }

    public void modifyUser(User user) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.UPDATE_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(user.getUsername() + "|");
            clientSocket.dataOut.write(user.getName() + "|");
            clientSocket.dataOut.write(user.getAddress() + "|");
            clientSocket.dataOut.write(user.getBirthday().toString() + "|");
            clientSocket.dataOut.write(user.getEmail() + "|");
            clientSocket.dataOut.write(user.getGender() + "|");
            clientSocket.dataOut.write(user.getTimeCreate() + "|");
            clientSocket.dataOut.write(user.getStatus() + "|");
            clientSocket.dataOut.write(user.getPassword() + "|");
            clientSocket.dataOut.write(user.getId() + "|");
            clientSocket.dataOut.newLine();

            clientSocket.dataOut.flush();

            // read number of users
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void deleteUser(User user) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.DELETE_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(user.getId() + "|");
            clientSocket.dataOut.newLine();

            clientSocket.dataOut.flush();

            // read number of users
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public String[] getLoginTime(User user) {
        ClientSocket clientSocket = ClientSocket.getInstance();
        try {
            // send request to view all users
            clientSocket.dataOut.write(MessageType.VIEW_LOGIN_BY_USER.toString());
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.write(user.getId() + "|");
            clientSocket.dataOut.newLine();
            clientSocket.dataOut.flush();

            // read number of users
            int numLoginTime = clientSocket.dataIn.read();

            ArrayList<String> loginTime = new ArrayList<>();
            for (int i = 0; i < numLoginTime; i++) {
                String userData = clientSocket.dataIn.readLine().trim();
                loginTime.add(userData);
            }
            String[] usersArray = new String[loginTime.size()];
            return loginTime.toArray(usersArray);
        } catch (IOException e) {
            System.err.println(e);
            return null;
        }

    }

}
