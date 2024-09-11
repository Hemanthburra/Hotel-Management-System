package com.HotelManagementSystem.service;

import java.io.IOException;
import java.util.List;

import com.HotelManagementSystem.dao.AdminDAO;
import com.HotelManagementSystem.dao.BookingDAO;
import com.HotelManagementSystem.models.Booking;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class BookedRoomServlet
 */
public class BookedRoomServlet extends HttpServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		BookingDAO book = null;
		AdminDAO admin = null;
		try {
			admin = new AdminDAO();
			book = new BookingDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (session.getAttribute("user") != null) {
			System.out.println((String)session.getAttribute("user"));
			List<Booking> allRooms = book.getBookingsByUser((String)session.getAttribute("user"));
			request.setAttribute("BookedRoomsByUser", allRooms);
			request.getRequestDispatcher("BookedRooms.jsp").forward(request, response);
		}
		else {
			List<Booking> allroomsofusers = admin.getAllBookings();
//			System.out.println((String)session.getAttribute("adminusername"));
			request.setAttribute("BookedRoomsByUser", allroomsofusers);
			request.getRequestDispatcher("BookedRooms.jsp").forward(request, response);
		}
		
	}
}
