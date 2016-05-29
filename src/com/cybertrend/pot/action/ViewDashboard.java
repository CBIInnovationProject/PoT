package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.DashboardTableauDAO;

public class ViewDashboard extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, String action, String url)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} else { 
			if (Interceptor.isAuthorized(action, request)){
				servletContext.setAttribute("hostName", getHostName());
				servletContext.setAttribute("siteRoot", getCurrentCredentials(request).getSite().getContentUrl().trim().equals("")?"":("/t/"+getCurrentCredentials(request).getSite().getContentUrl().trim()));
				servletContext.setAttribute("dashboardTableau", DashboardTableauDAO.getDashboardByUrl(url));
				servletContext.getRequestDispatcher("/views/viewDashboard.jsp").forward(request, response);
			} 
		} 
	}

}
