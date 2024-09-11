package com.HotelManagementSystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.HotelManagementSystem.models.Room;

public class RoomDAO {
    private Connection conn;

    public RoomDAO() throws ClassNotFoundException {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HotelManagement", "root", "vicky789");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all rooms
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Room");
            while (rs.next()) {
                Room room = new Room();
                room.setRoomid(rs.getString("roomid"));
                room.setCapacity(rs.getString("capacity"));
                room.setFare(rs.getInt("fare"));
                room.setStatus(rs.getString("statusOfRoom"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    
    public List<Room> getAvailableRooms() {
        List<Room> rooms = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Room WHERE statusOfRoom = ?");
            stmt.setString(1, "available");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomid(rs.getString("roomid"));
                room.setCapacity(rs.getString("capacity"));
                room.setFare(rs.getInt("fare"));
                room.setStatus(rs.getString("statusOfRoom"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    // Method to get a room by ID
    public Room getRoomById(String roomid) {
        Room room = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Room WHERE roomid = ?");
            stmt.setString(1, roomid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                room = new Room();
                room.setRoomid(rs.getString("roomid"));
                room.setCapacity(rs.getString("capacity"));
                room.setFare(rs.getInt("fare"));
                room.setStatus(rs.getString("statusOfRoom"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    // Method to add a room
    public void addRoom(Room room) {
        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Room (roomid, capacity, fare, statusOfRoom) VALUES (?, ?, ?, ?)");
            stmt.setString(1, room.getRoomid());
            stmt.setString(2, room.getCapacity());
            stmt.setInt(3, room.getFare());
            stmt.setString(4, room.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update a room
    public void updateRoom(Room room) {
        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE Room SET capacity = ?, fare = ?, statusOfRoom = ? WHERE roomid = ?");
            stmt.setString(1, room.getCapacity());
            stmt.setInt(2, room.getFare());
            stmt.setString(3, room.getStatus());
            stmt.setString(4, room.getRoomid());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a room
    public void deleteRoom(String roomid) {
        try {
            PreparedStatement stmt = conn.prepareStatement("DELETE FROM Room WHERE roomid = ?");
            stmt.setString(1, roomid);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Room> getAvailableRooms(String fromDate, String toDate) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Room WHERE roomid NOT IN ("
                   + "    SELECT roomid FROM Booking WHERE ? < toDate AND ? > fromDate"
                   + ") AND statusOfRoom = 'available'";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the query
            pstmt.setDate(1, Date.valueOf(fromDate));
            pstmt.setDate(2, Date.valueOf(toDate));

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Room room = new Room();
                    room.setRoomid(rs.getString("roomid"));
                    room.setCapacity(rs.getString("capacity"));
                    room.setFare(rs.getInt("fare"));
                    room.setStatus(rs.getString("statusOfRoom"));
                    rooms.add(room);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

	public boolean updateRoom(String roomId, String capacity, int fare, String status) {
		// TODO Auto-generated method stub
		String sql = "UPDATE Room SET capacity = ?, fare = ?, statusOfRoom = ? WHERE roomid = ?";

	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, capacity);
	        stmt.setInt(2, fare);
	        stmt.setString(3, status);
	        stmt.setString(4, roomId);

	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return false;
	}
}
