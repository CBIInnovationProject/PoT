
<!-- top navigation -->
<div class="top_nav">

	<div class="nav_menu">
		<nav class="" role="navigation">
			<div class="nav toggle">
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>
			<a href="landingPage.cbi"><img alt=""
				width="150px" src="${pageContext.request.contextPath}/images/logo.png"></a>
			<ul class="nav navbar-nav navbar-right">
				<li class=""><a href="javascript:;"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <i class="fa fa-user"></i>&nbsp;&nbsp;${user.username}&nbsp;&nbsp;<span
						class=" fa fa-angle-down"></span>
				</a>
					<ul class="dropdown-menu dropdown-usermenu pull-right">
						<li><a href="javascript:;"> Profile</a></li>
						<li><a href="settings.cbi"><span>Settings</span></a></li>
						<li><a href="javascript:;">Help</a></li>
						<li><a href="logout.cbi"><i
								class="fa fa-sign-out pull-right"></i> Log Out</a></li>
					</ul></li>

			</ul>
		</nav>
	</div>

</div>
<!-- /top navigation -->