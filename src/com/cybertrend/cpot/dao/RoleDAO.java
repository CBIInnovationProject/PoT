package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cybertrend.cpot.entity.Role;
import com.cybertrend.cpot.service.DatabaseService;

public class RoleDAO {
	
	public static Role getRoleById(String id) throws SQLException {
		Role output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from t_role WHERE id = ?");
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
	
	public static boolean save(Role role) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement("INSERT INTO t_role(id, createBy, createDate, updateBy, updateDate, name, description, siteId ) VALUES (?,?,?,?,?,?,?,?)");
			prep.setString(1, System.currentTimeMillis()+"-"+new Random().nextLong());
			prep.setString(2, role.getCreateBy());
			prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			prep.setString(4, role.getUpdateBy());
			prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			
			prep.setString(6, role.getName());
			prep.setString(7, role.getDescription());
			prep.setString(8, role.getSiteId());
			
			prep.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<Role> getList(String siteId) throws SQLException {
		List<Role> roles = new ArrayList<Role>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM t_role WHERE siteId=?");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Role role = getRoleById(result.getString("id"));
			roles.add(role);
		}
		return roles;
	}

}
