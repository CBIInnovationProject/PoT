package com.cybertrend.pot.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.RoleDAO;
import com.cybertrend.pot.dao.UserDAO;
import com.cybertrend.pot.dao.UserTableauDAO;
import com.cybertrend.pot.entity.User;
import com.cybertrend.pot.service.TableauService;

public class UserForm extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				request.setAttribute("roles", RoleDAO.getList(getCurrentCredentials(request).getSite().getId()));
				request.setAttribute("userTableaus", UserTableauDAO.getList(getCurrentCredentials(request).getSite().getId()));
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void save(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				User user = new User();
				user.setCreateBy(getCurrentUser(request).getId());
				user.setUpdateBy(getCurrentUser(request).getId());
				user.setUsername(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				user.setUserTableau(UserTableauDAO.getUserTableauById(request.getParameter("userTableau")));
				user.setRole(RoleDAO.getRoleById(request.getParameter("role")));
				user.setFullName(request.getParameter("fullName"));
				user.setAddress1(request.getParameter("address1"));
				user.setAddress2(request.getParameter("address2"));
				user.setAddress3(request.getParameter("address3"));
				user.setZip(request.getParameter("zip"));
				user.setEmail(request.getParameter("email"));
				user.setPhone(request.getParameter("phone"));
				user.setSiteId(getCurrentCredentials(request).getSite().getId());
				UserDAO.save(user);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void list(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
				List<User> users = UserDAO.getList(getCurrentCredentials(request).getSite().getId());
				request.setAttribute("users", users);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}

}
