package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.Role;
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

}
