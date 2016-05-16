package com.cybertrend.pot.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminPage extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException {
		if(getCurrentCredentials(request)!=null){
			request.setAttribute("username", request.getSession().getAttribute("username"));
			servletContext.getRequestDispatcher("/views/adminPage.jsp").forward(request, response);
		} else {
			servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
	}
}
