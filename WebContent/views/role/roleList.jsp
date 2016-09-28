<!DOCTYPE html>
<%@page import="com.cybertrend.cpot.entity.Role"%>
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
	                    <h2>${menu.name}</h2>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
							<!-- X-Content -->
		                    <table id="datatable" class="table table-striped table-bordered">
		                      <thead>
		                        <tr>
		                          <th>Role Name</th>
		                          <th>Create Date</th>
		                          <th>Last Updated</th>
		                        </tr>
		                      </thead>
		
		                      <tbody>
							 	<% List<Role> roles=(List<Role>) request.getAttribute("roles"); 
	                  			for (Role role: roles) { %>
		                        <tr>
		                          <td><ul style="list-style-type: none;padding: 0;margin:0">
		                          	<li class="dropdown"><a href="#<%= role.getName()%>" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" role="menu"><i class="fa fa-user-secret"></i>&nbsp;&nbsp;<%= role.getName()%></a>
		                          		<ul class="dropdown-menu" role="menu">
					                          <li role="presentation"><a href="#">Edit</a></li>
					                          <li role="presentation"><a href="#" onclick="popup_detail('detailRole.cbi?roleId=<%=role.getId()%>')">Set Privilege Menu</a></li>
					                          <li class="divider"></li>
					                          <li role="presentation"><a href="#">Remove</a></li>
			                        	</ul>
			                        </li>
			                        </ul>
                        			</td>
		                          <td><%= role.getCreateDate()!=null?role.getCreateDate():""%></td>
		                          <td><%= role.getUpdateDate()!=null?role.getUpdateDate():""%></td>
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

      		$('#myModal').modal();
    	}
		$("#myModal").on('hidden.bs.modal', function () {
			$('.dropdown-toggle').dropdown();
    	});
    </script>
</body>
</html>