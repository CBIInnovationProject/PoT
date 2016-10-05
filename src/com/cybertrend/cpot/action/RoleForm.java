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

import com.cybertrend.cpot.Constants;
import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.MenuDAO;
import com.cybertrend.cpot.dao.RoleDAO;
import com.cybertrend.cpot.dao.RoleMenuDAO;
import com.cybertrend.cpot.entity.Menu;
import com.cybertrend.cpot.entity.Role;
import com.cybertrend.cpot.entity.RoleMenu;

public class RoleForm extends DefaultAction{
	static Logger logger = Logger.getLogger(RoleForm.class);
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
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
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				getMenuAction(action, request);
				Role role = new Role();
				role.setCreateBy(getCurrentUser(request).getId());
				role.setUpdateBy(getCurrentUser(request).getId());
				role.setName(request.getParameter("name"));
				role.setDescription(request.getParameter("description"));
				role.setSiteId(getCurrentCredentials(request).getSite().getId());
				PrintWriter out = response.getWriter();
				out.println(RoleDAO.save(role));
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
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				getMenuAction(action, request);
				List<Role> roles = RoleDAO.getList(getCurrentCredentials(request).getSite().getId());
				request.setAttribute("roles", roles);
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
	
	public static void detailRole(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
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
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
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
			if (Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				Role role = RoleDAO.getRoleById(request.getParameter("roleId"));
				Menu menu = MenuDAO.getMenuById(request.getParameter("menuId"));
				RoleMenu roleMenu = new RoleMenu();
				roleMenu.setRole(role);
				roleMenu.setMenu(menu);
				RoleMenuDAO.delete(role, menu);
			}
		}
	}
	

	public static void delete(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if (Interceptor.isAuthorized(action, request)){
				getMenuAction(action, request);
				String menuId = request.getParameter("roleId");
				PrintWriter out = response.getWriter();
				out.write(RoleDAO.delete(RoleDAO.getRoleById(menuId)));
			}
			else {
				request.getRequestDispatcher("/views/fragments/do-not-have-access.jsp").forward(request, response);
			}
		}
	}
}
