package com.cybertrend.cpot.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cybertrend.cpot.Constants;
import com.cybertrend.cpot.Interceptor;
import com.cybertrend.cpot.dao.DashboardDAO;
import com.cybertrend.cpot.entity.Dashboard;
import com.cybertrend.cpot.entity.WorkbookTableau;
import com.cybertrend.cpot.service.TableauService;
import com.cybertrend.cpot.util.ReadConfig;

import tableau.api.rest.bindings.ProjectType;
import tableau.api.rest.bindings.UserType;
import tableau.api.rest.bindings.WorkbookType;

public class WorkbookForm extends DefaultAction{
	public static void execute(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			request.setAttribute("workbooks", getTableauService().invokeQueryWorkbooks(getCurrentCredentials(request), Integer.parseInt(ReadConfig.get("tableau.workbooks.max").trim()), 0).getWorkbooks().getWorkbook());
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
				request.getRequestDispatcher("/views/workbook/workbookList.jsp").forward(request, response);
			}
		}
	}
	
	/*
	 * View Detail of Workbook include sheets 
	 */
	private static void detail(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			WorkbookType workbookType = getTableauService().invokeGetWorkbook(getCurrentCredentials(request), request.getParameter("workbookId").toString()) ;
			WorkbookTableau workbook = new WorkbookTableau();
			workbook.setWorkbookType(workbookType);
			workbook.setImage(getTableauService().invokeQueryWorkbookImage(getCurrentCredentials(request), workbookType.getId()));
			UserType user = getTableauService().invokeGetUser(getCurrentCredentials(request), workbookType.getOwner().getId());
			request.setAttribute("workbook", workbook);
			request.setAttribute("owner", user);
			request.setAttribute("views", getTableauService().invokeQueryViews(getCurrentCredentials(request), workbookType.getId(), Integer.parseInt(ReadConfig.get("tableau.views.max").trim()), 0).getView());
			request.setAttribute("createDate", workbookType.getCreatedAt().toString().replace("T", " ").replace("Z", " "));
			request.setAttribute("updateDate", workbookType.getUpdatedAt().toString().replace("T", " ").replace("Z", " "));
			
		}
	}
	
	/*
	 * View Dashboard 
	 */
	private static void viewDashboard(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			request.setAttribute("menuName", "<a href=\"workbook.cbi\">Workbook</a> <i class=\"fa fa-angle-double-right\"></i> "+request.getParameter("url").split("/")[1]+"");
			request.setAttribute("url", request.getParameter("url"));
			request.getRequestDispatcher("/views/workbook/viewDashboard.jsp").forward(request, response);
		}
	}
	
	/*
	 * Add Dashboard To Portal
	 */
	private static void addToPortal(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if(Interceptor.isAuthorized(action, request)){
				Dashboard dashboard = new Dashboard();
				dashboard.setId(request.getParameter("viewId"));
				dashboard.setCreateBy(getCurrentUser(request).getUsername());
				dashboard.setCreateDate(new Timestamp(System.currentTimeMillis()));
				dashboard.setUrl(request.getParameter("url"));
				dashboard.setSiteId(getCurrentCredentials(request).getSite().getId());
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
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if(Interceptor.isAuthorized(action, request)){
				DashboardDAO.delete(request.getParameter("viewId"));
			}
		}
	}
	
	/*
	 * publish Workbook
	 */
	public static void publishWorkbook(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		String SAVE_DIR = "uploadWorkbook";
		String appPath = request.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		String project = request.getParameter("project");
		String workbookName = request.getParameter("workbookName");
		
		// creates the save directory if it does not exists
		File workbookFile = new File(savePath);
		if (!workbookFile.exists()) {
			workbookFile.mkdir();
		}
	
		for (Part part : request.getParts()) {
			String fileName = "tableauwb"+System.currentTimeMillis()+ ".twbx";
			part.write(savePath + File.separator + fileName);
		}
		
		boolean chunkedPublish = true;

        // Publishes the workbook as a multipart request
        WorkbookType publishedWorkbook = TableauService.invokePublishWorkbook(getCurrentCredentials(request), getCurrentCredentials(request).getSite().getId(),
                project, workbookName, workbookFile, chunkedPublish);
	}
	
	/*
	 * publish Workbook Form
	 */
	public static void publishWorkbookForm(HttpServletRequest request, HttpServletResponse response, String action)throws ServletException, IOException, SQLException {
		if(Interceptor.isLogin(request)==false){
			request.getRequestDispatcher("/views/loginForm.jsp").forward(request, response);
		}
		else {
			if(Interceptor.isAuthorized(action, request)){
				List<ProjectType> projects = TableauService.invokeQueryProjects(getCurrentCredentials(request), getCurrentCredentials(request).getSite().getId()).getProject();
				request.setAttribute("projects", projects);
			}
		}
	}
}
