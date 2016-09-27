package com.cybertrend.cpot.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.cpot.Constants;
import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.DashboardDAO;
import com.cybertrend.cpot.dao.MenuDAO;
import com.cybertrend.cpot.entity.Dashboard;
import com.cybertrend.cpot.entity.Menu;

public class MenuForm extends DefaultAction {
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				request.setAttribute("parentMenus", MenuDAO.getListParentMenu());
				request.setAttribute("dashboards", DashboardDAO.getListDashboards());
				if(request.getParameter("menuId")!=null) {
					Menu menu = MenuDAO.getMenuById(request.getParameter("menuId"));
					request.setAttribute("menuView", menu);
				}
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void save(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if (getCurrentRole(request).getId().equals("0")){
			getMenuAction(action, request);
			Menu menu = new Menu();
			Dashboard dashboard = DashboardDAO.getDashboardById(request.getParameter("dashboard"));
			menu.setCreateBy(getCurrentUser(request).getUsername());
			menu.setUpdateBy(getCurrentUser(request).getUsername());
			menu.setName(request.getParameter("name"));
			menu.setAction(request.getParameter("name").trim().toLowerCase().replace(" ", "_")+".cbi");
			menu.setParentId((request.getParameter("parentId")==null||request.getParameter("parentId").trim().equals(""))?null:request.getParameter("parentId"));
			menu.setContent(dashboard!=null?dashboard.getUrl():request.getParameter("content"));
			menu.setContentType(request.getParameter("contentType"));
			menu.setMenuOrder(Integer.parseInt(request.getParameter("menuOrder")));
			menu.setIcon(request.getParameter("icon").split("-")[0]+" "+request.getParameter("icon"));
			menu.setWorkbookId(dashboard!=null?dashboard.getWorkbookId():null);
			menu.setViewId(dashboard!=null?dashboard.getId():null);
			menu.setSiteId(getCurrentCredentials(request).getSite().getId());
			if(request.getParameter("menuId")!=null&&!"".equalsIgnoreCase(request.getParameter("menuId").trim())) {
				menu.setId(request.getParameter("menuId"));
				MenuDAO.update(menu);
			}else
				MenuDAO.save(menu);
			PrintWriter out = response.getWriter();
		    out.println(Constants.SUCCESS);
		}
		else {
			request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
		}
	}
	
	public static void list(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				List<Menu> menus = MenuDAO.getList(getCurrentCredentials(request).getSite().getId());
				request.setAttribute("menus", menus);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
}
