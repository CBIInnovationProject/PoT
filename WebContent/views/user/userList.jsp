<!DOCTYPE html>
<%@page import="com.cybertrend.cpot.util.ReadConfig"%>
<%@page import="com.cybertrend.cpot.Constants"%>
<%@page import="com.cybertrend.cpot.dao.RoleDAO"%>
<%@page import="com.cybertrend.cpot.entity.User"%>
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
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
							<!-- X-Content -->
							<div class="tambahan"></div>
		                    <table id="datatable" class="table table-striped table-bordered">
		                      <thead>
		                        <tr>
		                          <th>Username</th>
		                          <th>Create Date</th>
		                          <th>Last Updated</th>
		                        </tr>
		                      </thead>
		
		                      <tbody>
							 	<% List<User> users=(List<User>) request.getAttribute("users"); 
	                  			for (User user: users) { 
	                  			if(!user.getUsername().trim().equals(((User)session.getAttribute(Constants.USER_GA)).getUsername())&&
	                  					!user.getUsername().trim().equals(ReadConfig.get("tableau.admin.default"))) {%>
		                        <tr id="<%=user.getId()%>">
		                        	<td>
		                        	<ul style="list-style-type: none;padding: 0;margin:0">
		                          	<li class="dropdown"><a href="#<%= user.getUsername()%>" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" role="menu"><i class="fa fa-user"></i>&nbsp;&nbsp;<%= user.getUsername()%></a>
		                          		<ul class="dropdown-menu" role="menu">
					                          <li><a href="#">Edit</a></li>
					                          <li class="divider"></li>
					                          <li><a onclick="doDelete('<%=user.getId() %>','<%=user.getUsername() %>');" href="#"><i class="fa fa-trash"></i>&nbsp;&nbsp;Remove</a></li>
			                        	</ul>
			                        </li>
			                        </ul>
		                        	</td>
			                        <td><%= user.getCreateDate()!=null?user.getCreateDate():""%></td>
			                        <td><%= user.getUpdateDate()!=null?user.getUpdateDate():""%></td>
		                        </tr>
		                    	<% } }%>
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
      var table = $('#datatable').DataTable();
      function doDelete(userId, userName){ 
  	  	swal({   
	    	  	title: "Are you sure to delete user '"+userName+"'?",   
	    		text: "You will not be able to recover this data!",   
	    		type: "warning",   
	    		showCancelButton: true,   
	    		confirmButtonColor: "#DD6B55",   
	    		confirmButtonText: "Yes, delete it!",   
	    		cancelButtonText: "No, cancel plx!",   
	    		closeOnConfirm: true,   
	    		closeOnCancel: true }, 
	      	function(isConfirm){   
	    			if (isConfirm) {     
	  	    			var posting = $.post("userDelete.cbi", 
	  	  					{ 
	  	  						userId:userId
	  	  					} );
	  	  			
	  	      		posting.done(function(message) {
	  	      			var alert = "success";
	  	      			if(message.indexOf('ERROR')!==-1){
	  	      				alert = "danger";
	  	      			}
	  	                else{ 
	  	                	table = $('#datatable').DataTable();
	  	                	table.row("#"+userId).remove().draw();
	  	    			}
	  	                $(".tambahan").hide().html("<div class=\"alert alert-"+alert+" alert-dismissible \" role=\"alert\">"+
	   	                       "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>"+
	   	                       "</button> "+new Date().toUTCString()+" - "+message+"</div>").fadeIn('slow');
	  	      		});
	    		}
	     	});
   	}
    </script>
</body>
</html>