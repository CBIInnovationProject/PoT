package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.MenuGenerator;
import com.cybertrend.cpot.dao.ThemesDAO;

public class LandingPage extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			servletContext.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			request.getSession().setAttribute("treeMenu", MenuGenerator.treeMenu(request));
			request.getSession().setAttribute("user", getCurrentUser(request));
			servletContext.setAttribute("hostName", getHostName());
			if(getCurrentCredentials(request)!=null) { 
				servletContext.setAttribute("siteRoot", getCurrentSite(request).getContentUrl().trim().equals("")?"":("/t/"+getCurrentSite(request).getContentUrl().trim()));
				servletContext.getRequestDispatcher(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getPath()+"/landingPage.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
			}
		}
	}
}
