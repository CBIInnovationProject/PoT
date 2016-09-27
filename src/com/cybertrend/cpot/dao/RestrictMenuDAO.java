package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.cpot.entity.Menu;
import com.cybertrend.cpot.entity.User;
import com.cybertrend.cpot.service.DatabaseService;

public class RestrictMenuDAO {
	public static boolean isExist(Menu menu, User user) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_resctrict_menu where menuId = ? AND userId = ?");
		prep.setString(1, menu.getId());
		prep.setString(2, user.getId());
		ResultSet result = prep.executeQuery();
		while (result.next()) {

		}
		return false;
	}

}
