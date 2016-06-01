package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.entity.Menu;

public class ViewDashboard extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} else { 
			if (Interceptor.isAuthorized(action, request)){
				Menu menu = MenuDAO.getMenuByAction(action);
				servletContext.setAttribute("hostName", getHostName());
				servletContext.setAttribute("siteRoot", getCurrentCredentials(request).getSite().getContentUrl().trim().equals("")?"":("/t/"+getCurrentCredentials(request).getSite().getContentUrl().trim()));
				servletContext.setAttribute("menu", menu);
				servletContext.getRequestDispatcher("/views/viewDashboard.jsp").forward(request, response);
			} 
		} 
	}

}
