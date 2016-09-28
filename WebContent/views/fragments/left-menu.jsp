
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
				<h2>
					<b>${user.fullName}</b>&nbsp;&nbsp;
				</h2>
				<span>${user.role.name}</span> <p>Site :${user.siteUrl}</p>
			</div>
		</div>
		<!-- /menu profile quick info -->
		<div class="clearfix"></div>
		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
					<li><a href="landingPage.cbi"><i class="fa fa-home"></i>
							Home</a></li> ${treeMenu}
				</ul>
			</div>
			<!-- %@ include file="fragments/admin-menu.jsp" %-->
		</div>
		<!-- /sidebar menu -->
		<%@ include file="../fragments/footer-buttons.jsp"%>
	</div>
</div>