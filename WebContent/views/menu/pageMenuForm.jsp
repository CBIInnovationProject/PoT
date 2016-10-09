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
								<form id="formid" method="post" action="menuSave.cbi" class="form-horizontal form-label-left">

									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Menu Name <span class="required">*</span>
										</label>
										<div class="col-sm-3">
											<input type="text" name="name" id="menu_name" value="${menuView.name}" class="form-control col-md-7 col-xs-12" required="required">
										</div>
									</div>
									
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12">Parent Menu</label>
										<div class="col-sm-3">
											<select  id="parentId" name="parentId" class="selectpicker">
												<option></option>
												<% List<Menu> parents = (List<Menu>)request.getAttribute("parentMenus"); 
												Menu menuView = (Menu) request.getAttribute("menuView");
												for(Menu parent:parents){%>
													<option data-icon="<%=parent.getIcon()%>" value="<%= parent.getId() %>"><%=parent.getName() %></option>
												<% }%>
											</select>
										</div>
									</div>
									<input type="hidden" name="contentType" id="contentType" value="page"/>
									<div class="form-group">
										<label class="control-label col-md-3 col-sm-3 col-xs-12"
											for="menu-order">Menu Order 
										</label>
										<div class="col-sm-1">
											<input type="number" name="menuOrder" id="menuOrder" value="0${menuView.menuOrder}" class="form-control col-md-3">
										</div>
									</div>
									
									<%@ include file="../fragments/icon-picker-list.jsp"%>
									
									
									<div class="ln_solid"></div>
									<textarea name="content" id="content_text" style="width: 100%;height: 150px;">${menuView.content}</textarea>
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
			var ne = nicEditors.findEditor("content_text");
			var posting = $.post(url, 
					{ 
						name:$("#menu_name").val(),
		                parentId:$('#parentId :selected').val(),
		                content:$(".nicEdit-main").html(),
		                contentType:$("#contentType").val(),
		                menuOrder:$("#menuOrder").val(),
		                icon:$(".ownicon1[style='display: inline-block;'] input[name='icon']").val(),
		                actionsave:1
					} );
			
    		posting.done(function(message) {
    			var alert = "success";
    			if(message.indexOf('ERROR')!==-1){
    				alert = "danger";
    			}
                $(".tambahan").hide().html("<div class=\"alert alert-"+alert+" alert-dismissible \" role=\"alert\">"+
                        "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>"+
                        "</button> "+new Date().toUTCString()+" - "+message+
                      "</div>").fadeIn('slow');
				$(".tambahan").css("display","block");  
				  $('#parentId').selectpicker('deselectAll');
				  $('#icontype1').selectpicker('deselectAll');
				  $('.classfontawesome i').removeAttr( "class" );
				  $('.classidglyphicon i').removeAttr( "class" );
					ne.setContent("");					  
                document.getElementById("formid").reset();
    		});
		});
	});
	</script>
	
	<!-- text Area  -->
	<script src="${pageContext.request.contextPath}/js/nicEdit-latest.js"></script>
	<script type="text/javascript">
	        bkLib.onDomLoaded(function() { nicEditors.allTextAreas() });
	</script>
	
</body>
</html>