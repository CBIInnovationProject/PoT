package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.log4j.Logger;
import com.cybertrend.cpot.entity.Menu;
import com.cybertrend.cpot.service.DatabaseService;

public class MenuDAO {
	static Logger logger = Logger.getLogger(MenuDAO.class);
	
	public static List<Menu> getMenusByParentId(String parentId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_menu where parentId = ? ORDER BY menuOrder");
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
		PreparedStatement prep = conn.prepareStatement("select * from t_menu where id = ?");
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
		PreparedStatement prep = conn.prepareStatement("select id from t_menu where contentType=? ORDER BY menuOrder");
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
		PreparedStatement prep = conn.prepareStatement("select id from t_menu where action = ?");
		prep.setString(1, action);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			menu = getMenuById(result.getString("id"));
		} 
		return menu;
	}
	
	public static List<Menu> getListParentMenu(String siteId) throws SQLException{
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM t_menu WHERE contentType IS null AND siteId = ?");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
			menus.add(menu);
		}
		return menus;
	}
	
	public static String save(Menu menu) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "INSERT INTO t_menu(id, createBy, createDate, updateBy, updateDate, name, parentId, action, content, contentType, menuOrder, icon, workbookId, viewId, siteId ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			String id = UUID.randomUUID().toString();
			Timestamp currDate = new Timestamp(System.currentTimeMillis());
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, id);
			logger.info("Menu id : "+id);
			prep.setString(2, menu.getCreateBy());
			logger.info("Menu Created By : "+menu.getCreateBy());
			prep.setTimestamp(3, currDate);
			logger.info("Menu Create Date : "+currDate);
			prep.setString(4, menu.getUpdateBy());
			logger.info("Menu Update By : "+menu.getUpdateBy());
			prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			logger.info("Menu Update Date : "+currDate);
			
			prep.setString(6, menu.getName());
			logger.info("Menu Name : "+menu.getName());
			prep.setString(7, menu.getParentId());
			logger.info("Menu Parent Id : "+menu.getParentId());
			prep.setString(8, menu.getAction());
			logger.info("Menu Action : "+menu.getAction());
			prep.setString(9, menu.getContent());
			logger.info("Menu Content : "+menu.getContent());
			prep.setString(10, menu.getContentType());
			logger.info("Menu Content Type : "+menu.getContentType());
			prep.setInt(11, menu.getMenuOrder());
			logger.info("Menu Order : "+menu.getMenuOrder());
			prep.setString(12, menu.getIcon());
			logger.info("Menu Icon : "+menu.getIcon());
			prep.setString(13, menu.getWorkbookId());
			logger.info("Workbook Id : "+menu.getWorkbookId());
			prep.setString(14, menu.getViewId());
			logger.info("View Id : "+menu.getViewId());
			prep.setString(15, menu.getSiteId());
			logger.info("Site Id : "+menu.getSiteId());
			
			prep.executeUpdate();
			logger.info("Insert Menu Success!!!");
			return "Menu <strong>"+menu.getName()+"</strong> successfully added to record";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
	public static String update(Menu menu) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "UPDATE t_menu SET updateBy=?, updateDate=?, name=?, parentId=?, action=?, content=?, contentType=?, menuOrder=?, icon=?, workbookId=?, viewId=?, siteId=? WHERE id=?";
		try {
			Timestamp currDate = new Timestamp(System.currentTimeMillis());
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, menu.getUpdateBy());
			logger.info("Menu Update By : "+menu.getUpdateBy());
			prep.setTimestamp(2, currDate);
			logger.info("Menu Update Date : "+currDate);
			
			prep.setString(3, menu.getName());
			logger.info("Menu Name : "+menu.getName());
			prep.setString(4, menu.getParentId());
			logger.info("Menu Parent Id : "+menu.getParentId());
			prep.setString(5, menu.getAction());
			logger.info("Menu Action : "+menu.getAction());
			prep.setString(6, menu.getContent());
			logger.info("Menu Content : "+menu.getContent());
			prep.setString(7, menu.getContentType());
			logger.info("Menu Content Type : "+menu.getContentType());
			prep.setInt(8, menu.getMenuOrder());
			logger.info("Menu Order : "+menu.getMenuOrder());
			prep.setString(9, menu.getIcon());
			logger.info("Menu Icon : "+menu.getIcon());
			prep.setString(10, menu.getWorkbookId());
			logger.info("Workbook Id : "+menu.getWorkbookId());
			prep.setString(11, menu.getViewId());
			logger.info("View Id : "+menu.getViewId());
			prep.setString(12, menu.getSiteId());
			logger.info("Site Id : "+menu.getSiteId());
			prep.setString(13, menu.getId());
			logger.info("Menu Id : "+menu.getId());
			
			prep.executeUpdate();
			logger.info("Update Menu Success!!!");
			return "Menu <strong>"+menu.getName()+"</strong> successfully updated";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
	public static String delete(Menu menu){
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement("DELETE FROM t_menu WHERE id=?");
			prep.setString(1, menu.getId());
			prep.executeUpdate();
			return "Menu <strong>"+menu.getName()+"</strong> has been deleted";
		} catch (SQLException e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	public static List<Menu> getList(String siteId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM t_menu WHERE siteId=? AND (contentType <> 'admin' OR contentType IS NULL)");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
			menus.add(menu);
		}
		return menus;
	}
	
	public static List<Menu> getMenuParents(String siteId) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM t_menu WHERE siteId=? AND parentId IS NULL AND (contentType <> 'admin' OR contentType IS NULL)");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = getMenuById(result.getString("id"));
			menus.add(menu);
		}
		return menus;
	}
	
	public static boolean isDasboardExistOnMenu(String viewId) throws SQLException {
		Menu menu = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM t_menu WHERE viewId = ? ");
		prep.setString(1, viewId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			menu = getMenuById(result.getString("id"));
		}
		return menu!=null?true:false;
	}
}
