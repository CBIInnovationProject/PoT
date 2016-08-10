package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.DatabaseService;

public class RestrictMenuDAO {
	public static boolean isExist(Menu menu, User user) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from resctrictMenu where menuId = ? AND userId = ?");
		prep.setString(1, menu.getId());
		prep.setString(2, user.getId());
		ResultSet result = prep.executeQuery();
		while (result.next()) {

		}
		return false;
	}

}