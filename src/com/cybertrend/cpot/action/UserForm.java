package com.cybertrend.cpot.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.RoleDAO;
import com.cybertrend.cpot.dao.ThemesDAO;
import com.cybertrend.cpot.dao.UserDAO;
import com.cybertrend.cpot.entity.User;
import com.cybertrend.cpot.util.ReadConfig;

import tableau.api.rest.bindings.TableauCredentialsType;

public class UserForm extends DefaultAction{
	static Logger logger = Logger.getLogger(UserForm.class);
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				getMenuAction(action, request);
				request.setAttribute("roles", RoleDAO.getList(getCurrentSite(request).getId()));
				request.setAttribute("userTableaus", getTableauService().invokeQueryUsersOnSite(getCurrentCredentials(request), Integer.parseInt(ReadConfig.get("tableau.users.max").trim()), 0).getUsers().getUser());
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void save(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				User user = new User();
				user.setCreateBy(getCurrentUser(request).getId());
				user.setUpdateBy(getCurrentUser(request).getId());
				user.setUsername(request.getParameter("username"));
				user.setRole(RoleDAO.getRoleById(request.getParameter("role")));
				user.setFullName(request.getParameter("fullName"));
				user.setAddress1(request.getParameter("address1"));
				user.setAddress2(request.getParameter("address2"));
				user.setAddress3(request.getParameter("address3"));
				user.setZip(request.getParameter("zip"));
				user.setEmail(request.getParameter("email"));
				user.setPhone(request.getParameter("phone"));
				user.setSiteId(getCurrentSite(request).getId());
				user.setThemes(ThemesDAO.getThemesById("1"));
				TableauCredentialsType credentials = getTableauService().invokeSignIn(user.getUsername(), request.getParameter("password"), getCurrentSite(request).getContentUrl()).getCredentials();
				PrintWriter out = response.getWriter();
				if(credentials!=null){
					user.setSiteUrl(getCurrentSite(request).getContentUrl());
					out.println(UserDAO.save(user));
				} else {
					out.print("ERROR : invalid Password!!");
					logger.info("Invalid Password!!!");
				}
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void list(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				getMenuAction(action, request);
				List<User> users = UserDAO.getList(getCurrentSite(request).getId());
				request.setAttribute("users", users);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void delete(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				getMenuAction(action, request);
				String userId = request.getParameter("userId");
				PrintWriter out = response.getWriter();
				out.write(UserDAO.delete("id",userId));
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}

}
