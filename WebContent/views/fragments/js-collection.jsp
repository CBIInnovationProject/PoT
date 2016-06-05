<!-- jQuery -->
<script
	src="${pageContext.request.contextPath}/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script
	src="${pageContext.request.contextPath}/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- FastClick -->
<script
	src="${pageContext.request.contextPath}/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script
	src="${pageContext.request.contextPath}/vendors/nprogress/nprogress.js"></script>

<!-- Custom Theme Scripts -->
<script src="${pageContext.request.contextPath}/js/custom.js"></script>

<script type="text/javascript">
	var videoElement = document.getElementsByTagName("*");

	function toggleFullScreen() {
		for (var i = 0, max = videoElement.length; i < max; i++) {
			if (!document.mozFullScreen && !document.webkitFullScreen) {
				if (videoElement[i].mozRequestFullScreen) {
					videoElement[i].mozRequestFullScreen();
				} else {
					videoElement[i]
							.webkitRequestFullScreen(Element.ALLOW_KEYBOARD_INPUT);
				}
			} else {
				if (document.mozCancelFullScreen) {
					document.mozCancelFullScreen();
				} else {
					document.webkitCancelFullScreen();
				}
			}
		}
	}
</script>
<!-- TableauViz API -->
<script type='text/javascript'
	src='${hostName}/javascripts/api/tableau-2.js'></script>
	
<!-- Iconpicker -->
<script type="text/javascript" src="${pageContext.request.contextPath}/vendors/bootstrap-iconpicker/js/iconset/iconset-fontawesome-4.3.0.min.js"></script>
<script type='text/javascript' src='${pageContext.request.contextPath}/vendors/bootstrap-iconpicker/js/bootstrap-iconpicker.min.js'></script>

<!-- bootstrap-wysiwyg -->
<script src="${pageContext.request.contextPath}/vendors/bootstrap-wysiwyg/js/bootstrap-wysiwyg.min.js"></script>
<script src="${pageContext.request.contextPath}/vendors/jquery.hotkeys/jquery.hotkeys.js"></script>
<script src="${pageContext.request.contextPath}/vendors/google-code-prettify/src/prettify.js"></script>