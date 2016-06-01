package com.cybertrend.pot.entity;

public class Menu extends BaseEntity{
	private String name ;
	private String description;
	private int parentId ;
	private String action ;
	private String content ;
	private String contentType ; // tableau or page
	private String contentHeight;
	private String contentWidth;
	
	private int menuOrder;
	private String icon;
	private String workbookId;
	private String siteId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUrl() {
		return content;
	}
	public void setContent(String url) {
		this.content = url;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getContentHeight() {
		return contentHeight;
	}
	public void setContentHeight(String contentHeight) {
		this.contentHeight = contentHeight;
	}
	public String getContentWidth() {
		return contentWidth;
	}
	public void setContentWidth(String contentWidth) {
		this.contentWidth = contentWidth;
	}
	public int getMenuOrder() {
		return menuOrder;
	}
	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getWorkbookId() {
		return workbookId;
	}
	public void setWorkbookId(String workbookId) {
		this.workbookId = workbookId;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	
}
