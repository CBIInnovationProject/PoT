package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;

public class SiteForm extends DefaultAction {
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
			
		}
	}
}
