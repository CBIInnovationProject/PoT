<!DOCTYPE html>
<%@page import="com.cybertrend.pot.entity.Dashboard"%>
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
								<form method="post" class="form-horizontal form-label-left">

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Menu Name <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" name="name" id="name" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Action Name <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" name="action" id="action" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Parent Menu</label>
										<div class="col-sm-3">
											<select name="parentId" id="parentId" class="selectpicker">
												<option></option>
												<% List<Menu> parents = (List<Menu>)request.getAttribute("parentMenus"); 
												for(Menu parent:parents){%>
													<option data-icon="<%=parent.getIcon()%>" value="<%= parent.getId() %>" ><%=parent.getName() %></option>
												<% }%>
											</select>
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Dashboard URL</label>
										<div class="col-sm-3">
											<select name="dashboard" id="dashboard" class="selectpicker">
												<% List<Dashboard> dashboards=(List<Dashboard>) request.getAttribute("dashboards"); for (Dashboard dashboard: dashboards) { %>
													<option data-icon="fa fa-bar-chart" value="<%= dashboard.getId() %>"><%= dashboard.getUrl() %></option>
												<%}%>
											</select>
										</div>
									</div>
									<input type="hidden" name="contentType" id="contentType" value="tableau"/>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="menu-order">Menu Order 
										</label>
										<div class="col-sm-1">
											<input type="number" name="menuOrder" id="menuOrder" value="0" class="form-control col-md-3">
										</div>
									</div>
									<%@ include file="../fragments/icon-picker-list.jsp"%>
									
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6">
											<button class="btn btn-success submit-menu">Submit</button>
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
		$(".submit-menu").on('click',function(){
    		$.ajax({
    			type:'post',
    			url:'menuSave.cbi',
                cache: false,
                contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
                datatype:'json',
                data:{
                    name:$("#name").val(),
                    action:$("#action").val(),
                    parentId:$('#parentId :selected').val(),
                    dashboard:$('#dashboard :selected').val(),
                    menuOrder:$("#menuOrder").val(),
                    contentType:$("#contentType").val(),
                    icon:$(".ownicon1[style='display: inline-block;'] input[name='icon']").val(),
                    actionsave:1
                },
                success:function(data){
                	alert(data);
                },
                error: function(xhr, resp, text) {
                	alert("Error Bro");
                }
    		});
    	});
	});
	</script>
</body>
</html>