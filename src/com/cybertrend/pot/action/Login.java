package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.UserDAO;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.util.PropertyLooker;

import tableau.api.rest.bindings.TableauCredentialsType;

public class Login extends DefaultAction{
	
	public static void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean AUTH = UserDAO.authenticateUser(username, password);
		if (AUTH || Interceptor.isLogin(request)==true) {
			response.sendRedirect("landingPage.cbi");
		} else {
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} 
		User user = UserDAO.getUserByUsername(username);
		TableauCredentialsType credentials = getTableauService().invokeSignIn("Cybertrend", "passcbi2015", "").getCredentials();
		request.getSession().setAttribute(Constants.USER_GA, user);
		request.getSession().setAttribute(Constants.TABLEAU_CREDENTIALS, credentials);
		request.getSession().setAttribute(Constants.TABLEAU_WORKBOOKS, getTableauService().invokeQueryWorkbooks(credentials, Integer.parseInt(PropertyLooker.get("tableau.workbooks.max").trim()), 0).getWorkbooks().getWorkbook());
		
	}

}
