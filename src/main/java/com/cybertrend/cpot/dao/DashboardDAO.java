package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.cybertrend.cpot.entity.Dashboard;
import com.cybertrend.cpot.service.DatabaseService;

public class DashboardDAO {
	static Logger logger = Logger.getLogger(DashboardDAO.class);
	public static void add(Dashboard dashboard) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "INSERT INTO t_dashboard(id, createDate, createBy, url, workbookId, siteId) VALUES (?,?,?,?,?,?)";
		try {
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, dashboard.getId());
			logger.info("Dashboard Id : "+dashboard.getId());
			prep.setTimestamp(2, dashboard.getCreateDate());
			logger.info("Dashboard Create Date : "+dashboard.getCreateDate());
			prep.setString(3, dashboard.getCreateBy());
			logger.info("Dashboard Create By : "+dashboard.getCreateBy());
			prep.setString(4, dashboard.getUrl());
			logger.info("Dashboard Url : "+dashboard.getUrl());
			prep.setString(5, dashboard.getWorkbookId());
			logger.info("Dashboard Workbook Id : "+dashboard.getWorkbookId());
			prep.setString(6, dashboard.getSiteId());
			logger.info("Dashboard Site Id : "+dashboard.getSiteId());
			prep.executeUpdate();
			logger.info("Insert dashboard Success !!!");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void delete(String id) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "DELETE FROM t_dashboard WHERE id = ?";
		try {
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, id);
			logger.info("Dashboard Id : "+id);
			prep.executeUpdate();
			logger.info("Delete dashboard Success !!!");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
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
		PreparedStatement prep = conn.prepareStatement("SELECT d.* FROM t_dashboard d LEFT JOIN t_menu m ON d.id = m.viewid WHERE m.id is null");
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
		PreparedStatement prep = conn.prepareStatement("SELECT * FROM t_dashboard WHERE id=?");
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
