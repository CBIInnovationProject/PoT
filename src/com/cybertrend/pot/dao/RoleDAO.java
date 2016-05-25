package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.pot.entity.Role;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.DatabaseService;

public class RoleDAO {
	
	public static Role getRoleById(String id) throws SQLException {
		Role output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from role WHERE id = ?");
		prep.setString(1, id);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = new Role();
			output.setId(result.getString("id"));
			output.setCreateBy(result.getString("createBy"));
			output.setCreateDate(result.getTimestamp("createDate"));
			output.setUpdateBy(result.getString("updateBy"));
			output.setUpdateDate(result.getTimestamp("updateDate"));
			
			output.setName(result.getString("name"));
			output.setDescription(result.getString("description"));
		}
		return output;
	}

}
