<!DOCTYPE html>
<%@page import="com.cybertrend.cpot.entity.User"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cybertrend Portal of Tableau</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/css/custom.css" rel="stylesheet">
</head>

<body style="background: #F7F7F7;">
	<div class="">
		<a class="hiddenanchor" id="toregister"></a> <a class="hiddenanchor"
			id="tologin"></a>

		<div id="wrapper">
			<div id="login" class=" form">
				<section class="login_content">
					<form action="login.cbi" method="post">
						<h1><img alt=""
				src="${pageContext.request.contextPath}/images/logo.png"></h1>
						<div> <h3>Hi, ${username}</b></h3>Select Site : 
							<select name="userId" id="userId" class="selectpicker" required>
								<% List<User> users=(List<User>) request.getAttribute("users"); 
								for (User user: users) { %>
									<option data-icon="fa fa-bar-chart" value="<%= user.getId() %>"><%=(user.getUserTableau().getSiteContentUrl()==null||"".equalsIgnoreCase(user.getUserTableau().getSiteContentUrl().trim()))?"Default": user.getUserTableau().getSiteContentUrl()%></option>
								<%}%>
							</select>
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<div class="clearfix"></div>
							<div>
								<input class="btn btn-default submit" name="submit" type="submit" value="Go To Site"/> <h3><a href="loginForm.cbi">Back</a></h3>
								
							</div>
							<br />
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
</body>
</html>