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
			<%@ include file="../fragments/left-menu.jsp"%>
			<%@ include file="../fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col">
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2>${menuName}</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                    <li class="dropdown">
	                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fa fa-download"></i>&nbsp;Download</a>
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
        url = "${hostName}${siteRoot}/views/${url}",
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