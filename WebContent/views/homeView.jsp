<%@page import="phd.collins.imls.agents.ontologies.authentication.AuthenticateResponse"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parUsername = "", parPassword = "";

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		Info.sout("In homeView.jspView Params Keys: " + viewParams.getAllParameterKeys());
	}
	
	if (viewParams != null){
		//parUsername = (String)viewParams.getParameter(ParameterNames.PN_AUTH_USERNAME);
		//parPassword = (String)viewParams.getParameter(ParameterNames.PN_AUTH_PASSWORD);
	}
	
	String username = "", usertype = "", userLastLoginDate = "";
	AuthenticateResponse authResponse = (AuthenticateResponse)SessionManager.getUserAuthResponse(session);
	if (authResponse != null){
		usertype = authResponse.getUserType();
		username = authResponse.getUsername();
		userLastLoginDate = authResponse.getLastLoginDate();
	}
	
	
%>
<div>
	<h1>Welcome Home <%= username %> (Access Level: <%= usertype %>)!</h1>
	<div style="min-height: 100px;">
		<p>&nbsp;</p>
		<p>Your last login date was: <%= userLastLoginDate %></p>
		
		<ul>
			<li>Course Modules</li>
			<li>Field Courses</li>
			<li>Area Fields</li>
			<li>Study Areas</li>
		</ul>
	</div>
</div>