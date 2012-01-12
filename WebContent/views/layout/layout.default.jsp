<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String assetsBase = "assets";
	String testLayout = request.getParameter("test");
	String requestController = request.getParameter("requestController");
	
	boolean LAYOUT_TEST_MODE = (testLayout != null) && testLayout.equalsIgnoreCase("1");
	if ( LAYOUT_TEST_MODE ){ assetsBase = "../../assets"; }
	
	Info.sout("On the layout page, parameter passed as currentPage = " + requestController); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Intelligent Mobile Learning System</title>
	<link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.ico" />
	
	<link href="<%=assetsBase %>/css/cssreset.css" rel="stylesheet" type="text/css" />
	<link href="<%=assetsBase %>/css/cssrebuild.css" rel="stylesheet" type="text/css" />
	<link href="<%=assetsBase %>/css/960gs.css" rel="stylesheet" type="text/css" />
	<link href="<%=assetsBase %>/css/master.css" rel="stylesheet" type="text/css" />
	<link href="<%=assetsBase %>/css/formcontrols.css" rel="stylesheet" type="text/css" />
	
	<link href="<%=assetsBase %>/superfishmenu/superfish.css" rel="stylesheet" type="text/css" media="screen" />
	
	<script type="text/javascript" src="<%=assetsBase %>/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="<%=assetsBase %>/superfishmenu/hoverIntent.js"></script>
	<script type="text/javascript" src="<%=assetsBase %>/superfishmenu/superfish.js"></script>
	<script type="text/javascript">
		// initialise superfish menu plugins
		jQuery(function(){ jQuery('ul.sf-menu').superfish(); });
	</script>
</head>

<body>
	<div class="">
        <div class="container_16 pagewrapper">
            
            <div class="grid_16 alpha_omega header">
                <div class="topbar">
                    <div class="imls">Intelligent Mobile Learning System<sup>&reg;</sup></div>
                </div>
            </div>
            <div class="clear">&nbsp;</div>
            
            <% if (!SessionManager.isAuthenticated()){ %>
            <div id="navbar" class="alpha_omega">
            	<jsp:include page="../menu.default.jsp"></jsp:include>
            </div>
            <div class="clear">&nbsp;</div>
            <% } %>
            
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            
            <div class="grid_16 alpha_omega pagebody">
           	  <jsp:include page="<%= requestController %>" ></jsp:include>
            </div>
            <div class="clearfix">&nbsp;</div>
            
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            
            <div class="grid_16 alpha_omega footer">
                <div class="footerbar">
                	<ul id="footerlist">
                    	<li>&copy;Copyright, 2012 IMLS |</li>
                        <li>Terms &amp; Conditions |</li>
                        <li>Privacy Policy</li>
                    </ul>
                    
                    <div id="footerright">
                    	Powered By: theCLOUD<sup>&reg;</sup>
                    </div>
                </div>
            </div>
            <div class="clear">&nbsp;</div>
            
        </div>
	</div>
</body>
</html>
