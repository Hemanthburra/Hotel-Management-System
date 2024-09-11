package com.HotelManagementSystem.dao;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import com.HotelManagementSystem.models.Booking;

public class BookingDAO {
    private Connection conn;

    public BookingDAO() throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement", "root", "vicky789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Booking");
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingid"));
                booking.setUserId(rs.getString("userid"));
                booking.setRoomId(rs.getString("roomid"));
                booking.setFromDate(rs.getDate("fromDate").toLocalDate());
                booking.setToDate(rs.getDate("toDate").toLocalDate());
                booking.setTotalFare(rs.getDouble("totalFare"));
                booking.setBookingDate(rs.getTimestamp("bookingdate"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    // Method to get a booking by its ID
    public Booking getBookingById(int bookingId) {
        Booking booking = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Booking WHERE bookingid = ?");
            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                booking = new Booking();
                booking.setBookingId(rs.getInt("bookingid"));
                booking.setUserId(rs.getString("userid"));
                booking.setRoomId(rs.getString("roomid"));
                booking.setFromDate(rs.getDate("fromDate").toLocalDate());
                booking.setToDate(rs.getDate("toDate").toLocalDate());
                booking.setTotalFare(rs.getDouble("totalFare"));
                booking.setBookingDate(rs.getTimestamp("bookingdate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booking;
    }

    // Method to add a new booking
    public boolean addBooking(Booking booking) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO Booking (userid, roomid, fromDate, toDate, totalFare) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, booking.getUserId());
            stmt.setString(2, booking.getRoomId());
            stmt.setDate(3, Date.valueOf(booking.getFromDate()));  // Converting LocalDate to SQL Date
            stmt.setDate(4, Date.valueOf(booking.getToDate()));    // Converting LocalDate to SQL Date
            stmt.setDouble(5, booking.getTotalFare());
            int inserted = stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return false;
    }

    // Method to update an existing booking
    public void updateBooking(Booking booking) {
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "UPDATE Booking SET userid = ?, roomid = ?, fromDate = ?, toDate = ?, totalFare = ? WHERE bookingid = ?");
            stmt.setString(1, booking.getUserId());
            stmt.setString(2, booking.getRoomId());
            stmt.setDate(3, Date.valueOf(booking.getFromDate()));  // Converting LocalDate to SQL Date
            stmt.setDate(4, Date.valueOf(booking.getToDate()));    // Converting LocalDate to SQL Date
            stmt.setDouble(5, booking.getTotalFare());
            stmt.setInt(6, booking.getBookingId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a booking using userid and roomid
    public boolean deleteBooking(String userId, int roomId) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Booking WHERE userid = ? AND bookingid = ?");
            stmt.setString(1, userId);
            stmt.setInt(2, roomId);
            stmt.executeUpdate();
            System.out.print(userId+" "+roomId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Booking> getBookingsByUser(String userId) {
        List<Booking> bookings = new ArrayList<>();
        try {
            // Prepare SQL query to fetch bookings for a specific user
            String sql = "SELECT * FROM Booking WHERE userid = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId); // Set the userId in the query

            ResultSet rs = pstmt.executeQuery();
            
            // Iterate through the result set and populate the list of bookings
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingid"));
                booking.setUserId(rs.getString("userid"));
                booking.setRoomId(rs.getString("roomid"));
                booking.setFromDate(rs.getDate("fromDate").toLocalDate());
                booking.setToDate(rs.getDate("toDate").toLocalDate());
                booking.setTotalFare(rs.getDouble("totalFare"));
                booking.setBookingDate(rs.getTimestamp("bookingdate"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
    public boolean deleteBookingByRoomId(int roomId) {
        String sql = "DELETE FROM Booking WHERE bookingid = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the roomId parameter in the SQL query
            stmt.setInt(1, roomId);
            
            // Execute the query and check how many rows were affected
            int rowsAffected = stmt.executeUpdate();
            
            // If one or more rows were deleted, return true
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // If something went wrong, return false
        return false;
    }

}
