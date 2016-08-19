package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.UserDAO;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.entity.UserTableau;
import com.cybertrend.pot.util.PropertyLooker;

import tableau.api.rest.bindings.TableauCredentialsType;

public class Login extends DefaultAction{
	
	public static void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean AUTH = UserDAO.authenticateUser(request.getParameter("userId"));
		if (AUTH || Interceptor.isLogin(request)==true) {
			response.sendRedirect("landingPage.cbi");
		} else {
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} 
		User user = UserDAO.getUserById(request.getParameter("userId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String contentUrl = request.getParameter("siteContentUrl").trim()+"";
		TableauCredentialsType credentials = getTableauService().invokeSignIn(username, password, contentUrl).getCredentials();
		request.getSession().setAttribute(Constants.USER_GA, user);
		request.getSession().setAttribute(Constants.TABLEAU_CREDENTIALS, credentials);
		request.getSession().setAttribute(Constants.TABLEAU_WORKBOOKS, getTableauService().invokeQueryWorkbooks(credentials, Integer.parseInt(PropertyLooker.get("tableau.workbooks.max").trim()), 0).getWorkbooks().getWorkbook());
	}
	
	public static void selectSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		List<User> users = UserDAO.getUserByUsernameAndPassword(username, password);
		request.setAttribute("users", users );
		request.getRequestDispatcher("/views/siteSelector.jsp").forward(request, response);
	}
}
