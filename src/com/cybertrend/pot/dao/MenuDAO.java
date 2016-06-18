package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.service.DatabaseService;

public class MenuDAO {
	
	public static List<Menu> getMenusByParentId(String parentId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from menu where parentId = ? ORDER BY menuOrder");
		prep.setString(1, parentId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
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
			menu.setParentId(result.getString("parentId"));
			menu.setAction(result.getString("action"));
			menu.setContent(result.getString("content"));
			menu.setContentType(result.getString("contentType"));
			menu.setMenuOrder(result.getInt("menuOrder"));
			menu.setIcon(result.getString("icon"));
			menu.setWorkbookId(result.getString("workbookId"));
			menu.setViewId(result.getString("viewId"));
			menu.setSiteId(result.getString("siteId"));
		} 
		return menu;
	}
	
	public static List<Menu> getMenusByContentType(String contentType) throws SQLException{
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from menu where contentType=? ORDER BY menuOrder");
		prep.setString(1, contentType);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
			menus.add(menu);
		}
		return menus;
	}
	
	public static Menu getMenuByAction(String action) throws SQLException {
		Menu menu = null ;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from menu where action = ?");
		prep.setString(1, action);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			menu = getMenuById(result.getString("id"));
		} 
		return menu;
	}
	
	public static List<Menu> getListParentMenu() throws SQLException{
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM menu WHERE contentType IS null");
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
			menus.add(menu);
		}
		return menus;
	}
	
	public static void save(Menu menu) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO menu(id, createBy, createDate, updateBy, updateDate, name, parentId, action, content, contentType, menuOrder, icon, workbookId, viewId, siteId ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		prep.setString(1, System.currentTimeMillis()+"");
		prep.setString(2, menu.getCreateBy());
		prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		prep.setString(4, menu.getUpdateBy());
		prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		
		prep.setString(6, menu.getName());
		prep.setString(7, menu.getParentId());
		prep.setString(8, menu.getAction());
		prep.setString(9, menu.getContent());
		prep.setString(10, menu.getContentType());
		prep.setInt(11, menu.getMenuOrder());
		prep.setString(12, menu.getIcon());
		prep.setString(13, menu.getWorkbookId());
		prep.setString(14, menu.getViewId());
		prep.setString(15, menu.getSiteId());
		
		prep.executeUpdate();
	}
	
	public static void update(Menu menu) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("UPDATE menu SET updateBy=?, updateDate=?, name=?, parentId=?, action=?, content=?, contentType=?, menuOrder=?, icon=?, workbookId=?, viewId=?, siteId=? WHERE id=?");
		prep.setString(1, menu.getUpdateBy());
		prep.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
		
		prep.setString(3, menu.getName());
		prep.setString(4, menu.getParentId());
		prep.setString(5, menu.getAction());
		prep.setString(6, menu.getContent());
		prep.setString(7, menu.getContentType());
		prep.setInt(8, menu.getMenuOrder());
		prep.setString(9, menu.getIcon());
		prep.setString(10, menu.getWorkbookId());
		prep.setString(11, menu.getViewId());
		prep.setString(12, menu.getSiteId());
		prep.setString(13, menu.getId());
		
		prep.executeUpdate();
	}
	
	public static void delete(Menu menu) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM menu WHERE id=?");
		prep.setString(1, menu.getId());
		prep.executeUpdate();
	}
	
	public static List<Menu> getList() throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM menu WHERE contentType <> 'admin' OR contentType IS NULL");
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
			menus.add(menu);
		}
		return menus;
	}
	
}
