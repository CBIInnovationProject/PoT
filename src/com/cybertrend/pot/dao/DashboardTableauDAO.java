package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cybertrend.pot.entity.DashboardTableau;
import com.cybertrend.pot.service.DatabaseService;

public class DashboardTableauDAO {
	public static DashboardTableau getDashboardByUrl(String url) throws SQLException {
		DashboardTableau output = null ;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from dashboardtableau where name = ?");
		prep.setString(1, url);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = new DashboardTableau();
			output.setId(result.getString("id"));
			output.setCreateBy(result.getString("createBy"));
			output.setCreateDate(result.getTimestamp("createDate"));
			output.setUpdateBy(result.getString("updateBy"));
			output.setUpdateDate(result.getTimestamp("updateDate"));
			
			output.setName(result.getString("name"));
			output.setTabs(result.getBoolean("tabs"));
			output.setToolbar(result.getBoolean("toolbar"));
		}
		return output;
	}

}
