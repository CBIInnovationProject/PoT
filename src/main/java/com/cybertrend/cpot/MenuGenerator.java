package com.cybertrend.cpot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.cybertrend.cpot.action.DefaultAction;
import com.cybertrend.cpot.dao.MenuDAO;
import com.cybertrend.cpot.dao.RoleMenuDAO;
import com.cybertrend.cpot.dao.ThemesDAO;
import com.cybertrend.cpot.entity.Menu;

public class MenuGenerator extends DefaultAction {
	
	public static String treeMenu(HttpServletRequest request) throws ServletException, IOException, SQLException {
		String treeMenu = "";
		List<Menu> menus = RoleMenuDAO.getMenuByRole(getCurrentRole(request));
		for(Menu menu : menus){
			treeMenu = treeMenu + leafMenu(menu, request);
		}
		return treeMenu;
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
		} else if (menu.getContentType()!=null) {
			if(authMenu(menu, request)){
				treeMenu = treeMenu+String.format(ThemesDAO.getThemesById(getCurrentUser(request).getThemes().getId()).getLi(), menu.getAction(), menu.getIcon(),menu.getName());
			}
		}
		return treeMenu;
	}

}
