package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.entity.WorkbookTableau;
import com.cybertrend.pot.util.PropertyLooker;

import tableau.api.rest.bindings.UserType;
import tableau.api.rest.bindings.WorkbookType;

public class WorkbookForm extends DefaultAction{
	public static void list(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				List<WorkbookType> workbookTypes = getCurrentWorkbookList(request);
				request.setAttribute("workbooks", workbookTypes);
				request.setAttribute("credential", getCurrentCredentials(request));
				request.getRequestDispatcher("/views/workbookList.jsp").forward(request, response);
			}
		}
	}
	
	public static void detail(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				WorkbookType workbookType = getTableauService().invokeGetWorkbook(getCurrentCredentials(request), request.getParameter("workbookId").toString()) ;
				WorkbookTableau workbook = new WorkbookTableau();
				workbook.setWorkbookType(workbookType);
				workbook.setImage(getTableauService().invokeQueryWorkbookImage(getCurrentCredentials(request), workbookType.getId()));
				UserType user = getTableauService().invokeGetUser(getCurrentCredentials(request), workbookType.getOwner().getId());
				request.setAttribute("workbook", workbook);
				request.setAttribute("owner", user);
				request.setAttribute("views", getTableauService().invokeQueryViews(getCurrentCredentials(request), workbookType.getId(), Integer.parseInt(PropertyLooker.get("tableau.views.max").trim()), 0).getView());
				request.setAttribute("createDate", workbookType.getCreateAt().toString().replace("T", " ").replace("Z", " "));
				request.setAttribute("updateDate", workbookType.getUpdatedAt().toString().replace("T", " ").replace("Z", " "));
				request.getRequestDispatcher("/views/workbookDetail.jsp").forward(request, response);
			}
		}
	}
}
