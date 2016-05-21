package com.cybertrend.pot.action;

import javax.servlet.http.HttpServletRequest;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.service.TableauService;

import tableau.api.rest.bindings.TableauCredentialsType;

public class DefaultAction{

	public static TableauService getTableauService() {
		return TableauService.instance();
	}
	
	public static TableauCredentialsType getCurrentCredentials(HttpServletRequest request){
		return (TableauCredentialsType) request.getSession().getAttribute(Constants.TABLEAU_CREDENTIALS);
	}

}
