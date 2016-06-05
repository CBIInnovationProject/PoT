package com.cybertrend.pot;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cybertrend.pot.action.LandingPage;
import com.cybertrend.pot.action.Login;
import com.cybertrend.pot.action.Logout;
import com.cybertrend.pot.action.MenuForm;
import com.cybertrend.pot.action.ViewDashboard;
import com.cybertrend.pot.dao.MenuDAO;
import com.cybertrend.pot.entity.Menu;
import com.cybertrend.pot.util.PoTUtil;

public class PoTServlet extends HttpServlet {
	private ServletContext servletContext;

	public PoTServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.service("GET", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.service("POST", request, response);
	}

	protected void service(String method, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (servletContext == null) {
			servletContext = this.getServletContext();
		}

		StringBuffer url = request.getRequestURL();

		String action = PoTUtil.convertUrlIntoAction(url.toString());

		if (action != null) {
			if (!action.equals("")) {
				this.route(action, request, response);
			} else {
				servletContext.getRequestDispatcher("/views/fragments/404.jsp").forward(request, response);
			}
		}
	}

	/*
	 *  Actions Map
	 */
	private void route(String action, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			
			if (action.equals("loginForm.cbi")) {
				if(Interceptor.isLogin(request)!=false){
					response.sendRedirect("landingPage.cbi");
				} else
					servletContext.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
			} 
			else if (action.equals("login.cbi")) {
				Login.execute(request, response);
			}
			else if (action.equals("logout.cbi")) {
				Logout.execute(request, response);
			}
			
			else if (action.equals("landingPage.cbi")){
				LandingPage.execute(request, response, servletContext);
			}
			
			else if (action.equals("menuForm.cbi")){
				MenuForm.execute(request, response, action);
			}
			
			else if (action.equals("menuSave.cbi")){
				MenuForm.save(request, response, action);
			} 
			
			List<Menu> menus = MenuDAO.getActionsAndContents(Constants.CONTENT_TYPE_TABLEAU);
			
			for(Menu menu : menus){
				if (action.equals(menu.getAction())){
					ViewDashboard.execute(request, response, menu.getAction());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
}
