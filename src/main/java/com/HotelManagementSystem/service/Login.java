package com.HotelManagementSystem.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.HotelManagementSystem.dao.AdminDAO;
import com.HotelManagementSystem.dao.RoomDAO;
import com.HotelManagementSystem.dao.UserDAO;
import com.HotelManagementSystem.models.Room;

/**
 * Servlet implementation class mainPage
 */

public class Login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get method");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
//		PrintWriter p = response.getWriter();
//		p.println(username);
		UserDAO user = null;
		try {
			user = new UserDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RoomDAO r = null;
		try {
			r = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Room> l = r.getAvailableRooms();
		String x = user.getUnameByUsername(username);
		System.out.println(x+"username");
		HttpSession session1 = request.getSession();
		session1.setAttribute("user", x);
		session1.setAttribute("userrole", "user");
		request.setAttribute("availableRooms", l);
		RequestDispatcher rd = request.getRequestDispatcher("UserHome.jsp");
		rd.forward(request, response);
//		doGet(request, response);
	}	

}
