package com.cybertrend.pot.entity;

public class MenuPrivilage extends BaseEntity{
	private Menu menu;
	private User user;
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

}
