package com.cybertrend.pot.entity;

public class Dashboard extends BaseEntity {
	private String url;
	private String siteId;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
}
