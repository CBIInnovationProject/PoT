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
<%@ include file="fragments/styles-collection.jsp" %>
</head>

<body class="nav-md"> 
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
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
					<%@ include file="fragments/footer-buttons.jsp" %>
				</div>
			</div>

			<!-- top navigation -->
			<div class="top_nav">

				<div class="nav_menu">
					<nav class="" role="navigation">
						<div class="nav toggle">
							<a id="menu_toggle"><i class="fa fa-bars"></i></a>
						</div>
						<a href=#><img alt="" src="${pageContext.request.contextPath}/images/logo.png"></a>
						<ul class="nav navbar-nav navbar-right">
							<li class=""><a href="javascript:;"
								class="user-profile dropdown-toggle" data-toggle="dropdown"
								aria-expanded="false"> <img
									src="${pageContext.request.contextPath}/images/user.png" alt="">${user.username}&nbsp;&nbsp;<span
									class=" fa fa-angle-down"></span>
							</a>
								<ul class="dropdown-menu dropdown-usermenu pull-right">
									<li><a href="javascript:;"> Profile</a></li>
									<li><a href="javascript:;"> <span
											class="badge bg-red pull-right">50%</span> <span>Settings</span>
									</a></li>
									<li><a href="javascript:;">Help</a></li>
									<li><a href="logout.cbi"><i
											class="fa fa-sign-out pull-right"></i> Log Out</a></li>
								</ul></li>

						</ul>
					</nav>
				</div>

			</div>
			<!-- /top navigation -->

			<!-- page content -->
			<div class="right_col">
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2><a href="workbookList.cbi">Workbook</a> <i class="fa fa-angle-double-right"></i> ${workbook.workbookType.name}</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
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
			                      <div class="row">
			                        <div class="col-xs-12 table">
			                        <div class="ln_solid"></div>

			                          <table id="datatable" class="table table-striped table-bordered">
				                      <thead>
				                        <tr>
				                          <th>Sheets</th>
				                          <th>Action</th>
				                        </tr>
				                      </thead>
				
				                      <tbody>
									 	<% List<ViewType> views=(List<ViewType>) request.getAttribute("views"); 
			                  			for (ViewType view: views) { %>
				                        <tr>
				                          <td><a href=viewSheet.cbi?workbookId=${workbook.workbookType.id}&&url=<%=view.getContentUrl().replace("sheets/", "") %> ><i class='fa fa-bar-chart'></i>&nbsp;&nbsp;<%= view.getName()%></a></td>
				                          <td>
				                          <%if(DashboardDAO.isDasboardExist(view.getId())==false) {%>
				                          	<a class="btn btn-success btn-xs" href="workbookDetail.cbi?workbookId=${workbook.workbookType.id}&&viewId=<%= view.getId()%>&&url=<%= view.getContentUrl().replace("sheets/", "")%>"><li class="fa fa-plus"></li></a>
				                          <% } else {%>
				                          	<a class="btn btn-danger btn-xs" href="workbookDetail.cbi?workbookId=${workbook.workbookType.id}&&viewId=<%= view.getId()%>&&deleteAction=yes"><li class="fa fa-minus"></li></a>
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

		<%@ include file="fragments/footer.jsp" %>
		</div>
	</div>

<%@ include file="fragments/js-collection.jsp" %>
<!-- Datatables -->
    <script>
      $(document).ready(function() {

          var handleDataTableButtons = function() {
            if ($("#datatable-buttons").length) {
              $("#datatable-buttons").DataTable({
                dom: "Bfrtip",
                buttons: [
                  {
                    extend: "copy",
                    className: "btn-sm"
                  },
                  {
                    extend: "csv",
                    className: "btn-sm"
                  },
                  {
                    extend: "excel",
                    className: "btn-sm"
                  },
                  {
                    extend: "pdfHtml5",
                    className: "btn-sm"
                  },
                  {
                    extend: "print",
                    className: "btn-sm"
                  },
                ],
                responsive: true
              });
            }
          };
          
        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();
        $('#datatable-keytable').DataTable({
          keys: true
        });

        $('#datatable-responsive').DataTable();

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        var table = $('#datatable-fixed-header').DataTable({
          fixedHeader: true
        });

        TableManageButtons.init();
      });
    </script>
</body>
</html>