package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.Role;
import com.cybertrend.pot.entity.RoleMenu;
import com.cybertrend.pot.service.DatabaseService;

public class RoleMenuDAO {
	public static List<Menu> getMenuByRole(Role role) throws SQLException {
		List<Menu> menus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select menu from roleMenu where role = ? ORDER BY menuOrder");
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
	
	public static void save(RoleMenu roleMenu) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO rolemenu(id, createBy, createDate, updateBy, updateDate, role, menu, menuOrder) VALUES (?,?,?,?,?,?,?,?)");
		prep.setString(1, System.currentTimeMillis()+"-"+new Random().nextLong());
		prep.setString(2, roleMenu.getCreateBy());
		prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		prep.setString(4, roleMenu.getUpdateBy());
		prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		
		prep.setString(6, roleMenu.getRole().getId());
		prep.setString(7, roleMenu.getMenu().getId());
		prep.setInt(8, roleMenu.getMenu().getMenuOrder());
		prep.executeUpdate();
	}
	
	public static void delete(Role role, Menu menu) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM rolemenu WHERE menu=? AND role=?");
		prep.setString(1, menu.getId());
		prep.setString(2, role.getId());
		prep.executeUpdate();
	}

}
