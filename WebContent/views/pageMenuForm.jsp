<!DOCTYPE html>
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
<%@ include file="fragments/styles-collection.jsp"%>
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
					<%@ include file="fragments/footer-buttons.jsp"%>
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
								<form action="menuSave.cbi" data-parsley-validate
									class="form-horizontal form-label-left">

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Menu Name <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" name="name" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Action Name <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" name="action" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Parent Menu</label>
										<div class="col-sm-3">
											<select name="parentId" class="selectpicker">
												<option></option>
												<% List<Menu> menus=(List<Menu>) request.getAttribute("parentMenu"); for (Menu parent: menus) { %>
													<option data-icon="<%= parent.getIcon()%>" value="<%= parent.getId() %>"><%= parent.getName() %></option>
												<%}%>
											</select>
										</div>
									</div>
									<input type="hidden" name="contentType" value="page"/>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="menu-order">Menu Order 
										</label>
										<div class="col-sm-1">
											<input type="number" name="menuOrder" value="0" class="form-control col-md-3">
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12" >Icon
										</label>
										<div class="col-sm-2">
											<button name="icon" class="btn btn-default" data-iconset="fontawesome" role="iconpicker"></button>
										</div>
									</div>
									
									
									<div class="ln_solid"></div>
									<textarea name="content" style="width: 100%;height: 150px;"></textarea>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6">
											<button type="submit" class="btn btn-success">Submit</button>
							
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

		<%@ include file="fragments/footer.jsp"%>
	</div>

	<%@ include file="fragments/js-collection.jsp"%>
</body>
</html>