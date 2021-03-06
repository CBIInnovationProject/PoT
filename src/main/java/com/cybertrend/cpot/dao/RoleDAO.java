package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;
import com.cybertrend.cpot.entity.Role;
import com.cybertrend.cpot.service.DatabaseService;

public class RoleDAO {
	static Logger logger = Logger.getLogger(RoleDAO.class);
	
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
	
	public static String save(Role role) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "INSERT INTO t_role(id, createBy, createDate, updateBy, updateDate, name, description, siteId ) VALUES (?,?,?,?,?,?,?,?)";
		try {
			String roleId = UUID.randomUUID().toString();
			Timestamp currDate = new Timestamp(System.currentTimeMillis());
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, roleId );
			logger.info("Role Id : "+roleId);
			prep.setString(2, role.getCreateBy());
			logger.info("Role Create By : "+role.getCreateBy());
			prep.setTimestamp(3, currDate );
			logger.info("Role Create Date : "+currDate);
			prep.setString(4, role.getUpdateBy());
			logger.info("Role Update By : "+role.getUpdateBy());
			prep.setTimestamp(5, currDate);
			logger.info("Role Update Date : "+currDate);
			
			prep.setString(6, role.getName());
			logger.info("Role Name : "+role.getName());
			prep.setString(7, role.getDescription());
			logger.info("Role Description : "+role.getDescription());
			prep.setString(8, role.getSiteId());
			logger.info("Site Id : "+role.getSiteId());
			prep.executeUpdate();
			logger.info("Insert Role Success!!!");
			return "Role <strong>"+role.getName()+"</strong> successfully added to record";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
	public static List<Role> getList(String siteId) throws SQLException {
		List<Role> roles = new ArrayList<Role>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM t_role WHERE siteId=? OR id='0'");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Role role = getRoleById(result.getString("id"));
			roles.add(role);
		}
		return roles;
	}
	
	public static String delete(Role role) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "DELETE FROM t_role WHERE id=?";
		try {
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, role.getId());
			logger.info("Role id : "+role.getId());
			prep.executeUpdate();
			logger.info("Delete Role Success!!!");
			return "Role <strong>"+role.getName()+"</strong> has been deleted";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
	public static String update(Role role) {
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep;
		String sql = "UPDATE t_role SET updateBy=?, updateDate=?, name=?, description=?, siteId=? WHERE id=?";
		try {
			Timestamp currDate = new Timestamp(System.currentTimeMillis());
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, role.getUpdateBy());
			logger.info("Role Update By : "+role.getUpdateBy());
			prep.setTimestamp(2, currDate);
			logger.info("Role Update Date : "+currDate);
			prep.setString(3, role.getName());
			logger.info("Role Name : "+role.getName());
			prep.setString(4, role.getDescription());
			logger.info("Role Description : "+role.getDescription());
			prep.setString(5, role.getSiteId());
			logger.info("Site Id : "+role.getSiteId());
			prep.setString(6, role.getId());
			logger.info("Role Id : "+role.getId());
			prep.executeUpdate();
			logger.info("Update Role Success!!!");
			return "Role <strong>"+role.getName()+"</strong> successfully updated";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}

}
