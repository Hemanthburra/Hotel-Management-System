package com.HotelManagementSystem.service;

import java.io.IOException;
import java.util.List;

import com.HotelManagementSystem.dao.AdminDAO;
import com.HotelManagementSystem.dao.RoomDAO;
import com.HotelManagementSystem.models.Admin;
import com.HotelManagementSystem.models.Room;
import com.HotelManagementSystem.models.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		AdminDAO admin = null;
//		RoomDAO room = null;
//		try {
//			admin = new AdminDAO();
//			room = new RoomDAO();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		String username = request.getParameter("username");
//		System.out.println(username+"kjdbcskjdbvjdks ");
//		Admin s = admin.getUserByName("username");
//		
//		List<User> users = admin.getAllUsers(); 
//		List<Room> rooms = room.getAllRooms();
//		request.setAttribute("adminallusers", users);
//		request.setAttribute("adminallrooms", rooms);
//		request.setAttribute("adminname", s.getAdminname());
		
//		request.setAttribute("adminusername", s.getAdminusername());
		System.out.println("hello");
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AdminDAO admin = null;
		RoomDAO room = null;
		try {
			admin = new AdminDAO();
			room = new RoomDAO();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String username = request.getParameter("adminusername");
		System.out.println(username+"kjdbcskjdbvjdks ");
		Admin s = admin.getUserByName(username);
		
		List<User> users = admin.getAllUsers(); 
		List<Room> rooms = room.getAllRooms();
		request.setAttribute("adminallusers", users);
		request.setAttribute("adminallrooms", rooms);
		request.setAttribute("adminname", s.getAdminname());
		session.setAttribute("adminusername", username);
		session.setAttribute("adminname", s.getAdminname());
		request.setAttribute("adminusername", s.getAdminusername());
		System.out.println("hello");
		session.setAttribute("role", "admin");
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request, response);
		
	}

}
