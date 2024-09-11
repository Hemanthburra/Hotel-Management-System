package com.HotelManagementSystem.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.HotelManagementSystem.dao.RoomDAO;
import com.HotelManagementSystem.models.Room;

/**
 * Servlet implementation class CheckAvailabilityServlet
 */
public class CheckAvailabilityServlet extends HttpServlet {
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        
        RoomDAO roomDAO = null;
		try {
			roomDAO = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Room> availableRooms = roomDAO.getAvailableRooms(fromDate, toDate);
        
        request.setAttribute("availableRooms", availableRooms);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserHome.jsp");
        dispatcher.forward(request, response);
//		doGet(request, response);
	}

}
