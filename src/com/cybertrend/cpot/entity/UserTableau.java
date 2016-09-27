package com.cybertrend.cpot.entity;

public class UserTableau extends BaseEntity{
	private String username;
	private String password;
	private String siteContentUrl;
	private int inUse;
	private int capacity;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSiteContentUrl() {
		return siteContentUrl;
	}
	public void setSiteContentUrl(String siteContentUrl) {
		this.siteContentUrl = siteContentUrl;
	}
	public int getInUse() {
		return inUse;
	}
	public void setInUse(int inUse) {
		this.inUse = inUse;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
}
