<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parUsername = "", parPassword = "";

	ViewParameters viewParams = SessionManager.getViewParameters(request);
	Info.sout("In homeView.jspView Params Keys: " + viewParams.getAllParameterKeys());
	
	if (viewParams != null){
		//parUsername = (String)viewParams.getParameter(ParameterNames.PN_AUTH_USERNAME);
		//parPassword = (String)viewParams.getParameter(ParameterNames.PN_AUTH_PASSWORD);
		
		//AuthenticateResponse authResponse = (AuthenticateResponse)viewParams.getParameter(ParameterNames.PN_APP_INIT_ADMIN_USER);
	}
	
	
%>
<div class="clearfix">
	<h1>Welcome Home Nicki Minaj!</h1>
</div>