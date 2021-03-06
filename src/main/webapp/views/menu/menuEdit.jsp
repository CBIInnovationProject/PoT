<%@page import="java.sql.Timestamp"%>
<%@page import="com.cybertrend.cpot.dao.DashboardDAO"%>
<%@page import="com.cybertrend.cpot.entity.Dashboard"%>
<%@page import="com.cybertrend.cpot.entity.Menu"%>
<%@page import="java.util.List"%>
<script src="${pageContext.request.contextPath}/vendors/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/fastclick/lib/fastclick.js"></script>
<link href="${pageContext.request.contextPath}/vendors/select2/css/select2.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/vendors/select2/js/select2.min.js"></script>
<link href="${pageContext.request.contextPath}/vendors/datatables.net-bs/css/dataTables.bootstrap.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script type='text/javascript' src="${pageContext.request.contextPath}/js/nicEdit-latest.js"></script>

<script type="text/javascript">
$('.select2').select2();
</script>

<div class="main_container">
	<div>
		<div>
			<div class="x_panel" style="">
				<div class="x_title">
					<h2>Edit Menu </h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><button id="btnClose" type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">�</span>
							</button></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">
					<form id="formid" method="post" action="menuSave.cbi"
						class="form-horizontal form-label-left">
						<% Menu menuView = (Menu) request.getAttribute("menuView"); %>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">Menu
								Name <span class="required">*</span>
							</label>
							<div class="col-sm-3">
								<input type="text" name="name" id="menu_name"
									value="${menuView.name}"
									class="form-control col-md-7 col-xs-12" required="required">
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">Parent
								Menu</label>
							<div class="col-sm-3">
								<select id="parentId" name="parentId" class="select2" style="width: 200px;">
									<option data-icon=""></option>
									<%
										List<Menu> parents = (List<Menu>) request.getAttribute("parentMenus");
										for(Menu parent:parents){ if(!parent.getId().trim().equals(menuView.getId())) {%>
										<option data-icon="<%=parent.getIcon()%>" value="<%= parent.getId() %>" <%if(menuView.getParentId()!=null) { if(menuView.getParentId().equals(parent.getId())) {%>selected<% } }%>><%=parent.getName() %></option>
								<% } }%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12"
								for="menu-order">Menu Order </label>
							<div class="col-sm-1">
								<input type="number" name="menuOrder" id="menuOrder"
									value="${menuView.menuOrder}" class="form-control col-md-3">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">Icon </label>
							<div class="col-sm-2">
								<% String dataIcon = menuView.getIcon().trim().length()>0?menuView.getIcon().substring(3):""; %>
								<button data-icon="<%=dataIcon%>" style="display: inline-block;" name="icon" class="btn btn-default ownicon1 classfontawesome" data-iconset="fontawesome" role="iconpicker" ></button>
							
							</div>
						</div>

						<% if (menuView.getContentType()!=null && menuView.getContentType().trim().equals("page")) {%>
							<div class="ln_solid"></div>
							<input type="hidden" name="contentType" id="contentType" value="page" />
							<textarea name="content" id="content_text"
							style="width: 800px; height: 150px;">${menuView.content}</textarea>
						<% } else if (menuView.getContentType()!=null && menuView.getContentType().trim().equals("tableau")) { %>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tableau URL <span class="required">*</span></label>
								<div class="col-sm-3">
									<select name="dashboard" id="dashboard" required class="select2">
											<option data-icon="fa fa-bar-chart" value="<%=menuView.getViewId() %>"><%=menuView.getContent().split("\\?&")[0] %></option>
										<% List<Dashboard> dashboards=DashboardDAO.getListDashboards(); for (Dashboard dashboard: dashboards) { %>
											<option data-icon="fa fa-bar-chart" value="<%= dashboard.getId() %>"><%=dashboard.getUrl()%></option>
										<%}%>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="menu-order">Tableau Parameters
								</label>
								<div class="col-sm-4">
									<input type="text" name="customParams" id="customParams" value="<%=menuView.getContent().split("\\?&")[1] %>" class="form-control col-md-3">
								</div>
							</div>
							<input type="hidden" name="contentType" id="contentType" value="tableau" />
						<% } else if (menuView.getContentType()!=null && menuView.getContentType().trim().equals("module")) {%>
							<input type="hidden" name="contentType" id="contentType" value="module" />
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Action Name<span class="required">*</span>
								</label>
								<div class="col-sm-2">
									<input type="text" onkeypress="return blockSpecialChar(event)" id="action" name="action" value="<%=menuView.getAction().replace(".cbi", "") %>" required class="form-control col-md-3 col-xs-3">
									<i>Not to allow special characters and space</i>
								</div>
							</div>
						<% } else { %>
							<div class="form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Leaf Menu</label>
								<div class="col-sm-9">
									<ul class="list-unstyled">${previewMenu}</ul>
								</div>
							</div>
						<% } %>
						<div class="ln_solid"></div>
						<div class="form-group">
							<div class="col-md-6" >
								<input type="hidden" id="menuId" name="menuId" value="${menuView.id}"></input>
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
	function blockSpecialChar(e){
	    var k;
	    document.all ? k = e.keyCode : k = e.which;
	    return ((k > 64 && k < 91) || (k > 96 && k < 123) || k == 8 || (k >= 48 && k <= 57));
    }
	nicEditors.allTextAreas() ;

	$(document).ready(function(){
		$("#formid").submit(function(event){	
			event.preventDefault();
			var $form = $( this ),
	        url = $form.attr( 'action' );
			var ne = nicEditors.findEditor("content_text");
			var posting = $.post(url, 
					{ 
						menuId:$("#menuId").val(),
						name:$("#menu_name").val(),
		                parentId:$('#parentId :selected').val(),
		                content:$(".nicEdit-main").html(),
		                action:$("#action").val(),
		                dashboard:$('#dashboard :selected').val(),
		                contentType:$("#contentType").val(),
		                menuOrder:$("#menuOrder").val(),
		                customParams:$("#customParams").val(),
		                icon:$(".ownicon1[style='display: inline-block;'] input[name='icon']").val(),
		                actionsave:1
					} );
			
    		posting.done(function(message) {
    			var alert = "success";
    			if(message.indexOf('ERROR')!==-1){
    				alert = "danger";
    			} else {
                    $(".name"+$("#menuId").val()).html($("#menu_name").val());
                    $(".updateDate"+$("#menuId").val()).html('<%=new Timestamp(System.currentTimeMillis())%>');
                    $("#"+$("#menuId").val()).hide().fadeIn('slow');
    			}
                $(".tambahan").hide().html("<div class=\"alert alert-"+alert+" alert-dismissible \" role=\"alert\">"+
                        "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">�</span>"+
                        "</button> "+new Date().toUTCString()+" - "+message+
                      "</div>").fadeIn('slow');
                document.getElementById('btnClose').click();
    		});
		});
	});
</script>