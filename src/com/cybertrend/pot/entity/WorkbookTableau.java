package com.cybertrend.pot.entity;

import tableau.api.rest.bindings.WorkbookType;

public class WorkbookTableau {
	private WorkbookType workbookType;
	private String image ;
	public WorkbookType getWorkbookType() {
		return workbookType;
	}
	public void setWorkbookType(WorkbookType workbookType) {
		this.workbookType = workbookType;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
