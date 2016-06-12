package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.DashboardDAO;
import com.cybertrend.pot.entity.Dashboard;
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
				
				if(request.getParameter("viewId")!=null) {
					if(request.getParameter("url")!=null) {
						Dashboard dashboard = new Dashboard();
						dashboard.setId(request.getParameter("viewId"));
						dashboard.setCreateBy(getCurrentUser(request).getUsername());
						dashboard.setCreateDate(new Timestamp(System.currentTimeMillis()));
						dashboard.setUrl(request.getParameter("url"));
						dashboard.setSiteId(getCurrentCredentials(request).getSite().getId());
						DashboardDAO.add(dashboard);
					} else if(request.getParameter("deleteAction").equals("yes"))
						DashboardDAO.delete(request.getParameter("viewId"));
				}
				
				request.getRequestDispatcher("/views/workbookDetail.jsp").forward(request, response);
			}
		}
	}
	
	public static void viewDashboard(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				request.setAttribute("menuName", "<a href=\"workbookList.cbi\">Workbook</a> <i class=\"fa fa-angle-double-right\"></i> <a href=\"workbookDetail.cbi?workbookId="+request.getParameter("workbookId")+"\"> "+request.getParameter("url").split("/")[0]+"</a> <i class=\"fa fa-angle-double-right\"></i> "+request.getParameter("url").split("/")[1]+"");
				request.setAttribute("url", request.getParameter("url"));
				request.getRequestDispatcher("/views/viewDashboard.jsp").forward(request, response);
			}
		}
	}
}
