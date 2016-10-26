<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CPoT - Cybertrend Portal of Tableau</title>
</head>
<link
	href="${pageContext.request.contextPath}/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/custom2.css"
	rel="stylesheet">

<body class="nav-md"> 
	<div class="container body">
		<div class="main_container">
			<div class="menu_fixed">

			<!-- page content -->
			<div>
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2>${menu.name}</h2>
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

		</div>
	</div>
</body>
</html>