package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.cpot.entity.Themes;
import com.cybertrend.cpot.service.DatabaseService;

public class ThemesDAO {
	public static Themes getThemesById(String id)  throws SQLException {
		Themes output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from t_themes WHERE id = ?");
		prep.setString(1, id);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = new Themes();
			output.setId(result.getString("id"));
			
			output.setName(result.getString("name"));
			output.setPath(result.getString("path"));
			output.setUl(result.getString("ul"));
			output.setLi(result.getString("li"));
		}
		return output;
	}
}
