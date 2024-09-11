package com.HotelManagementSystem.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.HotelManagementSystem.dao.BookingDAO;

/**
 * Servlet implementation class CancelBookingServlet
 */
public class CancelBookingServlet extends HttpServlet {
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookingId = Integer.parseInt(request.getParameter("bookingId"));
		
		// Create BookingDAO instance
		HttpSession session = request.getSession();
		BookingDAO bookingDAO = null;
		try {
			bookingDAO = new BookingDAO();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		boolean isDeleted ;
		String message = "";
		// Delete the booking using BookingDAO
		if ("admin".equals((String)session.getAttribute("role"))) {
			isDeleted = bookingDAO.deleteBookingByRoomId(bookingId);
			message = isDeleted ? "Booking for room ID " + bookingId + " cancelled successfully by admin!" 
			                    : "Failed to cancel booking for room ID " + bookingId + " as admin.";
			request.setAttribute("cancelMessage", message);
			request.setAttribute("BookedRoomsByUser", bookingDAO.getAllBookings());
			
		}
		else if("user".equals((String)session.getAttribute("userrole"))){
			isDeleted = bookingDAO.deleteBooking((String)session.getAttribute("user"), bookingId);
			message = isDeleted ? "Your booking for room ID " + bookingId + " cancelled successfully!" 
			                    : "Failed to cancel your booking for room ID " + bookingId + ".";
			String userId = (String) session.getAttribute("user");
			request.setAttribute("cancelMessage", message);
			request.setAttribute("BookedRoomsByUser", bookingDAO.getBookingsByUser(userId));
		}
		
		
		// Create a message based on the outcome
	
		
		request.getRequestDispatcher("BookedRooms.jsp").forward(request, response);
	}

}
