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
		PreparedStatement prep = conn.prepareStatement("INSERT INTO dashboard(id, createDate, createBy, url, workbookId, siteId) VALUES (?,?,?,?,?,?)");
		prep.setString(1, dashboard.getId());
		prep.setTimestamp(2, dashboard.getCreateDate());
		prep.setString(3, dashboard.getCreateBy());
		prep.setString(4, dashboard.getUrl());
		prep.setString(5, dashboard.getWorkbookId());
		prep.setString(6, dashboard.getSiteId());
		prep.executeUpdate();
	}
	
	public static void delete(String id) throws SQLException {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("DELETE FROM dashboard WHERE id = ?");
		prep.setString(1, id);
		prep.executeUpdate();
	}
	
	public static boolean isDasboardExist(String id) throws SQLException {
		Dashboard dashboard = getDashboardById(id);
		if (dashboard!=null)
			return true;
		else
			return false;
	}
	
	public static List<Dashboard> getListDashboards() throws SQLException {
		List<Dashboard> dashboards = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM dashboard");
		ResultSet result = prep.executeQuery();
		while(result.next()){
			Dashboard dashboard = getDashboardById(result.getString("id"));
			dashboards.add(dashboard);
		}
		return dashboards;
	}
	
	public static Dashboard getDashboardById(String id) throws SQLException {
		Dashboard dash = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM dashboard WHERE id=?");
		prep.setString(1, id);
		ResultSet result = prep.executeQuery();
		while(result.next()){
			dash = new Dashboard();
			dash.setId(id);
			dash.setCreateBy(result.getString("createBy"));
			dash.setCreateDate(result.getTimestamp("createDate"));
			dash.setUrl(result.getString("url"));
			dash.setWorkbookId(result.getString("workbookId"));
			dash.setSiteId(result.getString("siteId"));
		}
		return dash;
	}
	
}
