package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;

public class LandingPage extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			treeMenu(request, response, servletContext);
			servletContext.setAttribute("user", getCurrentUser(request));
			servletContext.getRequestDispatcher("/views/landingPage.jsp").forward(request, response);
		}
	}
}
