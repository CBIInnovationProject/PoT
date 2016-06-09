package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;

import tableau.api.rest.bindings.WorkbookType;

public class WorkbookForm extends DefaultAction{
	public static void list(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				getMenuAction(action, request);
				List<WorkbookType> workbookTypes = getCurrentWorkbookList(request);
				request.setAttribute("workbooks", workbookTypes);
				request.setAttribute("credential", getCurrentCredentials(request));
				request.getRequestDispatcher("/views/workbookList2.jsp").forward(request, response);
			}
		}
	}
}
