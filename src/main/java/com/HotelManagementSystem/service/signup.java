package com.HotelManagementSystem.service;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.HotelManagementSystem.dao.UserDAO;
import com.HotelManagementSystem.models.User;

/**
 * Servlet implementation class signup
 */
public class signup extends HttpServlet {
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			UserDAO user = new UserDAO();
			User x = new User();
			x.setName(request.getParameter("name"));
			x.setUserName(request.getParameter("username"));
			x.setPassword(request.getParameter("password"));
			x.setAge(Integer.parseInt(request.getParameter("age")));
			user.addUser(x);
			RequestDispatcher rd = request.getRequestDispatcher("Login");
			rd.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
