<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	ViewParameters viewParams = (ViewParameters)request.getAttribute("viewParameters");
	Info.sout("In authView.jspView Params Keys: " + viewParams.getAllParameterKeys());
%>

<div id="authbox">
	<div id="authcap">
    	<h2 class="authcaption" title="IMLS Authentication">&raquo; IMLS Authentication</h2>
	</div>
	
	<h2 id="info">Please enter your login details to access the IMLS Platform</h2>
    
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post">
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Username</li>
            	<li class="grid_3 formcontrol"><input class="textbox" name="username" type="text" /></li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Password</li>
            	<li class="grid_3 formcontrol"><input class="textbox" name="password" type="password" /></li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_1a formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input class="button" name="btnSignIn" type="submit" id="btnSignIn" value="Sign In" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
		</form>
	</div>

</div>
<div class="clearfix">&nbsp;</div>