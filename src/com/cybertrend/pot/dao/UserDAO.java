package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.DatabaseService;

public class UserDAO {
	public static User getUserByUsernameAndSite(String username, String siteId) throws SQLException {
		User output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from user where username = ? AND siteId=?");
		prep.setString(1, username);
		List<User> users = new ArrayList<User>();
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			output = getUserById(result.getString("id"));
		}
		return output;
	}
	
	public static User getUserById(String userId) throws SQLException {
		User output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from user where id = ?");
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
			output.setPassword(result.getString("password"));
			output.setFullName(result.getString("fullName"));
			output.setAddress1(result.getString("address1"));
			output.setAddress2(result.getString("address2"));
			output.setAddress3(result.getString("address3"));
			output.setZip(result.getString("zip"));
			output.setPhone(result.getString("phone"));
			output.setEmail(result.getString("email"));
			output.setRole(RoleDAO.getRoleById(result.getString("roleId")));
			output.setUserTableau(UserTableauDAO.getUserTableauById(result.getString("userTableauId")));
		}
		return output;
	}
	
	public static boolean authenticateUser(String userId) throws SQLException {
		boolean output = false;
		User user = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from user where id = ?");
		prep.setString(1, userId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			user =  getUserById(result.getString("id"));
			output = true;
		}
		return output;
	}
	
	public static void save(User user) throws SQLException{
		Connection conn = DatabaseService.getConnection();
		String sql = "INSERT INTO user (id, createBy, createDate, updateBy, updateDate, username, password, fullName, address1, address2, address3, zip, phone, email, siteId, roleId, userTableauId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.setString(1, System.currentTimeMillis()+"");
		prep.setString(2, user.getCreateBy());
		prep.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
		prep.setString(4, user.getUpdateBy());
		prep.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		
		prep.setString(6, user.getUsername());
		prep.setString(7, user.getPassword());
		prep.setString(8, user.getFullName());
		prep.setString(9, user.getAddress1());
		prep.setString(10, user.getAddress2());
		prep.setString(11, user.getAddress3());
		prep.setString(12, user.getZip());
		prep.setString(13, user.getPhone());
		prep.setString(14, user.getEmail());
		prep.setString(15, user.getSiteId());
		prep.setString(16, user.getRole().getId());
		prep.setString(17, user.getUserTableau().getId());
		prep.executeUpdate();
	}
	
	public static List<User> getList(String siteId) throws SQLException{
		List<User> users = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from user WHERE siteId=?");
		prep.setString(1, siteId);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			User output =  getUserById(result.getString("id"));
			users.add(output);
		}
		return users;
	}
	
	public static List<User> getUserByUsernameAndPassword(String username, String password) throws SQLException {
		List<User> users = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select id from user WHERE username=? AND password=?");
		prep.setString(1, username);
		prep.setString(2, password);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			User output =  getUserById(result.getString("id"));
			users.add(output);
		}
		return users;
	}
}
