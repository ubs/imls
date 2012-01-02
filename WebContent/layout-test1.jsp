<%@page import="phd.collins.imls.util.Info"%>
<%@page import="phd.collins.imls.util.FrontController" %>

<% 
	String currentPage = FrontController.getCurrentPage(request);
	String layout = FrontController.getLayout(request);
	Info.sout("Main Page on Layout test 1 = " + currentPage); 
%>

<%@ include file="view/layout/testinclude1.jsp" %>
<% //@ include file="view/layout/testinclude1.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Intelligent Mobile Learning System</title>
<link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.ico">

<link href="assets/css/cssreset.css" rel="stylesheet" type="text/css" />
<link href="assets/css/cssrebuild.css" rel="stylesheet" type="text/css" />
<link href="assets/css/960gs.css" rel="stylesheet" type="text/css" />
<link href="assets/css/master.css" rel="stylesheet" type="text/css" />

<link rel="stylesheet" type="text/css" href="assets/superfishmenu/superfish.css" media="screen">
<script type="text/javascript" src="assets/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="assets/superfishmenu/hoverIntent.js"></script>
<script type="text/javascript" src="assets/superfishmenu/superfish.js"></script>
<script type="text/javascript">
	// initialise superfish menu plugins
	jQuery(function(){
		jQuery('ul.sf-menu').superfish();
	});
</script>
</head>

<body>
	<div class="">
        <div class="container_16 pagewrapper">
            
            <div class="grid_16 alpha_omega header">
                <div class="topbar">
                    <div class="netlivesuite">Intelligent Mobile Learning System<sup>&reg;</sup></div>
                </div>
            </div>
            <div class="clear">&nbsp;</div>
            
            <div style="background:#FF6805; height:31px; border-bottom: 1px solid #EA5D00;" id="navbar" class="alpha_omega">
            	<ul class="sf-menu">
                    <li class="current">
                        <a href="#a">menu item 1</a>
                        <ul>
                            <li>
                                <a href="#aa">menu item that is quite long</a>
                            </li>
                            <li class="current">
                                <a href="#ab">menu item</a>
                                <ul>
                                    <li class="current"><a href="#">menu item</a></li>
                                    <li><a href="#aba">menu item</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    
                    <li>
                        <a href="#">menu item 2</a>
                    </li>
                    
                    <li>
                        <a href="#">menu item 3</a>
                        <ul>
                            <li>
                                <a href="#">menu item</a>
                                <ul>
                                    <li><a href="#">short</a></li>
                                    <li><a href="#">short</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">menu item</a>
                                <ul>
                                    <li><a href="#">menu item</a></li>
                                    <li><a href="#">menu item</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                    
                    <li>
                        <a href="#">menu item 4</a>
                    </li>	
                </ul>
            </div>
            <div class="clear">&nbsp;</div>
            
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            
            <div class="grid_16 alpha_omega pagebody">
           	  <div id="appboxeswrapper">
                    <div class="grid_5">
                        <div class="appbox">
                            <p class="info">Sign into IMLS Platform</p>
                            <div class="appclickbox">
                                <a href="http://www.netlivecertng.com/ofsc/" title="FSC Platform">Fire Safety Certification &raquo;</a>
                            </div>
                        </div>
                    </div>
                    <div class="clear">&nbsp;</div>
               </div>

            </div>
            <div class="clear">&nbsp;</div>
            
            <p>&nbsp;</p>
            <p>&nbsp;</p>
            
            <div class="grid_16 alpha_omega footer">
                <div class="footerbar">
                	<ul id="footerlist">
                    	<li>&copy;Copyright, 2012 IMLS |</li>
                        <li>Terms &amp; Conditions |</li>
                        <li>Privacy Policy</li>
                    </ul>
                    <div id="footerIbeat">
                    	Powered By: theCLOUD<sup>&reg;</sup>
                    </div>
                </div>
            </div>
            <div class="clear">&nbsp;</div>
            
        </div>
	</div>
</body>
</html>
