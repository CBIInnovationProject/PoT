package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.pot.entity.Role;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.DatabaseService;

public class RoleUserDAO {
	
	public static Role getRoleByUser(User user, String siteId) throws SQLException{
		Role role = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select role from roleUser WHERE user = ? AND siteId = ?");
		prep.setString(1, user.getId());
		prep.setString(2, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			role =  RoleDAO.getRoleById(result.getString("role"));
		}
		return role;
	}

}
