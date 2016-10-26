package com.cybertrend.cpot.action;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.cybertrend.cpot.Constants;
import com.cybertrend.cpot.dao.MenuDAO;
import com.cybertrend.cpot.entity.Menu;
import com.cybertrend.cpot.entity.Role;
import com.cybertrend.cpot.entity.User;
import com.cybertrend.cpot.service.TableauService;
import com.cybertrend.cpot.util.ReadConfig;

import tableau.api.rest.bindings.SiteType;
import tableau.api.rest.bindings.TableauCredentialsType;

public class DefaultAction{

	public static TableauService getTableauService() {
		return TableauService.instance();
	}
	
	public static TableauCredentialsType getCurrentCredentials(HttpServletRequest request){
		return (TableauCredentialsType) request.getSession().getAttribute(Constants.TABLEAU_CREDENTIALS);
	}
	
	public static SiteType getCurrentSite(HttpServletRequest request){
		return getCurrentCredentials(request).getSite();
	}
	
//	public static List<WorkbookType> getCurrentWorkbookList(HttpServletRequest request){
//		return (List<WorkbookType>)request.getSession().getAttribute(Constants.TABLEAU_WORKBOOKS);
//	}
	
	public static User getCurrentUser(HttpServletRequest request){
		return (User) request.getSession().getAttribute(Constants.USER_GA);
	}
	
	public static String getHostName(){
		return ReadConfig.get("tableau.server.host");
	}
	
	public static boolean authMenu(Menu menu, HttpServletRequest request) {
		boolean authorized = false ;
		if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("tableau")){
//			for (WorkbookType wb : getCurrentWorkbookList(request)) {
//				if(wb.getId().equalsIgnoreCase(menu.getWorkbookId())){
					authorized = true;
//				}
//			}
		} else if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("page")){
			authorized = true;
		} else if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("admin")){
			authorized = true;
		} else if(menu.getContentType()!=null && menu.getContentType().trim().equalsIgnoreCase("module")){
			authorized = true;
		} else if(menu.getContentType()==null){
			authorized = true;
		}
		return authorized;
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
