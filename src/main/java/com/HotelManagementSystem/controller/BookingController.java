package com.HotelManagementSystem.controller;

import java.util.List;

import com.HotelManagementSystem.dao.BookingDAO;
import com.HotelManagementSystem.models.Booking;

public class BookingController {
	private BookingDAO bookingDAO;

	  public BookingController() {
	    bookingDAO = new BookingDAO();
	  }

	  public List<Booking> getAllBookings() {
	    return bookingDAO.getAllBookings();
	  }

	  public Booking getBookingById(int bookingId) {
	    return bookingDAO.getBookingById(bookingId);
	  }

	  public void addBooking(Booking booking) {
	    bookingDAO.addBooking(booking);
	  }

	  public void updateBooking(Booking booking) {
	    bookingDAO.updateBooking(booking);
	  }

	  public void deleteBooking(int bookingId) {
	    bookingDAO.deleteBooking(bookingId);
	  }
}
