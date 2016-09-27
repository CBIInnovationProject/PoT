package com.cybertrend.pot.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.pot.service.DatabaseService;

public class PropertyLooker {
	public static String get(String propertyName) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement("select * from t_properties where name = ?");
			prep.setString(1, propertyName);
			ResultSet result = prep.executeQuery();
			while (result.next()) {
				return result.getString("value");
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
