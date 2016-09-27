<!DOCTYPE html>
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
							<ul class="nav side-menu">
							 <li><a href="${pageContext.request.contextPath}"><i class="fa fa-home"></i> Home</a></li>
							${treeMenu}
							</ul>
						</div>
						<!-- %@ include file="fragments/admin-menu.jsp" %-->
					</div>
					<!-- /sidebar menu -->
					<%@ include file="../fragments/footer-buttons.jsp" %>
				</div>
			</div>

			<%@ include file="../fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col">
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2>${menuName}</h2>
	                    <ul class="nav navbar-right panel_toolbox">
						<li><a href="#" class="form_data_alert" onclick="popup_alert_data();"><i class="fa fa-bell-slash"></i></a></li>
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-download"></i>&nbsp;&nbsp;Download</a>
	                        <ul class="dropdown-menu" role="menu">
	                          <li><a href="#" onclick="exportToPDF();"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;PDF</a>
	                          </li>
	                          <li><a href="#" onclick="exportToImage();"><i class="fa fa-image"></i>&nbsp;&nbsp;Image(.png)</a>
	                          </li>
	                          <li><a href="#" onclick="exportToCSV();"><i class="fa fa-file-excel-o"></i>&nbsp;&nbsp;CSV</a>
	                          </li>
	                        </ul>
	                      </li>
	                    </ul>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
						<div style='width:auto' id="vizContainer"></div>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
			
			</div>
			<!-- /page content -->

		<%@ include file="../fragments/footer.jsp" %>
		</div>
	</div>

<div class="modal fade" tabindex="-1" role="dialog" aria-labelledby="dataallert" aria-hidden="true" id="myModal" >
  <div class="modal-dialog modal-lg">
    <div class="modal-content" >
	<div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Data Alert Schedule</h4>
        </div>
		<div class="modal-body">
	<form id="formid" method="post" action="menuSave.cbi" class="form-horizontal form-label-left">
	<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Description <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" name="name" id="menu_name" value="" class="form-control col-md-7 col-xs-12" required="required">
										</div>
									</div>
									<div class="form-group">
									<label class="control-label col-md-3 col-sm-3 col-xs-12">Time Alert&nbsp;&nbsp;</label>
								<div class="col-sm-3">
								<div class="input-group clockpicker">
								<input type="text" class="form-control" value="18:00">
								<span class="input-group-addon">
								<span class="glyphicon glyphicon-time"></span>
								</span>
								</div>
								</div>
								</div>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Worksheet</label>
										<div class="col-sm-3">
											<select  id="parentId" name="parentId" class="selectpicker">
												<option>Worksheet1</option>
												<option>Worksheet2</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Dimension</label>
										<div class="col-sm-3">
											<select  id="parentId" name="parentId" class="selectpicker">
												<option>Dimension1</option>
												<option>Dimension2</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Measure</label>
										<div class="col-sm-3">
											<select  id="parentId" name="parentId" class="selectpicker">
												<option>Measure1</option>
												<option>Measure2</option>
											</select>
										</div>
									</div>
									
					
	</form>
	</div>
	<div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
  </div>
</div>

<%@ include file="../fragments/js-collection.jsp" %>
<script type="text/javascript">
$('.clockpicker').clockpicker({
    placement: 'bottom',
    align: 'left',
    autoclose: true,
	'default': 'now'
});

function popup_alert_data(){
		$('#myModal').modal("show");
}

$("#myModal").on('hidden.bs.modal', function () {
	$('.dropdown-toggle').dropdown();
});
		
var viz;
    var containerDiv = document.getElementById("vizContainer"),
        url = "${hostName}${siteRoot}/views/${url}?&",
        options = {
            hideTabs: true,
        	hideToolbar: true
        };

    viz = new tableau.Viz(containerDiv, url, options); 
    // Create a viz object and embed it in the container div.

function exportToPDF() {
    viz.showExportPDFDialog();
}
function exportToImage(){
	viz.showExportImageDialog();
	
}
function exportToCSV(){
	viz.showExportCrossTabDialog();
}
</script>
</body>
</html>