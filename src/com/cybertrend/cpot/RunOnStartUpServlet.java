package com.cybertrend.cpot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class RunOnStartUpServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("Hello Start Up");
		super.init();
	}

}
