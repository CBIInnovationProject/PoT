package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.UserTableau;
import com.cybertrend.pot.service.DatabaseService;

public class UserTableauDAO {
	public static UserTableau getUserTableauById(String userTableauId) throws SQLException {
		UserTableau output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from usertableau where id = ?");
		prep.setString(1, userTableauId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = new UserTableau();
			output.setId(result.getString("id"));
			output.setCreateBy(result.getString("createBy"));
			output.setCreateDate(result.getTimestamp("createDate"));
			output.setUpdateBy(result.getString("updateBy"));
			output.setUpdateDate(result.getTimestamp("updateDate"));
			
			output.setUsername(result.getString("username"));
			output.setPassword(result.getString("password"));
			output.setSiteContentUrl(result.getString("contentUrl"));
			output.setCapacity(Integer.parseInt(result.getString("capacity")));
			output.setInUse(Integer.parseInt(result.getString("inUse")));
		}
		return output;
	}
	
	public static List<UserTableau> getList(String contentUrl) throws SQLException {
		List<UserTableau> userTableaus = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from usertableau WHERE contentUrl=?");
		prep.setString(1, contentUrl);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			UserTableau output = getUserTableauById(result.getString("id"));
			userTableaus.add(output);
		}
		return userTableaus;
	}

}
