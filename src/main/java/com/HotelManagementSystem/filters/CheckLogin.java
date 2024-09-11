package com.HotelManagementSystem.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.HotelManagementSystem.dao.AdminDAO;
import com.HotelManagementSystem.dao.UserDAO;

/**
 * Servlet Filter implementation class CheckLogin
 */
public class CheckLogin extends HttpFilter implements Filter {
       
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		UserDAO fun = null;
		
		try {
			fun = new UserDAO();
	
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String user = req.getParameter("username");
		String pass = req.getParameter("password");
		
		if (user!=null && pass!=null) {
			try {
				if (fun.checkUserExists(user,pass)) {
					chain.doFilter(req, res);
				}
				else {
					res.sendRedirect("signup.jsp");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			res.sendRedirect("signup.jsp");
		}
	}

}
