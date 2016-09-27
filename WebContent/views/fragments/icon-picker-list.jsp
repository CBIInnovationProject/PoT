<%@page import="com.cybertrend.pot.entity.Menu"%>

<!-- **************
Replace ## with < % to show dropdown list icon vendors 
*********** -->
<!-- <div class="form-group">
	## Menu menuView2 = (Menu) request.getAttribute("menuView");%>
	<label class="control-label col-md-3 col-sm-3 col-xs-12">Icon Type </label>
	<div class="col-sm-3">
		<select name="icontype" id="icontype1" class="selectpicker">
			<option value="classfontawesome" ##if(menuView2!=null) { if(menuView2.getIcon()!=null && !menuView2.getIcon().trim().equals("")) { if(menuView2.getIcon().split(" ")[0].equals("fa")) {%>selected##} } }%>>fontawesome</option>
			<option value="classidglyphicon" ##if(menuView2!=null) { if(menuView2.getIcon()!=null && !menuView2.getIcon().trim().equals("")) { if(menuView2.getIcon().split(" ")[0].equals("glyphicon")) {%>selected##} } }%>>glyphicon</option>
		</select>
	</div>
</div-->
<div class="form-group">
	<label class="control-label col-md-3 col-sm-3 col-xs-12">Icon </label>
	<div class="col-sm-2">
		<button style="display: inline-block;" name="icon" class="btn btn-default ownicon1 classfontawesome" data-iconset="fontawesome" role="iconpicker" ></button>
		<button style="display: none;" name="icon" class="btn btn-default ownicon1 classidglyphicon" data-iconset="glyphicon" role="iconpicker" ></button>
	</div>
</div>