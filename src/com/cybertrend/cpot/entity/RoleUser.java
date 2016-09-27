package com.cybertrend.cpot.entity;

public class RoleUser extends BaseEntity{
	private User user ;
	private Role role;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
}
