package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.dao.RoleMenuDAO;
import com.cybertrend.pot.dao.ThemesDAO;
import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.Role;
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
	
	public static String treeMenu(HttpServletRequest request) throws ServletException, IOException, SQLException {
		String treeMenu = "";
		List<Menu> menus = RoleMenuDAO.getMenuByRole(getCurrentRole(request));
		for(Menu menu : menus){
			treeMenu = treeMenu + leafMenu(menu, request);
		}
		return treeMenu;
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
		} if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("admin")){
			authorized = true;
		}
		return authorized;
	}
	
	private static String leafMenu(Menu menu, HttpServletRequest request) throws SQLException{
		String treeMenu = "";
		List<Menu> menus = MenuDAO.getMenusByParentId(menu.getId());
		if(menus.size()>0){
			treeMenu = treeMenu+String.format(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getUl(), menu.getIcon(),menu.getName());
			for(Menu menu2: menus){
				if(authMenu(menu2, request)){
					treeMenu = treeMenu + leafMenu(menu2, request);
				}
			} treeMenu = treeMenu + "</ul></li>";
		} else {
			if(authMenu(menu, request)){
				treeMenu = treeMenu+String.format(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getLi(), menu.getAction(), menu.getIcon(),menu.getName());
			}
		}
		return treeMenu;
	}
	
	public static Menu getMenuAction(String action, HttpServletRequest request) throws SQLException {
		Menu menuAction = MenuDAO.getMenuByAction(action);
		request.setAttribute("menu", menuAction);
		return menuAction;
	}
	
	public static Role getCurrentRole(HttpServletRequest request) throws SQLException {
		return getCurrentUser(request).getRole();
	}
}
