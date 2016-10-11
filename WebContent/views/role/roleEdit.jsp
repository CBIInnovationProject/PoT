<%@page import="java.sql.Timestamp"%>
<%@page import="com.cybertrend.cpot.entity.Role"%>
<%@page import="java.util.List"%>
<script src="${pageContext.request.contextPath}/vendors/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/fastclick/lib/fastclick.js"></script>
<link href="${pageContext.request.contextPath}/vendors/select2/css/select2.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/vendors/select2/js/select2.min.js"></script>
<link href="${pageContext.request.contextPath}/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">
$('.select2').select2();
</script>

<div class="main_container">
	<div>
		<div>
			<div class="x_panel" style="">
				<div class="x_title">
					<h2>Edit Role </h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><button id="btnClose" type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<form id="formid" method="post" action="roleSave.cbi"
						class="form-horizontal form-label-left">
						<% Role role = (Role) request.getAttribute("role"); %>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">Role
								Name <span class="required">*</span>
							</label>
							<div class="col-sm-3">
								<input type="text" name="name" id="role_name"
									value="${role.name}"
									class="form-control col-md-7 col-xs-12" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">Description <span class="required">*</span>
							</label>
							<div class="col-sm-6">
								<input type="text" id="description" name="description" value="${role.description}" required class="form-control col-md-7 col-xs-12">
							</div>
						</div>

						<div class="ln_solid"></div>
						<div class="form-group">
							<div class="col-md-6" >
								<input type="hidden" id="roleId" name="roleId" value="${role.id}"></input>
								<input class="btn btn-success submit-menu" type="submit"
									name="submitButton" value="Submit">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Iconpicker -->
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/bootstrap-iconpicker/js/iconset/iconset-glyphicon.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/bootstrap-iconpicker/js/iconset/iconset-fontawesome-4.3.0.min.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/vendors/bootstrap-iconpicker/js/bootstrap-iconpicker.min.js'></script>

<script type="text/javascript">
	$(document).ready(function(){
		$("#formid").submit(function(event){	
			event.preventDefault();
			var $form = $( this ),
	        url = $form.attr( 'action' );
			var posting = $.post(url, 
					{ 
						roleId:$("#roleId").val(),
						name:$("#role_name").val(),
		                description:$('#description').val(),
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
                $(".name"+$("#roleId").val()).html($("#role_name").val());
                $(".updateDate"+$("#roleId").val()).html('<%=new Timestamp(System.currentTimeMillis())%>');
                $("#"+$("#roleId").val()).hide().fadeIn('slow');
                document.getElementById('btnClose').click();
    		});
		});
	});
</script>