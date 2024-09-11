package com.HotelManagementSystem.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

import com.HotelManagementSystem.dao.BookingDAO;
import com.HotelManagementSystem.models.Booking;

public class BookRoom extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
        String roomid = request.getParameter("roomid");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        String totalFare = request.getParameter("totalFare");
        
        int roomID = Integer.parseInt(roomid);
        double fare = Double.parseDouble(totalFare);
        LocalDate fromDateParsed = LocalDate.parse(fromDate);
        LocalDate toDateParsed = LocalDate.parse(toDate);
        
		HttpSession session = request.getSession();
		BookingDAO book = null;
		try {
			book = new BookingDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Booking booked = new Booking();
		booked.setUserId(userid);
		booked.setRoomId(roomid);
		booked.setFromDate(fromDateParsed);
		booked.setToDate(toDateParsed);
		booked.setTotalFare(fare);
		
		boolean inserted = book.addBooking(booked);
		
		 if (inserted) {
             request.setAttribute("message", "Room booked successfully!");
         } else {
             request.setAttribute("message", "Booking failed!");
         }
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("UserHome.jsp");
        dispatcher.forward(request, response);
		doGet(request, response);
	}

}
