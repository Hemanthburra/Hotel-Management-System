package com.HotelManagementSystem.dao;

import java.sql.*;
import java.util.*;

import com.HotelManagementSystem.models.Admin;
import com.HotelManagementSystem.models.Booking;
import com.HotelManagementSystem.models.Room;
import com.HotelManagementSystem.models.User;

public class AdminDAO {
    private Connection conn;

    public AdminDAO() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement", "root", "vicky789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateAdmin(String username, String password) {
        boolean isValidAdmin = false;
        try {
            // SQL query to validate admin details
            String sql = "SELECT 1 FROM Admintable WHERE adminusername = ? AND adminpassword = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            System.out.println("admin");
            // If a record is found, admin validation is successful
            isValidAdmin = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValidAdmin;
    }
    // Method to update room status
    public boolean updateRoomStatus(String roomId, String status) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Room SET statusOfRoom = ? WHERE roomid = ?");
            stmt.setString(1, status);
            stmt.setString(2, roomId);
            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update room fare
    public boolean updateRoomFare(String roomId, double newFare) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Room SET fare = ? WHERE roomid = ?");
            stmt.setDouble(1, newFare);
            stmt.setString(2, roomId);
            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to update room capacity
    public boolean updateRoomCapacity(String roomId, String newCapacity) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Room SET capacity = ? WHERE roomid = ?");
            stmt.setString(1, newCapacity);
            stmt.setString(2, roomId);
            int updated = stmt.executeUpdate();
            return updated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get all users (from UserDAO)
    public List<User> getAllUsers() {
        UserDAO userDAO;
        List<User> users = new ArrayList<>();
        try {
            userDAO = new UserDAO();
            users = userDAO.getAllUsers();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to get all bookings (from BookingDAO)
    public List<Booking> getAllBookings() {
        BookingDAO bookingDAO;
        List<Booking> bookings = new ArrayList<>();
        try {
            bookingDAO = new BookingDAO();
            bookings = bookingDAO.getAllBookings();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    public Admin getUserByName(String userName) {
        Admin admin = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Admintable WHERE adminusername = ?"); // Changed to 'userid'
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
            	admin = new Admin();
            	admin.setAdminusername(rs.getString(1));
            	admin.setAdminpassword(rs.getString(2));
            	admin.setAdminname(rs.getString(3));
            	System.out.println(admin.getAdminname()+"jsncjns");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
        return admin;
    }
}
