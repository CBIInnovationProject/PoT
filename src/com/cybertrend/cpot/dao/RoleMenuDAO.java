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
import com.cybertrend.cpot.entity.Role;
import com.cybertrend.cpot.entity.RoleMenu;
import com.cybertrend.cpot.service.DatabaseService;

public class RoleMenuDAO {
	static Logger logger = Logger.getLogger(RoleMenuDAO.class);
	
	public static List<Menu> getMenuByRole(Role role) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select menu from t_roleMenu where role = ? ORDER BY menuOrder");
		prep.setString(1, role.getId());
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Menu menu = MenuDAO.getMenuById(result.getString("menu"));
			menus.add(menu);
		}
		return menus ;
	}
	
	public static boolean isMenuExist(String menuId, String roleId) throws SQLException {
		boolean exist = false;
		List<Menu> menus = getMenuByRole(RoleDAO.getRoleById(roleId));
		for(Menu menu: menus) {
			if (menuId.trim().equalsIgnoreCase(menu.getId())){
				exist = true;
				break;
			} else {
				exist = false;
			}
		}
		return exist;
	}
	
	public static void save(RoleMenu roleMenu) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "INSERT INTO t_rolemenu(id, createBy, createDate, updateBy, updateDate, role, menu, menuOrder) VALUES (?,?,?,?,?,?,?,?)";
		try {
			String roleMenuId = UUID.randomUUID().toString();
			Timestamp currDate = new Timestamp(System.currentTimeMillis());
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, roleMenuId);
			logger.info("RoleMenu Id : "+roleMenuId);
			prep.setString(2, roleMenu.getCreateBy());
			logger.info("RoleMenu Create By : "+roleMenu.getCreateBy());
			prep.setTimestamp(3, currDate);
			logger.info("RoleMenu Create Date : "+currDate);
			prep.setString(4, roleMenu.getUpdateBy());
			logger.info("RoleMenu Update By : "+roleMenu.getUpdateBy());
			prep.setTimestamp(5, currDate);
			logger.info("RoleMenu Update Date : "+currDate);
			
			prep.setString(6, roleMenu.getRole().getId());
			logger.info("RoleMenu Role Id : "+roleMenu.getRole().getId());
			prep.setString(7, roleMenu.getMenu().getId());
			logger.info("RoleMenu Menu Id : "+roleMenu.getMenu().getId());
			prep.setInt(8, roleMenu.getMenu().getMenuOrder());
			logger.info("RoleMenu Menu Order : "+roleMenu.getMenu().getMenuOrder());
			prep.executeUpdate();
			logger.info("Insert RoleMenu Success!!!");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void delete(Role role, Menu menu) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "DELETE FROM t_rolemenu WHERE menu=? AND role=?";
		try {
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, menu.getId());
			logger.info("RoleMenu Menu Id : "+menu.getId());
			prep.setString(2, role.getId());
			logger.info("RoleMenu Role Id : "+role.getId());
			prep.executeUpdate();
			logger.info("Delete RoleMenu Success!!!!");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

}
