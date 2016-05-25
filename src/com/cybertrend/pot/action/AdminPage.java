package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;

public class AdminPage extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException {
		if(request.getSession().getAttribute(Constants.USER_GA)!=null){
			try {
				treeMenu(request, response, servletContext);
				servletContext.setAttribute("user", getCurrentUser(request));
				servletContext.getRequestDispatcher("/views/adminPage.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
	}
}
