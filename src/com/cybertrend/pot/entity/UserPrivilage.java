package com.cybertrend.pot.entity;

public class UserPrivilage extends BaseEntity {
	private Role role;
	private User user;
	private UserTableau userTableau;
	private SiteTableau siteTableau;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserTableau getUserTableau() {
		return userTableau;
	}
	public void setUserTableau(UserTableau userTableau) {
		this.userTableau = userTableau;
	}
	public SiteTableau getSiteTableau() {
		return siteTableau;
	}
	public void setSiteTableau(SiteTableau siteTableau) {
		this.siteTableau = siteTableau;
	}

}
