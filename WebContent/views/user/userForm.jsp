<!DOCTYPE html>
<%@page import="com.cybertrend.pot.entity.UserTableau"%>
<%@page import="com.cybertrend.pot.entity.Role"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cybertrend.pot.entity.Menu"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CPoT - Cybertrend Portal of Tableau</title>
<%@ include file="../fragments/styles-collection.jsp"%>
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
					<%@ include file="../fragments/footer-buttons.jsp"%>
				</div>
			</div>

			<%@ include file="../fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col" role="main">

				<div class="row">

					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>${menu.name}</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
									<li class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-expanded="false"><i
											class="fa fa-wrench"></i></a>
										<ul class="dropdown-menu" role="menu">
											<li><a href="#">Settings 1</a></li>
											<li><a href="#">Settings 2</a></li>
										</ul></li>
									<li><a class="close-link"><i class="fa fa-close"></i></a>
									</li>
								</ul>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<!-- Fill with Content -->
								<div class="tambahan"></div>
								<form id="formid" method="post" action="userSave.cbi" class="form-horizontal form-label-left">

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Username <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" id="username" name="username" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Password <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="password" id="password" name="password" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">User Tableau<span class="required">*</span>
										</label>
										<div class="col-sm-3">
										<select name="userTableau" id="userTableau" required class="selectpicker">
											<option></option>
											<% List<UserTableau> userTableaus=(List<UserTableau>) request.getAttribute("userTableaus"); 
				                  			for (UserTableau userTableau: userTableaus) { %>
												<option data-icon="fa fa-user-secret" value="<%= userTableau.getId() %>" ><%=userTableau.getUsername() %></option>
											<%}%>
										</select>	
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Role <span class="required">*</span>
										</label>
										<div class="col-sm-3">
										<select name="role" id="role" required class="selectpicker">
											<option></option>
											<% List<Role> roles=(List<Role>) request.getAttribute("roles"); 
				                  			for (Role role: roles) { %>
												<option data-icon="fa fa-user-secret" value="<%= role.getId() %>" ><%=role.getName() %></option>
											<%} %>
										</select>	
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Full Name</label>
										<div class="col-sm-6">
											<input type="text" id="fullName" name="fullName" class="form-control col-md-7 col-xs-12">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Address 1</label>
										<div class="col-sm-6">
											<input type="text" id="address1" name="address1" class="form-control col-md-7 col-xs-12">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Address 2</label>
										<div class="col-sm-6">
											<input type="text" id="address2" name="address2" class="form-control col-md-7 col-xs-12">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Address 3</label>
										<div class="col-sm-6">
											<input type="text" id="address3" name="address3" class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Zip</label>
										<div class="col-sm-3">
											<input type="text" id="zip" name="zip" class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Email</label>
										<div class="col-sm-3">
											<input type="email" id="email" name="email" class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Phone</label>
										<div class="col-sm-3">
											<input type="phone" id="phone" name="phone" class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6">
											 <input  class="btn btn-success submit-menu" type="submit" name="submitButton" value="Submit">
										</div>
									</div>

								</form>
								<!-- /Fill with Content -->
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<!-- /page content -->

		<%@ include file="../fragments/footer.jsp"%>
	</div>

	<%@ include file="../fragments/js-collection.jsp"%>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#formid").submit(function(event){
			event.preventDefault();
			var $form = $( this ),
	          url = $form.attr( 'action' );
			var posting = $.post(url, 
					{ 
						username:$("#username").val(),
						password:$("#password").val(),
		                role:$('#role :selected').val(),
		                userTableau:$('#userTableau :selected').val(),
						fullName:$("#fullName").val(),
						address1:$("#address1").val(),
						address2:$("#address2").val(),
						address3:$("#address3").val(),
						email:$("#email").val(),
						phone:$("#phone").val(),
						zip:$("#zip").val(),
		                actionsave:1
					} );
			
    		posting.done(function(data) {
    			$(".tambahan").append("<div class=\"alert alert-success alert-dismissible fade in\" role=\"alert\">"+
                        "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">�</span>"+
                        "</button> "+new Date().toUTCString()+" - User <strong>"+$("#username").val()+"</strong> was successfully added to record"+
                      "</div>");
                document.getElementById("formid").reset();
    		});
		});
	});
	</script>
</body>
</html>