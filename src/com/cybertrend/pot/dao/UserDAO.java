package com.cybertrend.pot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.pot.entity.Role;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.DatabaseService;

public class UserDAO {
	public static User getUserByUsername(String username) throws SQLException {
		User output = null;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from user where username = ?");
		prep.setString(1, username);
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
		}
		return output;
	}
	
	public static boolean authenticateUser(String username, String password) throws SQLException {
		boolean output = false;
		User temp = getUserByUsername(username);
		if (temp != null) {
			if (temp.getPassword().equals(password)) {
				output = true;
			}
		}
		return output;
	}
	
	public static void save(User user) throws SQLException{
		Connection conn = DatabaseService.getConnection();
		String sql = "INSERT INTO user (id, username, password, fullName, address1, address2, address3, zip, phone, email, createBy, createDate) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.setString(1, user.getId());
		prep.setString(2, user.getUsername());
		prep.setString(3, user.getPassword());
		prep.setString(4, user.getFullName());
		prep.setString(5, user.getAddress1());
		prep.setString(6, user.getAddress2());
		prep.setString(7, user.getAddress3());
		prep.setString(8, user.getZip());
		prep.setString(9, user.getPhone());
		prep.setString(10, user.getEmail());
		prep.setString(11, user.getCreateBy());
		prep.setTimestamp(12, user.getCreateDate());
		prep.executeUpdate();
	}
	
	public static List<User> getUsers() throws SQLException{
		List<User> users = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from user");
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			User output =  new User();
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
			users.add(output);
		}
		return users;
	}
}
