<%@page import="phd.collins.imls.util.SessionManager"%>
<%@page import="phd.collins.imls.util.FrontController"%>

<%
	String currentPage=""; String pageLayout="";
	
	if (!SessionManager.isAuthenticated()){
		currentPage = "auth";
	}
	else{
		currentPage = FrontController.getCurrentPage(request);
	}
	
	pageLayout = FrontController.getLayout(request); 
%>

<jsp:include page="<%= pageLayout %>">
	<jsp:param name="currentPage" value="<%= currentPage %>" />
</jsp:include>