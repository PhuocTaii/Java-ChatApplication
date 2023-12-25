package com.btv.Server.database;

import com.btv.Server.model.Friends;
import com.btv.Server.model.Group;
import com.btv.Server.model.Login;
import com.btv.Server.model.OnlineUser;
import com.btv.Server.model.Spam;
import com.btv.Server.model.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Vo Quoc Binh
 */
public class AdminHandleDB extends ChatDB{

//    private Connection connection;
    private static AdminHandleDB dbInstance = null;


    private AdminHandleDB() {
        super();
    }
    
    public static AdminHandleDB getDBInstance() {
       if (dbInstance == null) {
           dbInstance = new AdminHandleDB();
       }
       return dbInstance;
   }  

    public ArrayList<User> getAllUsers() {
        ArrayList<User> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM User";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User tempUser = new User();
                tempUser.setId(rs.getInt("u_id"));
                tempUser.setUsername(rs.getString("username"));
                tempUser.setName(rs.getString("u_name"));
                tempUser.setAddress(rs.getString("address"));
                tempUser.setBirthday(rs.getDate("birthday"));
                tempUser.setEmail(rs.getString("email"));
                tempUser.setGender(rs.getBoolean("gender"));
                tempUser.setTimeCreate(rs.getDate("time_create"));
                tempUser.setStatus(rs.getString("u_status"));
                tempUser.setPassword(rs.getString("u_password"));
                resList.add(tempUser);
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
        return resList;
    }

    public void addUser(String[] split) throws SQLException {
        String query = """
                       INSERT INTO User (username, u_name, address, birthday, email, gender, time_create, u_status, u_password)
                       VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?);""";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        //username
        preparedStatement.setString(1, split[0]);
        //name
        preparedStatement.setString(2, split[1]);
        //address
        preparedStatement.setString(3, split[2]);
        //birthday
        preparedStatement.setString(4, split[3]);
        //email,
        preparedStatement.setString(5, split[4]);
        //gender
        preparedStatement.setString(6, split[5]);
        //time_create
        preparedStatement.setString(7, split[6]);
        //u_status
        preparedStatement.setString(8, split[7]);
        //u_password
        preparedStatement.setString(9, split[8]);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void modifyUser(String[] split) throws SQLException {
        String query = "UPDATE User SET username = ?, u_name = ?, address = ?, birthday = ?, email = ?, gender = ?, u_status = ?, u_password = ? WHERE u_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        //username
        preparedStatement.setString(1, split[0]);
        //name
        preparedStatement.setString(2, split[1]);
        //address
        preparedStatement.setString(3, split[2]);
        //birthday
        preparedStatement.setString(4, split[3]);
        //email,
        preparedStatement.setString(5, split[4]);
        //gender
        preparedStatement.setString(6, split[5]);
        //u_status
        preparedStatement.setString(7, split[7]);
        //u_password
        preparedStatement.setString(8, split[8]);
        //u_id
        preparedStatement.setString(9, split[9]);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deleteUser(String u_id) throws SQLException {
        String query = "DELETE FROM User WHERE u_id = ?;";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        //u_id
        preparedStatement.setString(1, u_id);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public ArrayList<Date> getLoginTime(String userId) throws SQLException {
        String query = "SELECT login_time FROM Logins WHERE u_id = ?";
        ArrayList<Date> loginTimeList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Date date = rs.getDate("login_time");
                    loginTimeList.add(date);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or throw it to the calling method
            System.err.println(e);
            throw e; // or handle it according to your application's logic
        }

        return loginTimeList;
    }

    public ArrayList<String> getFriendName(String userId) throws SQLException {
        String query = """
                       SELECT u2.u_name AS friend_name
                       FROM Friends f
                       INNER JOIN User u1 ON f.u_id1 = u1.u_id
                       INNER JOIN User u2 ON f.u_id2 = u2.u_id
                       WHERE u1.u_id = ?
                       """;
        ArrayList<String> nameList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("friend_name");
                    nameList.add(name);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or throw it to the calling method
            System.err.println(e);
            throw e; // or handle it according to your application's logic
        }

        return nameList;
    }

    public ArrayList<User> getAllUsersLogin() {
        ArrayList<User> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String sql = """
            SELECT user.u_id, username, u_name, login_time
            FROM User user
            JOIN Logins login ON user.u_id = login.u_id
            ORDER BY login_time DESC
            """;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User tempUser = new User();
                tempUser.setId(rs.getInt("u_id"));
                tempUser.setUsername(rs.getString("username"));
                tempUser.setName(rs.getString("u_name"));
                tempUser.setLoginDate(rs.getDate("login_time"));

                resList.add(tempUser);
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return resList;
    }

    public ArrayList<Group> getAllGroups() {
        ArrayList<Group> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String sql = "SELECT * FROM ChatGroups";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Group tempUser = new Group();
                tempUser.setId(rs.getInt("gr_id"));
                tempUser.setGroupName(rs.getString("gr_name"));
                tempUser.setTimeCreate(rs.getDate("created_time"));

                resList.add(tempUser);
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return resList;
    }

    public ArrayList<String> getGroupMember(String groupID) throws SQLException {
        String query = """
                       SELECT u_name
                       FROM GroupMembers groupMem
                       JOIN User user ON user.u_id = groupMem.u_id
                       WHERE groupMem.gr_id = ?
                       """;
        ArrayList<String> memberList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, groupID);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("u_name");
                    memberList.add(name);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or throw it to the calling method
            System.err.println(e);
            throw e; // or handle it according to your application's logic
        }

        return memberList;
    }

    public ArrayList<String> getGroupAdmin(String groupID) throws SQLException {
        String query = """
                       SELECT u_name
                       FROM GroupMembers groupMem
                       JOIN User user ON user.u_id = groupMem.u_id
                       WHERE groupMem.gr_id = ? AND is_admin=1
                       """;
        ArrayList<String> adminList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, groupID);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("u_name");
                    adminList.add(name);
                }
            }
        } catch (SQLException e) {
            // Handle the exception or throw it to the calling method
            System.err.println(e);
            throw e; // or handle it according to your application's logic
        }

        return adminList;
    }

    public ArrayList<Spam> getAllSpams() {
        ArrayList<Spam> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();

            String sql = """
                         SELECT spam_id, username, report_time, u_name
                         FROM SpamList spam
                         JOIN User user ON spam.reported_id = user.u_id
            """;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Spam tempSpam = new Spam();
                tempSpam.setSpamId(rs.getInt("spam_id"));
                tempSpam.setSpamUsername(rs.getString("username"));
                tempSpam.setSpamTime(rs.getDate("report_time"));
                tempSpam.setSpamName(rs.getString("u_name"));

                resList.add(tempSpam);
            }

            stmt.close();

        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }

        return resList;
    }


    public ArrayList<User> GetAllNewUsers() {
        ArrayList<User> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = "select u_id, username, u_name, time_create from User order by time_create desc";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                User tempUser = new User();
                tempUser.setId(rs.getInt("u_id"));
                tempUser.setUsername(rs.getString("username"));
                tempUser.setName(rs.getString("u_name"));
                tempUser.setTimeCreate(rs.getDate("time_create"));
                resList.add(tempUser);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
        return resList;
    }

    public ArrayList<Login> GetAllLogins() {
        ArrayList<Login> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = "SELECT * FROM Logins;";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Login tempLogin = new Login();
                tempLogin.setLoginDate(rs.getDate("login_time"));
                tempLogin.setId(rs.getInt("u_id"));
                resList.add(tempLogin);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
        return resList;
    }

    public ArrayList<Friends> GetAllFriends() {
        ArrayList<Friends> resList = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            String sql = """
                         select u_id, u_name, time_create, count(distinct(f.u_id2)) as 'direct_friends', count(distinct(f2.u_id2)) as 'indirect_friends'
                         from User u 
                         left join Friends f on u.u_id = f.u_id1 
                         left join Friends f2 on u.u_id = f2.u_id1 OR u.u_id = f2.u_id2
                         group by u.u_id, u.u_name
                         order by u.u_id""";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Friends tempFriends = new Friends();
                tempFriends.setId(rs.getInt("u_id"));
                tempFriends.setName(rs.getString("u_name"));
                tempFriends.setTimeCreate(rs.getDate("time_create"));
                tempFriends.setDirectFriends(rs.getInt("direct_friends"));
                tempFriends.setIndirectFriends(rs.getInt("indirect_friends"));
                resList.add(tempFriends);
            }
            stmt.close();
        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
        return resList;
    }

    public ArrayList<OnlineUser> GetAllOnlineUsers(String[] split) {
        String query = " SELECT\n    U.u_id,\n    U.u_name,\n    U.username,\n    COUNT(DISTINCT L.id) AS app_open_count,\n    COUNT(DISTINCT C.receive_id) AS personal_chat_count,\n    COUNT(DISTINCT CH.group_id) AS group_chat_count\n FROM\n    User U\n LEFT JOIN\n    Logins L ON U.u_id = L.u_id AND L.login_time BETWEEN ? AND ?\n LEFT JOIN\n    ChatHistory C ON U.u_id = C.receive_id AND C.sendtime BETWEEN ? AND ?\n LEFT JOIN\n    ChatHistory CH ON U.u_id = CH.send_id AND CH.sendtime BETWEEN ? AND ?\n GROUP BY\n    U.u_id, U.u_name, U.username;\n";
        ArrayList<OnlineUser> resList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, split[0]);
            preparedStatement.setString(3, split[0]);
            preparedStatement.setString(5, split[0]);
            preparedStatement.setString(2, split[1]);
            preparedStatement.setString(4, split[1]);
            preparedStatement.setString(6, split[1]);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    OnlineUser tempOnlUser = new OnlineUser();
                    tempOnlUser.setId(rs.getInt("u_id"));
                    tempOnlUser.setName(rs.getString("u_name"));
                    tempOnlUser.setUsername(rs.getString("username"));
                    tempOnlUser.setloginTimes(rs.getInt("app_open_count"));
                    tempOnlUser.setuserChatWith(rs.getInt("personal_chat_count"));
                    tempOnlUser.setgroupChatWith(rs.getInt("group_chat_count"));
                    resList.add(tempOnlUser);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }
}
