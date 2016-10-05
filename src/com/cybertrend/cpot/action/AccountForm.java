package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.UserDAO;
import com.cybertrend.cpot.entity.User;

public class AccountForm extends DefaultAction {
	static Logger logger = Logger.getLogger(AccountForm.class);
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} else {
			logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
			logger.info("Activity : "+action);
			logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
			User user = UserDAO.getUserById(getCurrentUser(request).getId());
			request.setAttribute("username", user.getUsername());
			request.setAttribute("email", user.getEmail());
		}
	}
}
