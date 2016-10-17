package com.cybertrend.cpot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.cpot.Constants;
import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.RoleDAO;
import com.cybertrend.cpot.dao.ThemesDAO;
import com.cybertrend.cpot.dao.UserDAO;
import com.cybertrend.cpot.entity.User;
import com.cybertrend.cpot.util.ReadConfig;
import com.cybertrend.cpot.util.StringUtil;

import tableau.api.rest.bindings.SiteType;
import tableau.api.rest.bindings.TableauCredentialsType;

public class Login extends DefaultAction{
	
	public static void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		boolean AUTH = UserDAO.authenticateUser(request.getParameter("userId"));
		if (AUTH || Interceptor.isLogin(request)==true) {
			response.sendRedirect("landingPage.cbi");
		} else {
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		} 
		User user = UserDAO.getUserById(request.getParameter("userId"));
		String username = user.getUsername();
		String password = StringUtil.decodeBase64(request.getParameter("password"));
		String contentUrl = (user.getSiteUrl()+" ").trim();
		TableauCredentialsType credentials = getTableauService().invokeSignIn(username, password, contentUrl).getCredentials();
		request.getSession().setAttribute(Constants.USER_GA, user);
		request.getSession().setAttribute(Constants.TABLEAU_CREDENTIALS, credentials);
//		request.getSession().setAttribute(Constants.TABLEAU_WORKBOOKS, getTableauService().invokeQueryWorkbooks(credentials, Integer.parseInt(ReadConfig.get("tableau.workbooks.max").trim()), 0).getWorkbooks().getWorkbook());
	}
	
	public static void selectSite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if(username.trim().equals(ReadConfig.get("tableau.admin.default"))){
			UserDAO.delete("username", username.trim());
			TableauCredentialsType credentials = getTableauService().invokeSignIn(username, password, "").getCredentials();
			List<SiteType> sites = getTableauService().invokeQuerySites(credentials).getSite();
			User [] userList = new User[sites.size()];
			for(int i = 0; i<userList.length; i++){
				userList[i] = new User();
				userList[i].setUsername(getTableauService().invokeGetUser(credentials, credentials.getUser().getId()).getName());
				userList[i].setFullName(getTableauService().invokeGetUser(credentials, credentials.getUser().getId()).getFullName());
				userList[i].setSiteUrl(sites.get(i).getContentUrl());
				userList[i].setSiteId(sites.get(i).getId());
				userList[i].setRole(RoleDAO.getRoleById("0"));
				userList[i].setThemes(ThemesDAO.getThemesById("1"));
				UserDAO.save(userList[i]);
			}
		}
		
		List<User> users = UserDAO.getUserByUsername(username);
		
		request.setAttribute("users", users );
		request.setAttribute("password", StringUtil.encodeBase64(password));
		request.setAttribute("username", username);
		if(users.size()>0) {
			if(users.size()==1) {
				response.sendRedirect("landingPage.cbi");
				User user = UserDAO.getUserById(users.get(0).getId());
				String contentUrl = (user.getSiteUrl()+" ").trim();
				TableauCredentialsType credentials = getTableauService().invokeSignIn(username, password, contentUrl).getCredentials();
				request.getSession().setAttribute(Constants.USER_GA, user);
				request.getSession().setAttribute(Constants.TABLEAU_CREDENTIALS, credentials);
			}
			else request.getRequestDispatcher("/views/siteSelector.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
	}
}
