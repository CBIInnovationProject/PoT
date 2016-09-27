package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.ThemesDAO;
import com.cybertrend.cpot.entity.Menu;

public class ViewDashboard extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} else { 
			if (Interceptor.isAuthorized(action, request)){
				Menu menu = getMenuAction(action, request);
				request.setAttribute("menuName", menu.getName());
				request.setAttribute("url", menu.getContent());
				request.getRequestDispatcher(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getPath()+"/workbook/viewDashboard.jsp").forward(request, response);
			} 
		} 
	}

}
