<!DOCTYPE html>
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
					<%@ include file="fragments/footer-buttons.jsp" %>
				</div>
			</div>

			<%@ include file="fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col">
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2>${menu.name}</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li class="dropdown">
	                        
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                    <font size="1.5px">${menu.createDate} <b>by</b> ${menu.createBy}</font>
	                  </div>
	                  <div class="x_content">
							${menu.content}
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
</body>
</html>