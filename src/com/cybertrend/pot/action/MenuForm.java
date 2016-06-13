package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.DashboardDAO;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.entity.Dashboard;
import com.cybertrend.pot.entity.Menu;

public class MenuForm extends DefaultAction {
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				getMenuAction(action, request);
				request.setAttribute("parentMenus", MenuDAO.getListParentMenu());
				request.setAttribute("dashboards", DashboardDAO.getListDashboards());
			}
		}
	}
	
	public static void save(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				Menu menu = new Menu();
				Dashboard dashboard = DashboardDAO.getDashboardById(request.getParameter("dashboard"));
				menu.setCreateBy(getCurrentUser(request).getUsername());
				menu.setName(request.getParameter("name"));
				menu.setAction(request.getParameter("action"));
				menu.setParentId((request.getParameter("parentId")==null||request.getParameter("parentId").trim().equals(""))?null:request.getParameter("parentId"));
				menu.setContent(request.getParameter("content"));
				menu.setContentType(request.getParameter("contentType"));
				menu.setMenuOrder(Integer.parseInt(request.getParameter("menuOrder")));
				menu.setIcon("fa "+request.getParameter("icon"));
				menu.setWorkbookId(dashboard!=null?dashboard.getWorkbookId():null);
				menu.setViewId(dashboard!=null?dashboard.getId():null);
				menu.setSiteId(getCurrentCredentials(request).getSite().getId());
				MenuDAO.save(menu);
				request.getRequestDispatcher("/views/success.jsp").forward(request, response);
			}
		}
	}
}
