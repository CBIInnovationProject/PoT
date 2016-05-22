package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.service.DatabaseService;

public class MenuDAO {
	public static List<Menu> getMenusByRole(String roleId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select menu from roleMenu where role = ? ORDER BY menuOrder");
		prep.setString(1, roleId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("menu"));
			menus.add(menu);
		}
		return menus ;
	}
	
	public static List<Menu> getMenusByParentId(String parentId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from menu where parentId = ? ORDER BY menuOrder");
		prep.setString(1, parentId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = new Menu();
			menu.setName(result.getString("id"));
			menu.setCreateBy(result.getString("createBy"));
			menu.setCreateDate(result.getTimestamp("createDate"));
			menu.setUpdateBy(result.getString("updateBy"));
			menu.setUpdateDate(result.getTimestamp("updateDate"));
			menu.setName(result.getString("name"));
			menu.setDescription(result.getString("description"));
			menu.setType(result.getString("type"));
			menu.setParentId(result.getInt("parentId"));
			menu.setAction(result.getString("action"));
			menu.setUrl(result.getString("url"));
			menu.setMenuOrder(result.getInt("menuOrder"));
			menu.setIcon(result.getString("icon"));
			menu.setWorkbookId(result.getString("workbookId"));
			menus.add(menu);
		}
		return menus ;
	}
	
	public static Menu getMenuById(String id) throws SQLException {
		Menu menu = null ;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from menu where id = ?");
		prep.setString(1, id);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			menu = new Menu();
			menu.setId(result.getString("id"));
			menu.setCreateBy(result.getString("createBy"));
			menu.setCreateDate(result.getTimestamp("createDate"));
			menu.setUpdateBy(result.getString("updateBy"));
			menu.setUpdateDate(result.getTimestamp("updateDate"));
			menu.setName(result.getString("name"));
			menu.setDescription(result.getString("description"));
			menu.setType(result.getString("type"));
			menu.setParentId(result.getInt("parentId"));
			menu.setAction(result.getString("action"));
			menu.setUrl(result.getString("url"));
			menu.setMenuOrder(result.getInt("menuOrder"));
			menu.setIcon(result.getString("icon"));
			menu.setWorkbookId(result.getString("workbookId"));
		} 
		return menu;
	}

}
