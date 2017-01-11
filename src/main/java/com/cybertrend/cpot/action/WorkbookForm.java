package com.cybertrend.cpot.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import com.cybertrend.cpot.Constants;
import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.DashboardDAO;
import com.cybertrend.cpot.entity.Dashboard;
import com.cybertrend.cpot.entity.WorkbookTableau;
import com.cybertrend.cpot.util.ReadConfig;

import tableau.api.rest.bindings.UserType;
import tableau.api.rest.bindings.WorkbookType;

public class WorkbookForm extends DefaultAction{
	static Logger logger = Logger.getLogger(WorkbookForm.class);
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			request.setAttribute("workbooks", getTableauService(request).invokeQueryWorkbooks(getCurrentCredentials(request), Integer.parseInt(ReadConfig.get("tableau.workbooks.max").trim()), 0).getWorkbooks().getWorkbook());
			request.setAttribute("credential", getCurrentCredentials(request));
			if(request.getParameter("workbookId")!=null){
				if(request.getParameter("url")!=null){
					if(request.getParameter("viewId")!=null){
						detail(request, response, action);
						addToPortal(request, response, action);
						PrintWriter out = response.getWriter();
					    out.println(Constants.SUCCESS);
					} else {
						viewDashboard(request, response, action);
					}
				} else {
					if(request.getParameter("deleteAction")!=null){
						detail(request, response, action);
						removeFromPortal(request, response, action);
						request.getRequestDispatcher("/views/workbook/workbookDetail.jsp").forward(request, response);
					} else{
						detail(request, response, action);
						request.getRequestDispatcher("/views/workbook/workbookDetail.jsp").forward(request, response);
					}
				}
			} else{ 
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				request.getRequestDispatcher("/views/workbook/workbookList.jsp").forward(request, response);
			}
		}
	}
	
	/*
	 * View Detail of Workbook include sheets 
	 */
	private static void detail(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
			logger.info("Activity : "+action);
			logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
			WorkbookType workbookType = getTableauService(request).invokeGetWorkbook(getCurrentCredentials(request), request.getParameter("workbookId").toString()) ;
			WorkbookTableau workbook = new WorkbookTableau();
			workbook.setWorkbookType(workbookType);
			workbook.setImage(getTableauService(request).invokeQueryWorkbookImage(getCurrentCredentials(request), workbookType.getId()));
			UserType user = getTableauService(request).invokeGetUser(getCurrentCredentials(request), workbookType.getOwner().getId());
			request.setAttribute("workbook", workbook);
			request.setAttribute("owner", user);
			request.setAttribute("views", getTableauService(request).invokeQueryViews(getCurrentCredentials(request), workbookType.getId(), Integer.parseInt(ReadConfig.get("tableau.views.max").trim()), 0).getView());
			request.setAttribute("createDate", workbookType.getCreatedAt().toString().replace("T", " ").replace("Z", " "));
			request.setAttribute("updateDate", workbookType.getUpdatedAt().toString().replace("T", " ").replace("Z", " "));
			
		}
	}
	
	/*
	 * View Dashboard 
	 */
	private static void viewDashboard(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
			logger.info("Activity : "+action);
			logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
			request.setAttribute("menuName", "<a href=\"workbook.cbi\">Workbook</a> <i class=\"fa fa-angle-double-right\"></i> "+request.getParameter("url").split("/")[1]+"");
			request.setAttribute("url", getDashboardURL(request, request.getParameter("url")));
			request.getRequestDispatcher("/views/workbook/viewDashboard.jsp").forward(request, response);
		}
	}
	
	/*
	 * Add Dashboard To Portal
	 */
	private static void addToPortal(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if(Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				Dashboard dashboard = new Dashboard();
				dashboard.setId(request.getParameter("viewId"));
				dashboard.setCreateBy(getCurrentUser(request).getUsername());
				dashboard.setCreateDate(new Timestamp(System.currentTimeMillis()));
				dashboard.setUrl(request.getParameter("url"));
				dashboard.setSiteId(getCurrentSite(request).getId());
				dashboard.setWorkbookId(request.getParameter("workbookId"));
				DashboardDAO.add(dashboard);
			}
		}
	}
	
	/*
	 * Remove Dashboard from Portal
	 */
	private static void removeFromPortal(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
		}
		else {
			if(Interceptor.isAuthorized(action, request)){
				logger.info("Current Date :"+new Timestamp(System.currentTimeMillis()) );
				logger.info("Activity : "+action);
				logger.info("Current user login :"+getCurrentUser(request).getUsername()+" "+getCurrentUser(request).getId());
				DashboardDAO.delete(request.getParameter("viewId"));
			}
		}
	}
}
