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
			<%@ include file="../fragments/left-menu.jsp"%>
			<%@ include file="../fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col">
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2>Workbook</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
							<!-- X-Content -->
							
							<table id="datatable" class="table table-striped table-bordered">
		                      <thead>
		                        <tr>
		                          <th>Name</th>
		                          <th>Create Date</th>
		                          <th>Last Updated</th>
		                        </tr>
		                      </thead>
		
		                      <tbody>
							 	<% List<WorkbookType> workbooks=(List<WorkbookType>) request.getAttribute("workbooks"); 
	                  			for (WorkbookType workbook: workbooks) { %>
		                        <tr>
		                          <td><a href="#" onclick="popup_detail('workbook.cbi?workbookId=<%=workbook.getId()%>')"><i class="fa fa-bar-chart"></i>&nbsp;&nbsp;<%= workbook.getName()%></a></td>
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
		
			<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModal" aria-hidden="true" id="myModal" >
			  <div class="modal-dialog modal-lg">
			    <div id="modal-content" ></div>
			  </div>
			</div>

		<%@ include file="../fragments/footer.jsp" %>
		</div>


<%@ include file="../fragments/js-collection.jsp" %>
    <!-- Datatables -->
    <script>
      $(document).ready(function() {
          
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

  	function popup_detail(url){

  		$('#myModal').on('show.bs.modal', function () {
  			$('#modal-content').html("<div style='text-align: center;background: #ffffff;background-position: center center;background-repeat: no-repeat;background-image: url(../images/loading.gif);'><p>&nbsp;</p><br/><br/><br/><p>&nbsp;&nbsp;Loading ...</p><p>&nbsp;</p></div>");
    			$('#modal-content').load(url);
  		})
  		$('#myModal').modal("show");
  	}
	$("#myModal").on('hidden.bs.modal', function () {
		$('.dropdown-toggle').dropdown();
	});
	</script>
</body>
</html>