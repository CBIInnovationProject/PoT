package com.cybertrend.pot;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cybertrend.pot.action.DefaultAction;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.dao.RoleMenuDAO;
import com.cybertrend.pot.entity.Menu;

public class Interceptor extends DefaultAction {
	
	public static boolean isLogin(HttpServletRequest request) {
		return getCurrentUser(request)!=null;
	}
	
	public static boolean isAuthorized(String action, HttpServletRequest request) throws SQLException {
		List<Menu> menus = RoleMenuDAO.getMenuByRole(getCurrentRole(request));
		for (Menu menu : menus){
			if(getCurrentRole(request).getId().trim().equals("0")){
				return true;
			}
			else if(checkLeafAction(action, menu)){
				return true;
			}
			else {
				if (action.equalsIgnoreCase(menu.getAction())){
					return true;
				}
			}
		}
		return false;
	}

	private static boolean checkLeafAction(String action, Menu parent) throws SQLException {
		for (Menu menu : MenuDAO.getMenusByParentId(parent.getId())){
			if(MenuDAO.getMenusByParentId(menu.getId()).size()>0){
				if(checkLeafAction(action, menu)) {
					return true;
				}
			} else {
				if (action.equalsIgnoreCase(menu.getAction())){
					return true;
				}
			}
		}
		return false;
	}
}
