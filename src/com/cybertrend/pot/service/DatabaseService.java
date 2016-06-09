package com.cybertrend.pot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cybertrend.pot.util.DBPropertyLooker;

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
			String user = DBPropertyLooker.get("db.username");
			String pass = DBPropertyLooker.get("db.password");
			Class.forName(DBPropertyLooker.get("db.driver"));
			output = DriverManager.getConnection(DBPropertyLooker.get("db.url"), user, pass);
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
