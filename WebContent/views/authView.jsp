<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parUsername = "", parPassword = "";
	String AuthBoxTitle = "IMLS Agent Authentication";
	boolean parNonAgentAuth = false;

	ViewParameters viewParams = SessionManager.getViewParameters(request);
	
	if (viewParams != null){
		Info.sout("In authView.jspView Params Keys: " + viewParams.getAllParameterKeys());
		parUsername = (String)viewParams.getParameter(ParameterNames.PN_AUTH_USERNAME);
		parPassword = (String)viewParams.getParameter(ParameterNames.PN_AUTH_PASSWORD);
		parNonAgentAuth = Boolean.valueOf("" + viewParams.getParameter(ParameterNames.PN_AUTH_NO_AGENT));
		
		if (parNonAgentAuth)AuthBoxTitle = "IMLS Non-Agent Authentication";
	}
%>

<div id="authbox">
	<div id="authcap">
    	<h2 class="authcaption" title="IMLS Authentication">&raquo; <%= AuthBoxTitle %></h2>
	</div>
	
	<h2 id="info">Please enter your login details to access the IMLS Platform</h2>
    
	<div style="margin-bottom: 10px;">
		<form name="frmAuth" id="frmAuth" method="post" action="">
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Username</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_AUTH_USERNAME %>" type="text" value="<%= parUsername %>" />
            	</li>
            </ul>
            
            <ul class="formrow clearfix">
            	<li class="grid_1a formlabel">Password</li>
            	<li class="grid_3 formcontrol">
            		<input class="textbox" name="<%= ParameterNames.PN_AUTH_PASSWORD %>" type="password" value="<%= parPassword %>" />
            	</li>
            </ul>
            
            <ul class="formrow formButtons clearfix">
            	<li class="grid_1a formlabel">&nbsp;</li>
            	<li class="grid_3 formcontrol">
            		<input class="button" name="btnSignIn" type="submit" id="btnSignIn" value="Sign In" />
            		<input class="button" name="btnReset" type="reset" id="btnReset" value="Reset Fields" />
				</li>
            </ul>
            
            <ul>
            	<li>
            		<div style="text-align:center;margin:20px 0 10px;">
            			<% if (!parNonAgentAuth){ %>
							<a href="<%= LinksManager.AUTH_PAGE_NO_AGENT %>">&raquo;Non-Agent Based Authentication</a>
						<% } else { %>
							<a href="<%= LinksManager.AUTH_PAGE %>">&raquo;Agent Based Authentication</a>
						<% } %>
					</div>
				</li>
            </ul>
		</form>
	</div>

</div>