package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.dao.UserDAO;

public class Login extends DefaultAction{
	
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			boolean AUTH = UserDAO.authenticateUser(username, password);
			if (AUTH) {
				response.sendRedirect("adminPage.cbi");
			} else {
				servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
			} 
			request.getSession().setAttribute(Constants.USER_GA, UserDAO.getUserByUsername(username));
		} catch (SQLException e) {
			servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
