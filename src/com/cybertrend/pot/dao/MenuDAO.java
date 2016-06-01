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
	
	public static List<Menu> getMenusByParentId(String parentId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from menu where parentId = ? ORDER BY menuOrder");
		prep.setString(1, parentId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = new Menu();
			menu.setId(result.getString("id"));
			menu.setCreateBy(result.getString("createBy"));
			menu.setCreateDate(result.getTimestamp("createDate"));
			menu.setUpdateBy(result.getString("updateBy"));
			menu.setUpdateDate(result.getTimestamp("updateDate"));
			
			menu.setName(result.getString("name"));
			menu.setDescription(result.getString("description"));
			menu.setParentId(result.getInt("parentId"));
			menu.setAction(result.getString("action"));
			menu.setContent(result.getString("content"));
			menu.setContentType(result.getString("contentType"));
			menu.setContentWidth(result.getString("contentWidth"));
			menu.setContentHeight(result.getString("contentHeight"));
			menu.setMenuOrder(result.getInt("menuOrder"));
			menu.setIcon(result.getString("icon"));
			menu.setWorkbookId(result.getString("workbookId"));
			menu.setSiteId(result.getString("siteId"));
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
			menu.setParentId(result.getInt("parentId"));
			menu.setAction(result.getString("action"));
			menu.setContent(result.getString("content"));
			menu.setContentType(result.getString("contentType"));
			menu.setContentWidth(result.getString("contentWidth"));
			menu.setContentHeight(result.getString("contentHeight"));
			menu.setMenuOrder(result.getInt("menuOrder"));
			menu.setIcon(result.getString("icon"));
			menu.setWorkbookId(result.getString("workbookId"));
			menu.setSiteId(result.getString("siteId"));
		} 
		return menu;
	}
	
	public static List<Menu> getActionsAndContents(String contentType) throws SQLException{
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from menu where contentType=? ORDER BY menuOrder");
		prep.setString(1, contentType);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = new Menu();
			menu.setAction(result.getString("action"));
			menu.setContent(result.getString("content"));
			menus.add(menu);
		}
		return menus;
	}
	
	public static Menu getMenuByAction(String action) throws SQLException {
		Menu menu = null ;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from menu where action = ?");
		prep.setString(1, action);
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
			menu.setParentId(result.getInt("parentId"));
			menu.setAction(result.getString("action"));
			menu.setContent(result.getString("content"));
			menu.setContentType(result.getString("contentType"));
			menu.setContentWidth(result.getString("contentWidth"));
			menu.setContentHeight(result.getString("contentHeight"));
			menu.setMenuOrder(result.getInt("menuOrder"));
			menu.setIcon(result.getString("icon"));
			menu.setWorkbookId(result.getString("workbookId"));
			menu.setSiteId(result.getString("siteId"));
		} 
		return menu;
	}

}
