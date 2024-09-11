package com.HotelManagementSystem.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Booking {

    private int bookingId;
    private String userId;
    private  String roomId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Double totalFare;
    private Date bookingDate;

    // Default constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(int bookingId, String userId, String roomId, LocalDate fromDate, LocalDate toDate, Double totalFare, Date bookingDate) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomId = roomId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.totalFare = totalFare;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDateParsed) {
        this.fromDate = fromDateParsed;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDateParsed) {
        this.toDate = toDateParsed;
    }

    public Double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double fare) {
        this.totalFare = fare;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Optionally, you can override toString() for debugging purposes
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId='" + userId + '\'' +
                ", roomId=" + roomId +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", totalFare=" + totalFare +
                ", bookingDate=" + bookingDate +
                '}';
    }

	

	
}
