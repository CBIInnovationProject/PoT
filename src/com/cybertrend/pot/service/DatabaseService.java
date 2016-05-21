package com.cybertrend.pot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cybertrend.pot.util.PropertyLooker;

public class DatabaseService {
	private static Connection connection;
	public static Connection getConnection() {

		try {
			if (connection == null || connection.isClosed()) {
				connection = establishConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	private static Connection establishConnection() {
		Connection output = null;

		try {
			String user = PropertyLooker.get("db.user");
			String pass = PropertyLooker.get("db.password");
			Class.forName(PropertyLooker.get("db.driver"));
			output = DriverManager.getConnection(PropertyLooker.get("db.url"), user, pass);
			if (output == null) {
				System.out.println("connection null!");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}
