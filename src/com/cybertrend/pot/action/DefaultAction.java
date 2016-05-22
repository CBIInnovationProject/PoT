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
import com.cybertrend.pot.dao.RoleDAO;
import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.TableauService;

import tableau.api.rest.bindings.TableauCredentialsType;

public class DefaultAction{

	public static TableauService getTableauService() {
		return TableauService.instance();
	}
	
	public static TableauCredentialsType getCurrentCredentials(HttpServletRequest request){
		return (TableauCredentialsType) request.getSession().getAttribute(Constants.TABLEAU_CREDENTIALS);
	}
	
	public static User getCurrentUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(Constants.USER_GA);
	}
	
	public static void treeMenu(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)throws ServletException, IOException, SQLException {
		String treeMenu = "";
		List<Menu> menus = MenuDAO.getMenusByRole(RoleDAO.getRoleByUser(getCurrentUser(request).getId()).getId());
		for(Menu menu : menus){
			if(MenuDAO.getMenusByParentId(menu.getId()).size()>0){
				treeMenu = treeMenu+"<li><a><i class=\"fa fa-home\"></i>"+menu.getName()+"<span class=\"fa fa-chevron-down\"></span></a><ul class=\"nav child_menu\">";
				for(Menu menuChild: MenuDAO.getMenusByParentId(menu.getId())){
					treeMenu = treeMenu+"<li><a href=\""+menuChild.getAction()+"\">"+menuChild.getName()+"</a></li>";
				} treeMenu = treeMenu + "</ul></li>";
			} else {
				treeMenu = treeMenu+"<li><a href=\""+menu.getAction()+"\"><i class=\"fa fa-laptop\"></i>"+menu.getName()+"</a></li>";
			}
		}
		servletContext.setAttribute("treeMenu", treeMenu);
	}

}
