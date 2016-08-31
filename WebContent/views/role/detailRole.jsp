<!DOCTYPE html>
<%@page import="com.cybertrend.pot.entity.Role"%>
<%@page import="com.cybertrend.pot.dao.RoleMenuDAO"%>
<%@page import="com.cybertrend.pot.dao.MenuDAO"%>
<%@page import="com.cybertrend.pot.entity.Menu"%>
<%@page import="com.cybertrend.pot.dao.DashboardDAO"%>
<%@page import="tableau.api.rest.bindings.ViewType"%>
<%@page import="java.util.List"%>

<script src="${pageContext.request.contextPath}/vendors/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/fastclick/lib/fastclick.js"></script>
<!-- page content -->
<div class="main_container">
	<div>
		<div>
			<div class="x_panel" style="">
				<div class="x_title">
					<h2>Role Detail</h2>
					<ul class="nav navbar-right panel_toolbox">
						<li><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span>
                    </button></li>
					</ul>
					<div class="clearfix"></div>
				</div>
				<div class="x_content">

					<section class="content invoice">
						<!-- title row -->
						<div class="row">
							<div class="col-xs-12 invoice-header">
								<h1>
									<i class="fa fa-user-secret"></i> ${role.name}
								</h1>
							</div>
							<!-- /.col -->
						</div>
						<!-- info row -->
						<div class="row invoice-info">
							<div class="col-sm-4 invoice-col">
								<!-- Isi Content -->
								<br> <b>Description :</b> ${role.description}
							</div>
							<!-- /.col -->
							<div class="col-sm-4 invoice-col">
								<address>
									<br> <b>Create Date :</b> ${role.createDate} <b>by </b>
									${role.createBy} <br> <b>Updated Date :</b>
									${role.updateDate} <b>by </b> ${role.updateBy}
								</address>
							</div>
							<!-- /.col -->
						</div>
						<!-- /.row -->

						<!-- Table row -->
						<div id="contentTable" class="row">
							<div class="col-xs-12 table">
								<div class="ln_solid"></div>
								<h3>Privilege Menu</h3>
								<table id="datatable" class="table table-striped table-bordered">
									<thead>
										<tr>
											<th>Menu Name</th>
											<th>Content Type</th>
											<th>Set Privilege Menu</th>
										</tr>
									</thead>

									<tbody>
										<%
											List<Menu> menus = (List<Menu>) request.getAttribute("menus");
											for (Menu menu : menus) {
										%>
										<tr>
											<td><i class="fa fa-sitemap"></i>&nbsp;&nbsp;<%=menu.getName()%></td>
											<td><%=menu.getContentType() != null ? menu.getContentType().toUpperCase() : "PARENT"%></td>
											<td id="dashboardAction">
												<%
													if (!RoleMenuDAO.isMenuExist(menu.getId(), ((Role) request.getAttribute("role")).getId())) {
												%> <a class="btn btn-success btn-xs"
												onclick="addToRole('${role.id}','<%= menu.getId()%>',this)"><i
													class="fa fa-plus"></i></a> <%
 												} else { %> 
 												<a class="btn btn-danger btn-xs"
												onclick="removeFromRole('${role.id}','<%= menu.getId()%>','yes',this)"><i
													class="fa fa-minus"></i></a> <%
 												}
 												%>
											</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
							<!-- /.col -->
						</div>
					</section>


					<!-- X-Content -->
				</div>
			</div>
		</div>
	</div>
</div>

<!-- /page content -->

<!-- Datatables -->
<script>
	function addToRole(role, menu, event) {
		$.ajax({
			type : 'post',
			url : "detailRole.cbi",
			cache : false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8;",
			data : {
				menuId : menu,
				roleId : role
			},
			beforeSend : function() {
				$(function() {
					var $elie = $("i", event), degree = 0, timer;
					rotate();
					function rotate() {
						$elie.removeClass('fa-plus');
						$elie.addClass('fa-spinner');
						$elie.css({
							WebkitTransform : 'rotate(' + degree + 'deg)'
						});
						$elie.css({
							'-moz-transform' : 'rotate(' + degree + 'deg)'
						});
						timer = setTimeout(function() {
							++degree;
							rotate();
						}, 2);
					}

				});
			},
			success : function(data) {
				$('#contentTable').load("detailRole.cbi?roleId=" + role + " #contentTable");
			},
			error : function(xhr, resp, text) {
				alert(xhr.status);
			}
		});
	}

	function removeFromRole(role, menu, remove, event) {
		$.ajax({
			type : 'post',
			url : "detailRole.cbi",
			cache : false,
			contentType : "application/x-www-form-urlencoded; charset=UTF-8;",
			data : {
				menuId : menu,
				roleId : role,
				deleteAction : remove

			},
			beforeSend : function() {
				$(function() {
					var $elie = $("i", event), degree = 0, timer;
					rotate();
					function rotate() {
						$elie.removeClass('fa-minus');
						$elie.addClass('fa-spinner');
						$elie.css({
							WebkitTransform : 'rotate(' + degree + 'deg)'
						});
						$elie.css({
							'-moz-transform' : 'rotate(' + degree + 'deg)'
						});
						timer = setTimeout(function() {
							++degree;
							rotate();
						}, 2);
					}

				});
			},
			success : function(data) {
				$('#contentTable').load("detailRole.cbi?roleId=" + role + " #contentTable");
			},
			error : function(xhr, resp, text) {
				alert(xhr.status);
			}
		});
	}
</script>