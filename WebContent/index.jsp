<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.FrontController"%>

<%
	String pageLayout="";
	String requestController="";
	
	pageLayout = FrontController.getLayout(request);
	
	if (!SessionManager.isAuthenticated()){
		requestController = FrontController.getAuthenticationController();
	}
	else{
		requestController = FrontController.getRequestController(request);
	}
%>

<jsp:include page="<%= pageLayout %>">
	<jsp:param name="requestController" value="<%= requestController %>" />
</jsp:include>