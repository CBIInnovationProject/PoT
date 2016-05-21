package com.cybertrend.pot.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;

public class Logout extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException {
		request.getSession().removeAttribute(Constants.USER_GA);
		servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
	}
}
