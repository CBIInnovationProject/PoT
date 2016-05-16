package com.cybertrend.pot.action;

import javax.servlet.http.HttpServletRequest;

import com.cybertrend.pot.persistence.PersistenceAware;
import com.cybertrend.pot.persistence.PersistenceManager;
import com.cybertrend.pot.service.TableauService;

import tableau.api.rest.bindings.TableauCredentialsType;

public class DefaultAction implements PersistenceAware {
	public PersistenceManager persistence ;

	public static TableauService getTableauService() {
		return TableauService.instance();
	}

	@Override
	public void setPersistenceManager(PersistenceManager persistenceManager) {
		persistence = persistenceManager ;
	}
	
	public static TableauCredentialsType getCurrentCredentials(HttpServletRequest request){
		return (TableauCredentialsType) request.getSession().getAttribute("credential");
	}

}
