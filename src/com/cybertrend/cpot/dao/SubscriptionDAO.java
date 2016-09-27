package com.cybertrend.cpot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cybertrend.cpot.entity.Subscription;
import com.cybertrend.cpot.service.DatabaseService;

public class SubscriptionDAO {
	public static Subscription getSubscriptionById(String id) throws SQLException {
		Subscription subscription = null ;
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("select * from t_subscription WHERE id = ?");
		prep.setString(1, id);
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			subscription = new Subscription();
			subscription.setSubscribeBy(UserDAO.getUserById(result.getString("subscribeBy")));
			subscription.setSubscribeDate(result.getTimestamp("subscribeDate"));
			subscription.setCronsyntax(result.getString("cronsyntax"));
			subscription.setDescription(result.getString("description"));
			subscription.setContentUrl(result.getString("contentUrl"));
			subscription.setMenuId(result.getString("menuId"));
		}
		return subscription;
	}
	
	public static List<Subscription> getList() throws SQLException{
		List<Subscription> subscriptions = new ArrayList<>();
		Connection conn = DatabaseService.getConnection();
		PreparedStatement prep = conn.prepareStatement("SELECT id FROM t_subscription");
		ResultSet result = prep.executeQuery();
		while (result.next()) {
			Subscription subscription = getSubscriptionById(result.getString("id"));
			subscriptions.add(subscription);
		}
		return subscriptions;
	}
}
