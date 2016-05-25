package com.cybertrend.pot.entity;

public class UserTableau extends BaseEntity{
	private String username;
	private String password;
	private String siteRole;
	private String siteId;
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
	public String getSiteRole() {
		return siteRole;
	}
	public void setSiteRole(String siteRole) {
		this.siteRole = siteRole;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
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
