package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.ThemesDAO;

public class LandingPage extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			servletContext.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				servletContext.setAttribute("treeMenu", treeMenu(request));
				servletContext.setAttribute("user", getCurrentUser(request));
				servletContext.setAttribute("hostName", getHostName());
				servletContext.setAttribute("siteRoot", getCurrentCredentials(request).getSite().getContentUrl().trim().equals("")?"":("/t/"+getCurrentCredentials(request).getSite().getContentUrl().trim()));
				servletContext.getRequestDispatcher(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getPath()+"/landingPage.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
}
