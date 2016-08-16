package com.cybertrend.pot.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.Constants;
import com.cybertrend.pot.Interceptor;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.dao.RoleDAO;
import com.cybertrend.pot.dao.RoleMenuDAO;
import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.entity.Role;
import com.cybertrend.pot.entity.RoleMenu;

public class RoleForm extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				getMenuAction(action, request);
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
				getMenuAction(action, request);
				Role role = new Role();
				role.setCreateBy(getCurrentUser(request).getUsername());
				role.setUpdateBy(getCurrentUser(request).getUsername());
				role.setName(request.getParameter("name"));
				role.setDescription(request.getParameter("description"));
				role.setSiteId(getCurrentCredentials(request).getSite().getId());
				RoleDAO.save(role);
				PrintWriter out = response.getWriter();
			    out.println(Constants.SUCCESS);
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
				List<Role> roles = RoleDAO.getList(getCurrentCredentials(request).getSite().getId());
				request.setAttribute("roles", roles);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void menuPrivilegeForm(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				Role role = RoleDAO.getRoleById(request.getParameter("roleId"));
				List<Menu> menus = MenuDAO.getMenuParents(getCurrentCredentials(request).getSite().getId());
				request.setAttribute("menus", menus);
				request.setAttribute("role", role);
				if(request.getParameter("menuId")!=null&&request.getParameter("menuId").length()>0){
					 if(request.getParameter("deleteAction")!=null){
							removeMenuFromRole(request, response, action);
					} else {
						addMenuToRole(request, response, action);
						PrintWriter out = response.getWriter();
					    out.println(Constants.SUCCESS);
					}
				}
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}

	private static void addMenuToRole(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				Role role = RoleDAO.getRoleById(request.getParameter("roleId"));
				Menu menu = MenuDAO.getMenuById(request.getParameter("menuId"));
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRole(role);
				roleMenu.setMenu(menu);
				roleMenu.setCreateBy(getCurrentUser(request).getUsername());
				RoleMenuDAO.save(roleMenu);
			}
		}
	}
	
	private static void removeMenuFromRole(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (getCurrentRole(request).getId().equals("0")){
				Role role = RoleDAO.getRoleById(request.getParameter("roleId"));
				Menu menu = MenuDAO.getMenuById(request.getParameter("menuId"));
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRole(role);
				roleMenu.setMenu(menu);
				RoleMenuDAO.delete(role, menu);
			}
		}
	}
}
