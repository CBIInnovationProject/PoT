<!DOCTYPE html>
<%@page import="com.cybertrend.cpot.entity.Menu"%>
<%@page import="java.util.List"%>
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
	                    <h2>${menu.name}</h2>
	                    <div class="clearfix"></div>
	                  </div>
	                  <div class="x_content">
							<!-- X-Content -->
							<div class="tambahan"></div>
		                    <table id="datatable" class="table table-striped table-bordered">
		                      <thead>
		                        <tr>
		                          <th>Name</th>
		                          <th>Icon</th>
		                          <th>Create Date</th>
		                          <th>Last Updated</th>
		                        </tr>
		                      </thead>
		
		                      <tbody>
							 	<% List<Menu> menus=(List<Menu>) request.getAttribute("menus"); 
							 	for (Menu menu: menus) { 
							 	String contentType = menu.getContentType()!=null?menu.getContentType().toUpperCase():"PARENT";
							 	String icon = contentType.equalsIgnoreCase("PARENT")?"fa fa-folder-o":(contentType.equals("TABLEAU")?"fa fa-line-chart":"fa fa-file-text-o");%>
		                        <tr id="<%=menu.getId()%>">
		                          <td><ul style="list-style-type: none;padding: 0;margin:0"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false" role="menu"><i class="<%=icon%>"></i>&nbsp;&nbsp;<%= menu.getName()%></a><ul class="dropdown-menu" role="menu">
			                          <li><a onclick="popup_detail('pageMenuEdit.cbi?menuId=<%=menu.getId()%>')" href="#">Edit</a>
			                          </li>
			                          <li class="divider"></li>
			                          <li><a onclick="doDelete('<%=menu.getId()%>','<%= menu.getName()%>')" href="#"><i class="fa fa-trash"></i>&nbsp;&nbsp;Remove</a>
			                          </li>
			                        </ul></li></ul>
		                          </td>
		                          <td width="5%" align="center"><i class="<%=menu.getIcon()%>"></i></td>
		                          <td><%= menu.getCreateDate()!=null?menu.getCreateDate():""%></td>
		                          <td><%= menu.getUpdateDate()!=null?menu.getUpdateDate():""%></td>
		                          
		                        </tr>
		                    	<%} %>
		                      </tbody>
		                    </table>
	                  </div>
	                </div>
	              </div>
	            </div>
	          </div>
			
			</div>
			<!-- /page content -->
		
			<div class="modal fade" role="dialog" aria-labelledby="myModal" aria-hidden="true" id="myModal" >
			  <div class="modal-dialog modal-lg">
			    <div id="modal-content" ></div>
			  </div>
			</div>

		<%@ include file="../fragments/footer.jsp" %>
		</div>

<%@ include file="../fragments/js-collection.jsp" %>

    <!-- Datatables -->
    <script>
      $(document).ready(function() {
          
        TableManageButtons = function() {
          "use strict";
          return {
            init: function() {
              handleDataTableButtons();
            }
          };
        }();

        $('#datatable').dataTable();
        $('#datatable-keytable').DataTable({
          keys: true
        });

        $('#datatable-responsive').DataTable();

        $('#datatable-scroller').DataTable({
          ajax: "js/datatables/json/scroller-demo.json",
          deferRender: true,
          scrollY: 380,
          scrollCollapse: true,
          scroller: true
        });

        var table = $('#datatable-fixed-header').DataTable({
          fixedHeader: true
        });

        TableManageButtons.init();
      });
      
      
      function doDelete(menuId, menuName){ 
    	  	swal({   
  	    	  	title: "Are you sure to delete menu '"+menuName+"'?",   
  	    		text: "You will not be able to recover this data!",   
  	    		type: "warning",   
  	    		showCancelButton: true,   
  	    		confirmButtonColor: "#DD6B55",   
  	    		confirmButtonText: "Yes, delete it!",   
  	    		cancelButtonText: "No, cancel plx!",   
  	    		closeOnConfirm: true,   
  	    		closeOnCancel: true }, 
  	      	function(isConfirm){   
  	    		if (isConfirm) {     
  	    			var posting = $.post("menuDelete.cbi", 
  	  					{ 
  	  						menuId:menuId
  	  					} );
  	  			
  	      		posting.done(function(message) {
  	      			var alert = "success";
  	      			if(message.indexOf('ERROR')!==-1){
  	      				alert = "danger";
  	      			}
  	                else{ 
  	                	table = $('#datatable').DataTable();
  	                	table.row("#"+menuId).remove().draw();
  	    			}
  	                $(".tambahan").hide().html("<div class=\"alert alert-"+alert+" alert-dismissible \" role=\"alert\">"+
   	                       "<button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\"><span aria-hidden=\"true\">×</span>"+
   	                       "</button> "+new Date().toUTCString()+" - "+message+"</div>").fadeIn('slow');
  	      		});
  	    		}
  	     	});
     	}
      
		function popup_detail(url){
		
			$('#myModal').on('show.bs.modal', function () {
				$('#modal-content').html("<div style='text-align: center;background: #ffffff;background-position: center center;background-repeat: no-repeat;background-image: url(../images/loading.gif);'><p>&nbsp;</p><br/><br/><br/><p>&nbsp;&nbsp;Loading ...</p><p>&nbsp;</p></div>");
		 			$('#modal-content').load(url);
			})
			$('#myModal').modal("show");
		}
		$("#myModal").on('hidden.bs.modal', function () {
			$('.dropdown-toggle').dropdown();
		});
    </script>
</body>
</html>