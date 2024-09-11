package com.HotelManagementSystem.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.HotelManagementSystem.models.User;

public class UserDAO {
	
    private Connection conn;

    public UserDAO() throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement", "root", "vicky789");
        } catch (SQLException e) {	
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Customers");
            while (rs.next()) {
                User user = new User();
                user.setUserName(rs.getString("userid")); // Changed to match 'userid'
                user.setPassword(rs.getString("pass"));
                user.setName(rs.getString("uname")); // Changed to match 'uname'
                user.setAge(rs.getInt("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User getUserByName(String userName) {
        User user = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Customers WHERE userid = ?"); // Changed to 'userid'
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserName(rs.getString("userid")); // Changed to 'userid'
                user.setPassword(rs.getString("pass"));
                user.setName(rs.getString("uname")); // Changed to 'uname'
                user.setAge(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void addUser(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Customers (userid, pass, uname, age) VALUES (?, ?, ?, ?)"); // Changed to match table columns
            stmt.setString(1, user.getUserName()); // Changed to 'userid'
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName()); // Changed to 'uname'
            stmt.setInt(4, user.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Customers SET pass = ?, uname = ?, age = ? WHERE userid = ?"); // Changed to match table columns
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName()); // Changed to 'uname'
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getUserName()); // Changed to 'userid'
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(String userName) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Customers WHERE userid = ?"); // Changed to 'userid'
            stmt.setString(1, userName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean checkUserExists(String userId, String password) {
        boolean exists = false;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT 1 FROM Customers WHERE userid = ? and pass = ?");
            stmt.setString(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            exists = rs.next(); // If a result is returned, the user exists
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
    public String getUnameByUsername(String username) {
        String uname = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT uname FROM Customers WHERE userid = ?");
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                uname = rs.getString("uname");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uname;
    }
}
