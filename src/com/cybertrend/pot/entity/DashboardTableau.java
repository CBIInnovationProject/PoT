package com.cybertrend.pot.entity;

public class DashboardTableau extends BaseEntity {
	private String name ;
	private boolean tabs;
	private boolean toolbar;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isTabs() {
		return tabs;
	}
	public void setTabs(boolean tabs) {
		this.tabs = tabs;
	}
	public boolean isToolbar() {
		return toolbar;
	}
	public void setToolbar(boolean toolbar) {
		this.toolbar = toolbar;
	}
	
}
