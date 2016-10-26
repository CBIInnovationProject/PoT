<!DOCTYPE html>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.cybertrend.cpot.entity.Menu"%>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>CPoT - Cybertrend Portal of Tableau</title>
<%@ include file="../fragments/styles-collection.jsp"%>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<%@ include file="../fragments/left-menu.jsp"%>
			<%@ include file="../fragments/top-navigation.jsp"%>

			<!-- page content -->
			<div class="right_col" role="main">

				<div class="row">

					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>${menu.name}</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<!-- Fill with Content -->
								<div class="tambahan"></div>
								<form id="formid" method="post" action="roleSave.cbi" class="form-horizontal form-label-left">

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Role Name <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" id="name" name="name" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Description <span class="required">*</span>
										</label>
										<div class="col-sm-6">
											<input type="text" id="description" name="description" required class="form-control col-md-7 col-xs-12">
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6">
											 <input  class="btn btn-success submit-menu" type="submit" name="submitButton" value="Submit">
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

		<%@ include file="../fragments/footer.jsp"%>
	</div>

	<%@ include file="../fragments/js-collection.jsp"%>
	<script type="text/javascript">
	$(document).ready(function(){
		$("#formid").submit(function(event){
			event.preventDefault();
			var $form = $( this ),
	          url = $form.attr( 'action' );
			var posting = $.post(url, 
					{ 
						name:$("#name").val(),
						description:$("#description").val(),
		                actionsave:1
					} );
			
    		posting.done(function(message) {
    			var alert = "success";
    			if(message.indexOf('ERROR')!==-1){
    				alert = "danger";
    			}
    			$(".tambahan").hide().html("<div class=\"alert alert-"+alert+" alert-dismissible fade in\" role=\"alert\">"+
                        "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>"+
                        "</button> "+new Date().toUTCString()+" - "+message+
                      "</div>").fadeIn('slow');
                document.getElementById("formid").reset();
    		});
		});
	});
	</script>
</body>
</html>