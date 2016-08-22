<!DOCTYPE html>
<%@page import="com.cybertrend.pot.dao.DashboardDAO"%>
<%@page import="tableau.api.rest.bindings.ViewType"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CPoT - Cybertrend Portal of Tableau</title>
<%@ include file="../fragments/styles-collection.jsp" %>
</head>

<body class="nav-md"> 
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col menu_fixed">
				<div class="left_col scroll-view">
					
					<div class="clearfix"></div>
					<!-- menu profile quick info -->
					<div class="profile">
						<div class="profile_pic">
							<img src="${pageContext.request.contextPath}/images/user.png"
								alt="..." class="img-circle profile_img">
						</div>
						<div class="profile_info">
							<span>Welcome,</span>
							<h2>${user.fullName}&nbsp;&nbsp;</h2>
						</div>
					</div>
					<!-- /menu profile quick info -->

					<br />

					<!-- sidebar menu -->
					<div id="sidebar-menu"
						class="main_menu_side hidden-print main_menu">
						<div class="menu_section">
							<h3>&nbsp;</h3>
							<ul class="nav side-menu">${treeMenu}
							</ul>
						</div>
						<!-- %@ include file="fragments/admin-menu.jsp" %-->
					</div>
					<!-- /sidebar menu -->
					<%@ include file="../fragments/footer-buttons.jsp" %>
				</div>
			</div>

			<%@ include file="../fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col">
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2><a href="workbook.cbi">Workbook</a> <i class="fa fa-angle-double-right"></i> ${workbook.workbookType.name}</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
	                            
	                            <section class="content invoice">
			                      <!-- title row -->
			                      <div class="row">
			                        <div class="col-xs-12 invoice-header">
			                          <h1>
			                                          <i class="fa fa-bar-chart"></i> ${workbook.workbookType.name}
			                                      </h1>
			                        </div>
			                        <!-- /.col -->
			                      </div>
			                      <!-- info row -->
			                      <div class="row invoice-info">
			                        <div class="col-sm-4 invoice-col">
			                          <!-- Isi Content -->
			                          <br><b>Owner :</b> ${owner.name}
			                          <br><b>Project :</b> ${workbook.workbookType.project.name}
			                        </div>
			                        <!-- /.col -->
			                        <div class="col-sm-4 invoice-col">
			                          &nbsp;
			                          <address>
			                                          <strong>Content Url :</strong>
			                                          <br>${workbook.workbookType.contentUrl}
			                                          <br>
			                                          <br><b>Create Date :</b> ${createDate}
			                                          <br><b>Updated Date :</b> ${updateDate}
			                                      </address>
			                        </div>
			                        <!-- /.col -->
			                        <div class="col-sm-4 invoice-col">
			                          <img width="150" src="${workbook.image}" alt="image" />
			                        </div>
			                        <!-- /.col -->
			                      </div>
			                      <!-- /.row -->
									
			                      <!-- Table row -->
			                      <div id="contentTable" class="row">
			                        <div class="col-xs-12 table">
			                        <div class="ln_solid"></div>
									<h3>Views</h3>
			                          <table id="datatable" class="table table-striped table-bordered">
				                      <thead>
				                        <tr>
				                          <th>View Name</th>
				                          <th>Register as Menu</th>
				                        </tr>
				                      </thead>
				
				                      <tbody>
									 	<% List<ViewType> views=(List<ViewType>) request.getAttribute("views"); 
			                  			for (ViewType view: views) { %>
				                        <tr>
				                          <td><a href=workbook.cbi?workbookId=${workbook.workbookType.id}&&url=<%=view.getContentUrl().replace("sheets/", "") %> ><i class='fa fa-bar-chart'></i>&nbsp;&nbsp;<%= view.getName()%></a></td>
				                          <td id="dashboardAction">
				                          <%if(DashboardDAO.isDasboardExist(view.getId())==false) {%>
				                          	<a data-toggle="tooltip" data-placement="top" title="Register" class="btn btn-success btn-xs" onclick="addToMenu('${workbook.workbookType.id}','<%= view.getId()%>','<%= view.getContentUrl().replace("sheets/", "")%>',this)"><i class="fa fa-plus"></i></a>
				                          <% } else {%>
				                          	<a data-toggle="tooltip" data-placement="top" title="Unregister" class="btn btn-danger btn-xs" onclick="removeFromMenu('${workbook.workbookType.id}','<%= view.getId()%>','yes',this)"><i class="fa fa-minus"></i></a>
				                          <% } %>	
				                          </td>
				                        </tr>
				                    	<%} %>
				                      </tbody>
				                    </table>
			                        </div>
			                        <!-- /.col -->
			                      </div>
	                            </section>
	                            
	                            
	                            <!-- X-Content -->
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
			
			</div>
			<!-- /page content -->

		<%@ include file="../fragments/footer.jsp" %>
		</div>
	</div>

<%@ include file="../fragments/js-collection.jsp" %>
<!-- Datatables -->
    <script>
       
       function addToMenu(workbook,view,link,event){
		   $.ajax({
               type:'post',
			   url:"workbook.cbi",
               cache: false,
               contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
               data:{
            	   workbookId:workbook,
            	   viewId:view,
            	   url:link
                   
               },
               beforeSend: function() {
            	 $(function() {
           		   var $elie = $("i",event), degree = 0, timer;
           		   rotate();
           		function rotate() {
           			$elie.removeClass('fa-plus');
           			$elie.addClass('fa-spinner');
           		    $elie.css({ WebkitTransform: 'rotate(' + degree + 'deg)'});  
           		       $elie.css({ '-moz-transform': 'rotate(' + degree + 'deg)'});                      
           		        timer = setTimeout(function() {
           		            ++degree; rotate();
           		        },2);
           		    }
           		
               });},
               success:function(data){
        		   $('#contentTable').load("workbook.cbi?workbookId="+workbook+" #contentTable")

               },
               error: function(xhr, resp, text) {
            	   alert(xhr.status);
               }
		   });
       }
       
       function removeFromMenu(workbook,view,remove,event){
		   $.ajax({
               type:'post',
			   url:"workbook.cbi",
               cache: false,
               contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
               data:{
            	   workbookId:workbook,
            	   viewId:view,
            	   deleteAction:remove
                   
               },
               beforeSend: function() {
            	 $(function() {
           		   var $elie = $("i",event), degree = 0, timer;
           		   rotate();
           		function rotate() {
           			$elie.removeClass('fa-minus');
           			$elie.addClass('fa-spinner');
           		    $elie.css({ WebkitTransform: 'rotate(' + degree + 'deg)'});  
           		       $elie.css({ '-moz-transform': 'rotate(' + degree + 'deg)'});                      
           		        timer = setTimeout(function() {
           		            ++degree; rotate();
           		        },2);
           		    }
           		
               });},
               success:function(data){
            		   $('#contentTable').load("workbook.cbi?workbookId="+workbook+" #contentTable")
               },
               error: function(xhr, resp, text) {
            	   alert(xhr.status);
               }
		   });
       }
   </script>
</body>
</html>