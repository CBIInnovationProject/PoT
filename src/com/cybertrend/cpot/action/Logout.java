package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.cybertrend.cpot.Constants;

public class Logout extends DefaultAction{
	static Logger logger = Logger.getLogger(Logout.class);
	public static void execute(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.getSession().removeAttribute(Constants.USER_GA);
		request.getSession().removeAttribute(Constants.TABLEAU_CREDENTIALS);
		request.getSession().removeAttribute(Constants.TABLEAU_WORKBOOKS);
		logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
		logger.info("Activity : Logout");
		request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
	}
}
