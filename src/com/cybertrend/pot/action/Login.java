package com.cybertrend.pot.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.util.PropertyLooker;

import tableau.api.rest.bindings.TableauCredentialsType;

public class Login extends DefaultAction{
	
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException {
		TableauCredentialsType credential = getCurrentCredentials(request);
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (credential == null) {
			credential = getTableauService().invokeSignIn(username, password, PropertyLooker.get("tableau.site.default.contentUrl")).getCredentials();
			if(credential==null){
				servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
			} else {
				response.sendRedirect("adminPage.cbi");
			}
		} else {
			response.sendRedirect("adminPage.cbi");
		} 
		request.getSession().setAttribute("credential", credential);
		request.getSession().setAttribute("username", username);
	}

}
