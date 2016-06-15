<!DOCTYPE html>
<%@page import="tableau.api.rest.bindings.WorkbookType"%>
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
	                    <h2>Workbook</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
							<!-- X-Content -->
							<p class="text-muted font-13 m-b-30">
		                      DataTables has most features enabled by default, so all you need to do to use it with your own tables is to call the construction function: <code>$().DataTable();</code>
		                    </p>
		                    <table id="datatable" class="table table-striped table-bordered">
		                      <thead>
		                        <tr>
		                          <th>Name</th>
		                          <th>Project</th>
		                          <th>Create Date</th>
		                          <th>Last Updated</th>
		                        </tr>
		                      </thead>
		
		                      <tbody>
							 	<% List<WorkbookType> workbooks=(List<WorkbookType>) request.getAttribute("workbooks"); 
	                  			for (WorkbookType workbook: workbooks) { %>
		                        <tr>
		                          <td><a href="workbookDetail.cbi?workbookId=<%=workbook.getId()%>"><li class="fa fa-bar-chart"></li>&nbsp;&nbsp;<%= workbook.getName()%></a></td>
		                          <td><%= workbook.getProject().getName()%></td>
		                          <td><%= workbook.getCreatedAt().toString().replace("Z", " ").replace("T", " ")%></td>
		                          <td><%= workbook.getUpdatedAt().toString().replace("Z", " ").replace("T", " ")%></td>
		                        </tr>
		                    	<%} %>
		                      </tbody>
		                    </table>
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