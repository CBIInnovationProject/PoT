package com.cybertrend.cpot;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

public class RunOnStartUpServlet extends HttpServlet {
	static Logger logger = Logger.getLogger(RunOnStartUpServlet.class);
	@Override
	public void init() throws ServletException {
		logger.info("Application running up");
		super.init();
	}

}
