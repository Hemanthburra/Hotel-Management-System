package com.HotelManagementSystem.service;

import java.io.IOException;

import com.HotelManagementSystem.dao.RoomDAO;
import com.HotelManagementSystem.models.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddRoomServlet
 */
public class AddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String roomid = request.getParameter("roomid");
		String capacity = request.getParameter("capacity");
		int fare = (int) Double.parseDouble(request.getParameter("fare"));
		String status = request.getParameter("status");
		Room room = new Room(roomid,capacity,fare,status);
		RoomDAO x = null;
		try {
			x = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x.addRoom(room);
		request.getRequestDispatcher("ViewRoomsServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
