
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/bootstrap/css/bootstrap.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/font-awesome-4.6.3/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/bootstrap-colorpicker-master/dist/css/bootstrap-colorpicker.min.css">
<script
	src="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/bootstrap-colorpicker-master/dist/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/bootstrap-filestyle/src/bootstrap-filestyle.min.js">
	
</script>

<style>
.marginBottom-0 {
	margin-bottom: 0;
}

.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -9px;
	margin-left: -1px;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #cccccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #555;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	border-radius: 0px;
}

ul.dropdown-menu {
	border-color: transparent;
	background-color: #ffffff;
}

.dropdown-menu li {
	padding: 5px;
}

/* NAVBAR COLOR STYLE*/
.navbar-default {
	background-color: #f8f8f8;
	border-color: #e7e7e7;
}

.navbar-default .navbar-brand {
	color: #777;
}

.navbar-default .navbar-brand:hover, .navbar-default .navbar-brand:focus
	{
	color: #5e5e5e;
	background-color: transparent;
}

.navbar-default .navbar-text {
	color: #777;
}

.navbar-default .navbar-nav>li>a {
	color: #777;
}

.navbar-default .navbar-nav>li>a:hover, .navbar-default .navbar-nav>li>a:focus
	{
	color: #333;
	background-color: transparent;
}

.navbar-default .navbar-nav>.active>a, .navbar-default .navbar-nav>.active>a:hover,
	.navbar-default .navbar-nav>.active>a:focus {
	color: #555;
	background-color: #e7e7e7;
}

.navbar-default .navbar-nav>.disabled>a, .navbar-default .navbar-nav>.disabled>a:hover,
	.navbar-default .navbar-nav>.disabled>a:focus {
	color: #ccc;
	background-color: transparent;
}

.navbar-default .navbar-toggle {
	border-color: #ddd;
}

.navbar-default .navbar-toggle:hover, .navbar-default .navbar-toggle:focus
	{
	background-color: #ddd;
}

.navbar-default .navbar-toggle .icon-bar {
	background-color: #888;
}

.navbar-default .navbar-collapse, .navbar-default .navbar-form {
	border-color: #e7e7e7;
}

.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:hover,
	.navbar-default .navbar-nav>.open>a:focus {
	color: #555;
	background-color: #e7e7e7;
}

@media ( max-width : 767px) {
	.navbar-default .navbar-nav .open .dropdown-menu>li>a {
		color: #777;
	}
	.navbar-default .navbar-nav .open .dropdown-menu>li>a:hover,
		.navbar-default .navbar-nav .open .dropdown-menu>li>a:focus {
		color: #333;
		background-color: transparent;
	}
	.navbar-default .navbar-nav .open .dropdown-menu>.active>a,
		.navbar-default .navbar-nav .open .dropdown-menu>.active>a:hover,
		.navbar-default .navbar-nav .open .dropdown-menu>.active>a:focus {
		color: #555;
		background-color: #e7e7e7;
	}
	.navbar-default .navbar-nav .open .dropdown-menu>.disabled>a,
		.navbar-default .navbar-nav .open .dropdown-menu>.disabled>a:hover,
		.navbar-default .navbar-nav .open .dropdown-menu>.disabled>a:focus {
		color: #ccc;
		background-color: transparent;
	}
}

.navbar-default .navbar-link {
	color: #777;
}

.navbar-default .navbar-link:hover {
	color: #333;
}

.navbar-default .btn-link {
	color: #777;
}

.navbar-default .btn-link:hover, .navbar-default .btn-link:focus {
	color: #333;
}

.navbar-default .btn-link[disabled]:hover, fieldset[disabled] .navbar-default .btn-link:hover,
	.navbar-default .btn-link[disabled]:focus, fieldset[disabled] .navbar-default .btn-link:focus
	{
	color: #ccc;
}

.navbar-inverse .navbar-brand {
	color: #9d9d9d;
}

.navbar-inverse .navbar-brand:hover, .navbar-inverse .navbar-brand:focus
	{
	color: #fff;
	background-color: transparent;
}

.navbar-inverse .navbar-text {
	color: #9d9d9d;
}

.navbar-inverse .navbar-nav>li>a:hover, .navbar-inverse .navbar-nav>li>a:focus
	{
	color: red;
	background-color: transparent;
}

.navbar-inverse .navbar-nav>.active>a, .navbar-inverse .navbar-nav>.active>a:hover,
	.navbar-inverse .navbar-nav>.active>a:focus {
	color: #fff;
	background-color: #080808;
}

.navbar-inverse .navbar-nav>.disabled>a, .navbar-inverse .navbar-nav>.disabled>a:hover,
	.navbar-inverse .navbar-nav>.disabled>a:focus {
	color: #444;
	background-color: transparent;
}

.navbar-inverse .navbar-toggle {
	border-color: #333;
}

.navbar-inverse .navbar-toggle:hover, .navbar-inverse .navbar-toggle:focus
	{
	background-color: #333;
}

.navbar-inverse .navbar-toggle .icon-bar {
	background-color: #fff;
}

.navbar-inverse .navbar-collapse, .navbar-inverse .navbar-form {
	border-color: #101010;
}

.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:hover,
	.navbar-inverse .navbar-nav>.open>a:focus {
	color: #000000;
	background-color: #cccccc;
}

@media ( max-width : 767px) {
	.navbar-inverse .navbar-nav .open .dropdown-menu>.dropdown-header {
		border-color: #080808;
	}
	.navbar-inverse .navbar-nav .open .dropdown-menu .divider {
		background-color: #080808;
	}
	.navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
		color: #9d9d9d;
	}
	.navbar-inverse .navbar-nav .open .dropdown-menu>li>a:hover,
		.navbar-inverse .navbar-nav .open .dropdown-menu>li>a:focus {
		color: #fff;
		background-color: transparent;
	}
	.navbar-inverse .navbar-nav .open .dropdown-menu>.active>a,
		.navbar-inverse .navbar-nav .open .dropdown-menu>.active>a:hover,
		.navbar-inverse .navbar-nav .open .dropdown-menu>.active>a:focus {
		color: #fff;
		background-color: #080808;
	}
	.navbar-inverse .navbar-nav .open .dropdown-menu>.disabled>a,
		.navbar-inverse .navbar-nav .open .dropdown-menu>.disabled>a:hover,
		.navbar-inverse .navbar-nav .open .dropdown-menu>.disabled>a:focus {
		color: #444;
		background-color: transparent;
	}
}

.navbar-inverse .navbar-link {
	color: #9d9d9d;
}

.navbar-inverse .navbar-link:hover {
	color: #fff;
}

.navbar-inverse .btn-link {
	color: #9d9d9d;
}

.navbar-inverse .btn-link:hover, .navbar-inverse .btn-link:focus {
	color: #fff;
}

.navbar-inverse .btn-link[disabled]:hover, fieldset[disabled] .navbar-inverse .btn-link:hover,
	.navbar-inverse .btn-link[disabled]:focus, fieldset[disabled] .navbar-inverse .btn-link:focus
	{
	color: #444;
}

//
.dropdown:hover>ul { //
	display: block; //
	margin-top: 0;
	//
}

.subtitle {
	margin: 0 0 2em 0;
}

.fancy {
	line-height: 0.5;
	text-align: center;
}

.fancy span {
	display: inline-block;
	position: relative;
}

.fancy span:before, .fancy span:after {
	content: "";
	position: absolute;
	height: 5px;
	border-bottom: 1px solid black;
	border-top: 1px solid black;
	top: 0;
	width: 600px;
}

.fancy span:before {
	right: 100%;
	margin-right: 15px;
}

.fancy span:after {
	left: 100%;
	margin-left: 15px;
}

.navbar-login {
	width: 305px;
	padding: 10px;
	padding-bottom: 0px;
}

.navbar-login-session {
	padding: 10px;
	padding-bottom: 0px;
	padding-top: 0px;
}

.icon-size {
	font-size: 87px;
}
/*END OF NAVBAR COLOR STYLE*/

/*CHENGE STYLE NEED*/
.navbar-inverse {
	background-color: #000;
}

.navbar-inverse .navbar-nav>li>a {
	color: #41dbeb;
	letter-spacing: 4px;
	text-transform: uppercase;
	font-size: 12px;
}

.nav li a {
	font-family: 'Segoe UI', Arial, sans-serif;
	font-size: 14px;
}

.navbar-brand>img {
	display: block;
	height: 100%;
}

.navbar-inverse .navbar-nav>li:hover, .navbar-inverse .navbar-nav>li:focus
	{
	color: red;
}

.navbar {
	margin-bottom: 0;
}

body {
	background-color: #cccccc;
}

.view_name {
	font-family: sans-serif, 'Open Sans';
	font-size: 20px;
	font-weight: 700;
}

.navbar-nav>li>a {
	line-height: 60px;
	height: 60px;
	padding-top: 0;
}

#login-dp {
	min-width: 500px;
	padding: 14px 14px 0;
	overflow: hidden;
	background-color: #ffffff;
}

.navbar-brand {
	margin-left: 20px;
	margin-right: 20px;
}
</style>
<script type="text/javascript">
	$(function() {
		$('#cp2').colorpicker().on('changeColor', function(e) {
			$('body')[0].style.backgroundColor = e.color.toHex();
		});
	});
	$(function() {
		$('#cp3').colorpicker().on('changeColor', function(e) {
			$('.navbar-inverse')[0].style.backgroundColor = e.color.toHex();
		});
	});
	$(function() {
		$('#cp4').colorpicker().on('changeColor', function(e) {
			$('.view_name')[0].style.color = e.color.toHex();
		});
	});
	$(function() {
		$('#cp6').colorpicker().on(
				'changeColor',
				function(e) {
					$('.navbar-inverse .navbar-nav > li > a').css("color",
							e.color.toHex());

				});
	});
	$(function() {
		$('#cp7').colorpicker().on(
				'changeColor',
				function(e) {
					var prev_color = $(".navbar-inverse .navbar-nav > li > a")
							.css("color");
					$(".navbar-inverse .navbar-nav > li > a").hover(function() {
						$(this).css("color", e.color.toHex());
					})
				});
	});
	(function($) {
		$(document).ready(
				function() {

					$(":file").filestyle('buttonText', 'Choose Image');
					$('ul.dropdown-menu [data-toggle=dropdown]')
							.on(
									'click',
									function(event) {
										event.preventDefault();
										event.stopPropagation();
										$(this).parent().siblings()
												.removeClass('open');
										$(this).parent().toggleClass('open');
									});

					$("#edit_appr").on("click", function() {
						$("#edit_appr").hide();
						$("#save_appr").show();
						$('.form-control').prop("disabled", false);
					})
					$("#save_appr").on("click", function() {
						$("#save_appr").hide();
						$("#edit_appr").show();
						$('.form-control').prop("disabled", true);
					})

					$(".edit_appr").on("click", function() {
						$("#save_appr").hide();
						$("#edit_appr").show();
						$('.form-control').prop("disabled", true);
					})
				});
	})(jQuery);
	function remove_class() {
		var tes = $('.navbar-brand img').attr('src');

		if (tes == '${pageContext.request.contextPath}/themes/Bootstrap_Navbar/logo.png') {
			$(".navbar-brand img")
					.attr(
							"src",
							"https://upload.wikimedia.org/wikipedia/commons/thumb/c/c9/Intel-logo.svg/2000px-Intel-logo.svg.png");
		} else {
			$(".navbar-brand img").attr("src", "logo.png");
		}
	}
</script>
</head>
<body style="overflow-y: hidden">
	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath}">
				<img
				src="${pageContext.request.contextPath}/themes/Bootstrap_Navbar/logo.png"
				alt="CPoT">
			</a>
		</div>

		<div class="navbar-collapse collapse" id='navbar-collapse-1'>
			<ul class="nav navbar-nav navbar-left">
			${treeMenu }
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
						<strong>${user.username }</strong> <span
						class="glyphicon glyphicon-chevron-down"></span>
				</a>
					<ul class="dropdown-menu">
						<li>
							<div class="navbar-login">
								<div class="row">
									<div class="col-lg-4">
										<p class="text-center">
											<span class="glyphicon glyphicon-user icon-size"></span>
										</p>
									</div>
									<div class="col-lg-8">
										<p class="text-left">
											<strong>${user.username }</strong>
										</p>
										<p class="text-left small">${user.email }</p>
										<p class="text-left">
											<a href="#" class="btn btn-primary btn-block btn-sm">Profile</a>
										</p>
									</div>
								</div>
							</div>
						</li>
						<li class="divider navbar-login-session-bg"></li>
						<li><a href="#">Account Settings <span
								class="glyphicon glyphicon-cog pull-right"></span></a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#apprearance_mod">Appearance<span
								class="glyphicon glyphicon-heart pull-right"></span></a></li>
						<li class="divider"></li>
						<li><a href="logout.cbi">Sign Out <span
								class="glyphicon glyphicon-log-out pull-right"></span></a></li>
					</ul></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->

	</nav>

	<div style="border-style: none; width: 100%; height: 90%;">
		<iframe name="mainframe" src="" frameborder="0" border="0"
			cellspacing="0"
			style="border-style: none; width: 100%; height: 100%;"></iframe>

	</div>
	<div id="apprearance_mod" class="modal fade" tabindex="-1"
		role="dialog" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">Edit Appreance</h4>
				</div>
				<div class="modal-body">
					<form id="login-nav" class="form" role="form">
						<div class="form-group">
							<label for="cp2">Body Color</label>
							<div id="cp2" class="input-group colorpicker-component">
								<input type="text" value="#00AABB" class="form-control" disabled />
								<span class="input-group-addon"><i></i></span>
							</div>
						</div>
						<div class="form-group">
							<label for="cp3">Navigation Background Color</label>
							<div id="cp3" class="input-group colorpicker-component">
								<input type="text" value="#00AABB" class="form-control" disabled />
								<span class="input-group-addon"><i></i></span>
							</div>
						</div>
						<div class="form-group">
							<label for="cp6">Navigation Color</label>
							<div id="cp6" class="input-group colorpicker-component">
								<input type="text" value="#00AABB" class="form-control" disabled />
								<span class="input-group-addon"><i></i></span>
							</div>
						</div>
						<div class="form-group">
							<label for="cp7">Navigation Hover Color</label>
							<div id="cp7" class="input-group colorpicker-component">
								<input type="text" value="#00AABB" class="form-control" disabled />
								<span class="input-group-addon"><i></i></span>
							</div>
						</div>
						<div class="form-group">
							<label for="cp4">Header Color</label>
							<div id="cp4" class="input-group colorpicker-component">
								<input type="text" value="#00AABB" class="form-control" disabled />
								<span class="input-group-addon"><i></i></span>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-warning" id="edit_appr">Edit</button>
					<button type="button" class="btn btn-primary"
						style="display: none;" id="save_appr">Save message</button>
				</div>
			</div>
		</div>
	</div>




	<div id="login_mod" class="modal fade" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">Form Login</h4>
				</div>
				<div class="modal-body">
					<form id="login-nav" class="form" role="form">

						<div class="form-group">
							<label for="user">Username</label> <input type="text" value=""
								class="form-control" />

						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								value="" class="form-control" />
						</div>

					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal">login</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>