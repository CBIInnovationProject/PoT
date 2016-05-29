package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.dao.RoleMenuDAO;
import com.cybertrend.pot.dao.RoleUserDAO;
import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.TableauService;
import com.cybertrend.pot.util.PropertyLooker;

import tableau.api.rest.bindings.TableauCredentialsType;
import tableau.api.rest.bindings.WorkbookType;

public class DefaultAction{

	public static TableauService getTableauService() {
		return TableauService.instance();
	}
	
	public static TableauCredentialsType getCurrentCredentials(HttpServletRequest request){
		return (TableauCredentialsType) request.getSession().getAttribute(Constants.TABLEAU_CREDENTIALS);
	}
	
	public static List<WorkbookType> getCurrentWorkbookList(HttpServletRequest request){
		return (List<WorkbookType>)request.getSession().getAttribute(Constants.TABLEAU_WORKBOOKS);
	}
	
	public static User getCurrentUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(Constants.USER_GA);
	}
	
	public static String getHostName(){
		return PropertyLooker.get("tableau.server.host");
	}
	
	public static void treeMenu(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException, SQLException {
		String treeMenu = "";
		List<Menu> menus = RoleMenuDAO.getMenuByRole(RoleUserDAO.getRoleByUser(getCurrentUser(request), getCurrentCredentials(request).getSite().getId()));
		for(Menu menu : menus){
			treeMenu = treeMenu + leafMenu(menu, request, response, servletContext);
		}
		servletContext.setAttribute("treeMenu", treeMenu);
	}
	
	private static boolean authMenu(Menu menu, HttpServletRequest request) {
		boolean authorized = false ;
		if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("tableau")){
			for (WorkbookType wb : getCurrentWorkbookList(request)) {
				if(wb.getId().equalsIgnoreCase(menu.getWorkbookId())){
					authorized = true;
				}
			}
		} if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("page")){
			authorized = true;
		}
		return authorized;
	}
	
	private static String leafMenu(Menu menu, HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws SQLException{
		String treeMenu = "";
		List<Menu> menus = MenuDAO.getMenusByParentId(menu.getId());
		if(menus.size()>0){
			treeMenu = treeMenu+"<li><a><i class=\""+menu.getIcon()+"\"></i>"+menu.getName()+"<span class=\"fa fa-chevron-down\"></span></a><ul class=\"nav child_menu\">";
			for(Menu menu2: menus){
				treeMenu = treeMenu + leafMenu(menu2, request, response, servletContext);
			} treeMenu = treeMenu + "</ul></li>";
		} else {
			if(authMenu(menu, request)){
				treeMenu = treeMenu+"<li><a href=\""+menu.getAction()+"\"><i class=\""+menu.getIcon()+"\"></i>"+menu.getName()+"</a></li>";
			}
		}
		return treeMenu;
	}
	
}
