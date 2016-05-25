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
	
	public static void treeMenu(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException, SQLException {
		String treeMenu = "";
		List<Menu> menus = RoleMenuDAO.getMenuByRole(RoleUserDAO.getRoleByUser(getCurrentUser(request), getCurrentCredentials(request).getSite().getId()));
		for(Menu menu : menus){
			List<Menu> menusByParent = MenuDAO.getMenusByParentId(menu.getId());
			if(menusByParent.size()>0){
				treeMenu = treeMenu+"<li><a><i class=\""+menu.getIcon()+"\"></i>"+menu.getName()+"<span class=\"fa fa-chevron-down\"></span></a><ul class=\"nav child_menu\">";
				for(Menu menuChild: menusByParent){
					if(menuIsWorkbook(menuChild, request)){
						treeMenu = treeMenu+"<li><a href=\""+menuChild.getAction()+"\">"+menuChild.getName()+"</a></li>";
					}
				} treeMenu = treeMenu + "</ul></li>";
			} else {
				if(menuIsWorkbook(menu, request)){
					treeMenu = treeMenu+"<li><a href=\""+menu.getAction()+"\"><i class=\""+menu.getIcon()+"\"></i>"+menu.getName()+"</a></li>";
				}
			}
		}
		servletContext.setAttribute("treeMenu", treeMenu);
	}
	
	private static boolean menuIsWorkbook(Menu menu, HttpServletRequest request) {
		if(menu.getUrlType()!=null && menu.getUrlType().trim().equalsIgnoreCase("tableau")){
			for (WorkbookType wb : getCurrentWorkbookList(request)) {
				if(wb.getId().equalsIgnoreCase(menu.getWorkbookId())){
					return true;
				}
			}
		} if(menu.getUrlType()!=null && menu.getUrlType().trim().equalsIgnoreCase("page")){
			return true;
		}
		return false;
	}

}
