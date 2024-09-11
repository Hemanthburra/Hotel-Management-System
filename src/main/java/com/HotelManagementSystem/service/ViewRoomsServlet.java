package com.HotelManagementSystem.service;

import java.io.IOException;
import java.util.List;

import com.HotelManagementSystem.dao.RoomDAO;
import com.HotelManagementSystem.models.Room;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewRoomsServlet
 */
public class ViewRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewRoomsServlet() {
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
		RoomDAO roomDAO = null;
		try {
			roomDAO = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<Room> rooms = null;

        rooms = roomDAO.getAllRooms();

        // Set rooms in request attribute
        request.setAttribute("rooms", rooms);

        // Forward the request to the JSP page
        request.getRequestDispatcher("ViewRooms.jsp").forward(request, response);
	}

}
