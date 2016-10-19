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

import com.cybertrend.cpot.entity.User;
import com.cybertrend.cpot.service.DatabaseService;

public class UserDAO {
	static Logger logger = Logger.getLogger(UserDAO.class);
	public static User getUserByUsernameAndSite(String username, String siteId) throws SQLException {
		User output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_user where username = ? AND siteId=?");
		prep.setString(1, username);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = getUserById(result.getString("id"));
		}
		return output;
	}
	
	public static User getUserById(String userId) throws SQLException {
		User output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from t_user where id = ?");
		prep.setString(1, userId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = new User();
			output.setId(result.getString("id"));
			output.setCreateBy(result.getString("createBy"));
			output.setCreateDate(result.getTimestamp("createDate"));
			output.setUpdateBy(result.getString("updateBy"));
			output.setUpdateDate(result.getTimestamp("updateDate"));
			
			output.setUsername(result.getString("username"));
			output.setSiteUrl(result.getString("siteUrl"));
			output.setFullName(result.getString("fullName"));
			output.setAddress1(result.getString("address1"));
			output.setAddress2(result.getString("address2"));
			output.setAddress3(result.getString("address3"));
			output.setZip(result.getString("zip"));
			output.setPhone(result.getString("phone"));
			output.setEmail(result.getString("email"));
			output.setRole(RoleDAO.getRoleById(result.getString("roleId")));
			output.setThemes(ThemesDAO.getThemesById(result.getString("themes")));
		}
		return output;
	}
	
	public static boolean authenticateUser(String userId) throws SQLException {
		boolean output = false;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_user where id = ?");
		prep.setString(1, userId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = true;
		}
		return output;
	}
	
	public static String save(User user){
		Connection conn = DatabaseService.getConnection();
		String sql = "INSERT INTO t_user (id, createBy, createDate, updateBy, updateDate, username, siteUrl, fullName, address1, address2, address3, zip, phone, email, siteId, roleId, themes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prep;
		try {
			logger.info("Query "+sql);
			String userId = UUID.randomUUID().toString();
			Timestamp currDate = new Timestamp(System.currentTimeMillis());
			prep = conn.prepareStatement(sql);
			prep.setString(1, userId);
			logger.info("User Id : "+userId);
			prep.setString(2, user.getCreateBy());
			logger.info("User Create By : "+user.getCreateBy());
			prep.setTimestamp(3, currDate);
			logger.info("User Create Date : "+user.getCreateDate());
			prep.setString(4, user.getUpdateBy());
			logger.info("User Update By : "+user.getUpdateBy());
			prep.setTimestamp(5, currDate);
			logger.info("User Update Date : "+user.getUpdateDate());
			
			prep.setString(6, user.getUsername());
			logger.info("User Update Date : "+user.getUpdateDate());
			prep.setString(7, user.getSiteUrl());
			logger.info("User Site Url : "+user.getSiteUrl());
			prep.setString(8, user.getFullName());
			logger.info("User FullName : "+user.getFullName());
			prep.setString(9, user.getAddress1());
			logger.info("User Address 1 : "+user.getAddress1());
			prep.setString(10, user.getAddress2());
			logger.info("User Address 2 : "+user.getAddress2());
			prep.setString(11, user.getAddress3());
			logger.info("User Address 3 : "+user.getAddress3());
			prep.setString(12, user.getZip());
			logger.info("User Zip : "+user.getZip());
			prep.setString(13, user.getPhone());
			logger.info("User Phone : "+user.getPhone());
			prep.setString(14, user.getEmail());
			logger.info("User Email : "+user.getEmail());
			prep.setString(15, user.getSiteId());
			logger.info("User Site Id : "+user.getSiteId());
			prep.setString(16, user.getRole().getId());
			logger.info("User Role Id : "+user.getRole().getId());
			prep.setString(17, user.getThemes().getId());
			logger.info("User Themes Id : "+user.getThemes().getId());
			prep.executeUpdate();
			logger.info("Insert User Success!!!");
			return "User <strong>"+user.getUsername()+"</strong> successfully added to record";
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return e.getMessage();
		}
	}
	
	public static List<User> getList(String siteId) throws SQLException{
		List<User> users = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_user WHERE siteId=?");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			User output =  getUserById(result.getString("id"));
			users.add(output);
		}
		return users;
	}
	
	public static List<User> getUserByUsername(String username) throws SQLException {
		List<User> users = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_user WHERE username=?");
		prep.setString(1, username);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			User output =  getUserById(result.getString("id"));
			users.add(output);
		}
		return users;
	}
	
	public static String delete(String param, String value) {
		Connection conn = DatabaseService.getConnection();
		String sql = "DELETE FROM t_user WHERE "+param+" = ? ";
		PreparedStatement prep;
		try {
			logger.info("Query "+sql);
			prep = conn.prepareStatement(sql);
			prep.setString(1, value);
			logger.info("User Value of "+param+" : "+value);
			prep.executeUpdate();
			logger.info("Delete User Success!!");
			return "User <strong> "+value+"</strong> has been deleted";
		} 
		catch(SQLException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return e.getMessage();
		}
	}
}
