package com.btv.Server.socket;

import com.btv.Server.database.AdminHandleDB;
import com.btv.Server.database.ChatDB;
import com.btv.Server.helpers.AdminMessage;
import com.btv.Server.model.Friends;
import com.btv.Server.model.Group;
import com.btv.Server.model.Login;
import com.btv.Server.model.OnlineUser;
import com.btv.Server.model.Spam;
import com.btv.Server.model.User;
import java.io.IOException;
import java.net.Socket;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tvan
 */
public class AdminHandler extends ClientHandler {

    public static ArrayList<AdminHandler> adminHandlers = new ArrayList<>();

    public AdminHandler(Socket clientSocket) {
        super(clientSocket);
        adminHandlers.add(this);
    }

    public void handleMessage(String messStr) {
        AdminMessage mess = AdminMessage.valueOf(messStr);
        AdminHandleDB db = AdminHandleDB.getDBInstance();
        switch (mess) {
            case VIEW_USERS:
                ArrayList<User> allUsers = db.getAllUsers();
                try {
                    // send number of users
                    dataOut.write(allUsers.size());

                    // send data of all users
                    for (User user : allUsers) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getUsername() + "|");
                        dataOut.write(user.getName() + "|");
                        dataOut.write(user.getAddress() + "|");
                        dataOut.write(user.getBirthday().toString() + "|");
                        dataOut.write(user.getGenderStr() + "|");
                        dataOut.write(user.getEmail() + "|");
                        dataOut.write(user.getTimeCreate().toString() + "|");
                        dataOut.write(user.getStatus() + "|");
                        dataOut.write(user.getPassword() + "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case VIEW_NEW_USERS:
                ArrayList<User> allNewUsers = db.GetAllNewUsers();
                try {
                    // send number of users
                    dataOut.write(allNewUsers.size());

                    // send data of all users
                    for(User user : allNewUsers) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getUsername() + "|");
                        dataOut.write(user.getName() + "|");
                        dataOut.write(user.getTimeCreate()+ "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case ADD_USER:
                try {
                    String userData = dataIn.readLine();

                    String[] split = userData.split("\\|");
                    db.addUser(split);

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case UPDATE_USER:
                try {
                    String userData = dataIn.readLine();

                    String[] split = userData.split("\\|");
                    db.modifyUser(split);

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case DELETE_USER:
                try {
                    String userData = dataIn.readLine();
                    String[] split = userData.split("\\|");
//                    System.out.println(split[0]);
                    db.deleteUser(split[0]);
                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case VIEW_LOGIN_BY_USER:
                try {
                    String userData = dataIn.readLine();
                    String[] split = userData.split("\\|");
//                    ArrayList<Date> allLoginTime = db.getLoginTime(split[0]);

                    ArrayList<Date> allLoginTime = db.getLoginTime(split[0]);
                    dataOut.write(allLoginTime.size());
                    for (Date loginTime : allLoginTime) {
                        dataOut.write(loginTime.toString());
                        dataOut.newLine();
                    }
                    dataOut.flush();

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case VIEW_FRIENDS_BY_USER:
                try {
                    String userData = dataIn.readLine();
                    String[] split = userData.split("\\|");

                    ArrayList<String> allFriendName = db.getFriendName(split[0]);
                    dataOut.write(allFriendName.size());
                    for (String name : allFriendName) {
                        dataOut.write(name);
                        dataOut.newLine();
                    }
                    dataOut.flush();

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case VIEW_LOGINS:
                ArrayList<User> allUserLogin = db.getAllUsersLogin();
                try {
                    dataOut.write(allUserLogin.size());

                    for (User user : allUserLogin) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getUsername() + "|");
                        dataOut.write(user.getName() + "|");
                        dataOut.write(user.getLoginDate()+ "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case VIEW_USER_FRIEND:
                ArrayList<Friends> allUsersFriends = db.GetAllFriends();
                try{
                    dataOut.write(allUsersFriends.size());
                    
                    for(Friends friends : allUsersFriends){
                        dataOut.write(friends.getId() + "|");
                        dataOut.write(friends.getName()+ "|");
                        dataOut.write(friends.getTimeCreate()+ "|");
                        dataOut.write(friends.getDirectFriends()+ "|");
                        dataOut.write(friends.getIndirectFriends()+ "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e){
                    System.out.println(e);
                }
                break;
            case VIEW_GROUPS:
                ArrayList<Group> allGroups = db.getAllGroups();
                try {
                    // send number of users
                    dataOut.write(allGroups.size());

                    // send data of all users
                    for (Group group : allGroups) {
                        dataOut.write(group.getId() + "|");
                        dataOut.write(group.getGroupName()+ "|");
                        dataOut.write(group.getTimeCreate()+ "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case VIEW_MEMBERS_GROUP:
                try {
                    String userData = dataIn.readLine();
                    String[] split = userData.split("\\|");

                    ArrayList<String> groupMember = db.getGroupMember(split[0]);
                    dataOut.write(groupMember.size());
                    for (String member : groupMember) {
                        dataOut.write(member);
                        dataOut.newLine();
                    }
                    dataOut.flush();

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case VIEW_ADMINS_GROUP:
                try {
                    String userData = dataIn.readLine();
                    String[] split = userData.split("\\|");

                    ArrayList<String> groupAdmin = db.getGroupAdmin(split[0]);
                    dataOut.write(groupAdmin.size());
                    for (String admin : groupAdmin) {
                        dataOut.write(admin);
                        dataOut.newLine();
                    }
                    dataOut.flush();

                } catch (IOException | SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case VIEW_SPAMS:
                ArrayList<Spam> allSpams = db.getAllSpams();
                try {
                    // send number of users
                    dataOut.write(allSpams.size());

                    // send data of all users
                    for (Spam spam : allSpams) {
                        dataOut.write(spam.getSpamId()+ "|");
                        dataOut.write(spam.getReporter()+ "|");
                        dataOut.write(spam.getSpamTime()+ "|");
                        dataOut.write(spam.getReportedUsername()+ "|");
                        dataOut.write(spam.getIsLocked()+ "|");

                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
                
            case VIEW_ONLINE_USERS:                
                try {
                    String dates = dataIn.readLine();
//                    System.out.println(dates);
                    String[] split = dates.split("\\|");
                    
                    ArrayList<OnlineUser> allOnlineUsers = db.GetAllOnlineUsers(split);
                    
                    try{
                        dataOut.write(allOnlineUsers.size());
                        
                        for(OnlineUser OnlineUser : allOnlineUsers) {
                            dataOut.write(OnlineUser.getId() + "|");
                            dataOut.write(OnlineUser.getUsername()+ "|");
                            dataOut.write(OnlineUser.getTimeCreate()+ "|");
                            dataOut.write(OnlineUser.getLoginTime()+ "|");
                            dataOut.write(OnlineUser.getuserChatWith()+ "|");
                            dataOut.write(OnlineUser.getgroupChatWith()+ "|");
                            dataOut.newLine();
                        }
                        dataOut.flush();
                    } catch (IOException e) {
                        System.out.println(e);
                    }

                } catch (IOException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case LOCK_USER:                
                try {
                    int spamId = dataIn.read();
                    
                    db.lockUserSpam(spamId);

                } catch (IOException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
                
            case VIEW_LOGINS_LOG:
                ArrayList<Login> allLogins = db.GetAllLogins();
                try {
                    // send number of users
                    dataOut.write(allLogins.size());

                    // send data of all users
                    for(Login user : allLogins) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getLoginDate()+ "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Invalid message");
        }
    }

    @Override
    protected void removeClientFromList() {
        adminHandlers.remove(this);
    }
}