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
                    for (User user : allNewUsers) {
                        dataOut.write(user.getId() + "|");
                        dataOut.write(user.getUsername() + "|");
                        dataOut.write(user.getName() + "|");
                        dataOut.write(user.getTimeCreate() + "|");
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
                db.adminHandleDB.addUser(split);

            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case UPDATE_USER:
                try {
                String userData = dataIn.readLine();

                String[] split = userData.split("\\|");
                db.adminHandleDB.modifyUser(split);

            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case DELETE_USER:
                try {
                String userData = dataIn.readLine();
                String[] split = userData.split("\\|");
                System.out.println(split[0]);
                db.adminHandleDB.deleteUser(split[0]);
            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case VIEW_LOGIN_BY_USER:
                try {
                String userData = dataIn.readLine();
                String[] split = userData.split("\\|");
//                    ArrayList<Date> allLoginTime = db.getLoginTime(split[0]);

                ArrayList<Date> allLoginTime = db.adminHandleDB.getLoginTime(split[0]);
                dataOut.write(allLoginTime.size());
                for (Date loginTime : allLoginTime) {
                    dataOut.write(loginTime.toString());
                    dataOut.newLine();
                }
                dataOut.flush();

            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case VIEW_FRIENDS_BY_USER:
                try {
                String userData = dataIn.readLine();
                String[] split = userData.split("\\|");

                ArrayList<String> allFriendName = db.adminHandleDB.getFriendName(split[0]);
                dataOut.write(allFriendName.size());
                for (String name : allFriendName) {
                    dataOut.write(name.toString());
                    dataOut.newLine();
                }
                dataOut.flush();

            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
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
                        dataOut.write(user.getLoginDate() + "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case VIEW_USER_FRIEND:
                ArrayList<Friends> allUsersFriends = db.GetAllFriends();
                try {
                    dataOut.write(allUsersFriends.size());

                    for (Friends friends : allUsersFriends) {
                        dataOut.write(friends.getId() + "|");
                        dataOut.write(friends.getName() + "|");
                        dataOut.write(friends.getTimeCreate() + "|");
                        dataOut.write(friends.getDirectFriends() + "|");
                        dataOut.write(friends.getIndirectFriends() + "|");
                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
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
                        dataOut.write(group.getGroupName() + "|");
                        dataOut.write(group.getTimeCreate() + "|");
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

                ArrayList<String> groupMember = db.adminHandleDB.getGroupMember(split[0]);
                dataOut.write(groupMember.size());
                for (String member : groupMember) {
                    dataOut.write(member.toString());
                    dataOut.newLine();
                }
                dataOut.flush();

            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case VIEW_ADMINS_GROUP:
                try {
                String userData = dataIn.readLine();
                String[] split = userData.split("\\|");

                ArrayList<String> groupAdmin = db.adminHandleDB.getGroupAdmin(split[0]);
                dataOut.write(groupAdmin.size());
                for (String admin : groupAdmin) {
                    dataOut.write(admin.toString());
                    dataOut.newLine();
                }
                dataOut.flush();

            } catch (IOException ex) {
                Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
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
                        dataOut.write(spam.getSpamId() + "|");
                        dataOut.write(spam.getSpamUsername() + "|");
                        dataOut.write(spam.getSpamTime() + "|");
                        dataOut.write(spam.getSpamName() + "|");
                        dataOut.write(spam.isBlocked() + "|");

                        dataOut.newLine();
                    }
                    dataOut.flush();
                } catch (IOException e) {
                    System.out.println(e);
                }
                break;
            case SPAM_USER: {
                try {
                    String userData = dataIn.readLine();

                    String[] split = userData.split("\\|");
                    db.adminHandleDB.blockedUser(split);

                } catch (IOException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(AdminHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
            default:
                System.out.println("Invalid message");
        }
    }

    @Override
    protected void removeClientFromList() {
        adminHandlers.remove(this);
    }
}
