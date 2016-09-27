package com.cybertrend.cpot.entity;

import java.sql.Timestamp;

public class Subscription {
	private String id;
	private User subscribeBy;
	private Timestamp subscribeDate;
	private String cronsyntax;
	private String description;
	private String contentUrl;
	private String menuId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getSubscribeBy() {
		return subscribeBy;
	}
	public void setSubscribeBy(User subscribeBy) {
		this.subscribeBy = subscribeBy;
	}
	public Timestamp getSubscribeDate() {
		return subscribeDate;
	}
	public void setSubscribeDate(Timestamp subscribeDate) {
		this.subscribeDate = subscribeDate;
	}
	public String getCronsyntax() {
		return cronsyntax;
	}
	public void setCronsyntax(String cronsyntax) {
		this.cronsyntax = cronsyntax;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContentUrl() {
		return contentUrl;
	}
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
}
