package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.ThemesDAO;
import com.cybertrend.cpot.entity.Menu;

public class ViewStaticPage extends DefaultAction {
	static Logger logger = Logger.getLogger(ViewStaticPage.class);
	
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		} else { 
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				Menu menu = getMenuAction(action, request);
				request.setAttribute("menu", menu);
				request.getRequestDispatcher(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getPath()+"/viewPage.jsp").forward(request, response);
			} 
		} 
	}
}
