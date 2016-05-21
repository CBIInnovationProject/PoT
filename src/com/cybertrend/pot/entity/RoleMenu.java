package com.cybertrend.pot.entity;

public class RoleMenu extends BaseEntity{
	private Role role;
	private Menu menu;
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	

}
