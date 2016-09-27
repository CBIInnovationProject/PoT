package com.cybertrend.cpot.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.cpot.Constants;

public class Logout extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.getSession().removeAttribute(Constants.USER_GA);
		request.getSession().removeAttribute(Constants.TABLEAU_CREDENTIALS);
		request.getSession().removeAttribute(Constants.TABLEAU_WORKBOOKS);
		request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
	}
}