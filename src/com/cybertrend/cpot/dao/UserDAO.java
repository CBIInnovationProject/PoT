package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cybertrend.cpot.entity.User;
import com.cybertrend.cpot.service.DatabaseService;

public class UserDAO {
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
		User user = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from t_user where id = ?");
		prep.setString(1, userId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			user =  getUserById(result.getString("id"));
			output = true;
		}
		return output;
	}
	
	public static String save(User user){
		Connection conn = DatabaseService.getConnection();
		String sql = "INSERT INTO t_user (id, createBy, createDate, updateBy, updateDate, username, siteUrl, fullName, address1, address2, address3, zip, phone, email, siteId, roleId, themes) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prep;
		try {
			prep = conn.prepareStatement(sql);
			prep.setString(1, System.currentTimeMillis()+""+new Random().nextFloat());
			prep.setString(2, user.getCreateBy());
			prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			prep.setString(4, user.getUpdateBy());
			prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			
			prep.setString(6, user.getUsername());
			prep.setString(7, user.getSiteUrl());
			prep.setString(8, user.getFullName());
			prep.setString(9, user.getAddress1());
			prep.setString(10, user.getAddress2());
			prep.setString(11, user.getAddress3());
			prep.setString(12, user.getZip());
			prep.setString(13, user.getPhone());
			prep.setString(14, user.getEmail());
			prep.setString(15, user.getSiteId());
			prep.setString(16, user.getRole().getId());
			prep.setString(17, user.getThemes().getId());
			prep.executeUpdate();
			return "User <strong>"+user.getUsername()+"</strong> successfully added to record";
		} catch (SQLException e) {
			e.printStackTrace();
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
}
