package com.cybertrend.cpot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cybertrend.cpot.util.ReadConfig;

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
			String user = ReadConfig.get("db.username");
			String pass = ReadConfig.get("db.password");
			Class.forName(ReadConfig.get("db.driver"));
			output = DriverManager.getConnection(ReadConfig.get("db.url"), user, pass);
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
