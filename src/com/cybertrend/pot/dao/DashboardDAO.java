package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.Dashboard;
import com.cybertrend.pot.service.DatabaseService;

public class DashboardDAO {
	public static void add (Dashboard dashboard) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("INSERT INTO dashboard(id, createDate, createBy, url, siteId) VALUES (?,?,?,?,?)");
		prep.setString(1, dashboard.getId());
		prep.setTimestamp(2, dashboard.getCreateDate());
		prep.setString(3, dashboard.getCreateBy());
		prep.setString(4, dashboard.getUrl());
		prep.setString(5, dashboard.getSiteId());
		prep.executeUpdate();
	}
	
	public static void delete(String id) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM dashboard WHERE id = ?");
		prep.setString(1, id);
		prep.executeUpdate();
	}
	
	public static boolean isDasboardExist(String id) throws SQLException {
		String url = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM dashboard WHERE id=?");
		prep.setString(1, id);
		ResultSet result = prep.executeQuery();
		while(result.next()){
			url = result.getString("url");
		}
		if (url!=null)
			return true;
		else
			return false;
	}
	
	public static List<Dashboard> getListDashboards() throws SQLException {
		List<Dashboard> dashboards = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM dashboard");
		ResultSet result = prep.executeQuery();
		while(result.next()){
			Dashboard dashboard = new Dashboard();
			dashboard.setId(result.getString("id"));
			dashboard.setCreateBy(result.getString("createBy"));
			dashboard.setCreateDate(result.getTimestamp("createDate"));
			dashboard.setUrl(result.getString("url"));
			dashboard.setSiteId(result.getString("siteId"));
			dashboards.add(dashboard);
		}
		return dashboards;
	}
	
}
