package com.HotelManagementSystem.service;

import java.io.IOException;

import com.HotelManagementSystem.dao.RoomDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateRoomServlet
 */
public class UpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomId = request.getParameter("roomId");
        String capacity = request.getParameter("capacity");
        int fare = (int)Double.parseDouble(request.getParameter("fare"));
        String status = request.getParameter("status");

        RoomDAO roomDAO = null;
		try {
			roomDAO = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block	
			e.printStackTrace();
		}
        boolean isUpdated = roomDAO.updateRoom(roomId, capacity, fare, status);

        String message = isUpdated ? "Room updated successfully!" : "Failed to update room!";
        request.setAttribute("message", message);

        request.getRequestDispatcher("ViewRoomsServlet").forward(request, response);
	}

}
