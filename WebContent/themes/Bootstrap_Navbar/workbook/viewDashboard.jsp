<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CPoT - Cybertrend Portal of Tableau</title>
<script type='text/javascript' src='${hostName}/javascripts/api/tableau-2.js'></script>
</head>
<link
	href="${pageContext.request.contextPath}/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/custom.min.css"
	rel="stylesheet">
<body class="nav-md"> 
	<div>
		<div>
			<div>


			<!-- page content -->
			<div>
			
				<div class="row">
	
	              <div class="col-md-12 col-sm-12 col-xs-12">
	                <div class="x_panel" style="100%">
	                  <div class="x_title">
	                    <h2>${menuName}</h2>
	                    <ul class="nav navbar-right panel_toolbox">
	                      <li class="dropdown">
	                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-download"></i>&nbsp;&nbsp;Download</a>
	                        <ul class="dropdown-menu" role="menu">
	                          <li><a href="#" onclick="exportToPDF();"><i class="fa fa-file-pdf-o"></i>&nbsp;&nbsp;PDF</a>
	                          </li>
	                          <li><a href="#" onclick="exportToImage();"><i class="fa fa-image"></i>&nbsp;&nbsp;Image(.png)</a>
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
			</div>
			</div>

<script type="text/javascript">
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
function exportWorkbook(){
	viz.showDownloadWorkbookDialog();
}
</script>
</body>
</html>