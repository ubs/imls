<%@page import="phd.collins.imls.util.LinksManager"%>
<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.ParameterNames"%>
<%@page import="phd.collins.imls.model.User"%>
<%@page import="phd.collins.imls.util.ViewParameters"%>
<%@page import="phd.collins.imls.util.Info"%>
<%
	String parUsername = "", parPassword = "";

	ViewParameters viewParams = SessionManager.getViewParameters(request);

	if (viewParams != null){
		Info.sout("In courseadminallView.jsp View Params Keys: " + viewParams.getAllParameterKeys());
	}
	
	if (viewParams != null){
		//parUsername = (String)viewParams.getParameter(ParameterNames.PN_AUTH_USERNAME);
		//parPassword = (String)viewParams.getParameter(ParameterNames.PN_AUTH_PASSWORD);
	}
	
%>
<div>
	<div class="grid_7">
		<a href="<%= LinksManager.STUDY_AREAS_ADMIN %>">Study Areas Admin</a>
	</div>
	
	<div class="grid_7">
		<a href="<%= LinksManager.AREA_FIELDS_ADMIN %>">Study Areas Admin</a>
	</div>
	
	<div class="grid_7">
		<a href="<%= LinksManager.FIELD_COURSES_ADMIN %>">Study Areas Admin</a>
	</div>
	
	<div class="grid_7">
		<a href="<%= LinksManager.COURSE_MODULES_ADMIN %>">Study Areas Admin</a>
	</div>
</div>